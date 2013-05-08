package semante.prover.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;
import lombok.val;
import lombok.extern.slf4j.Slf4j;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.PredCalc;
import semante.prover.OutputParser;
import semante.prover.Prover;
import semante.prover.ProverException;
import semante.prover.ProverArgs.ResultType;
import semante.prover.impl.IProverRunner.ExitStatus;
import semante.settings.Settings;

@Slf4j
public class IProver implements Prover {

	private final static int TimeoutSeconds = 60;
	private final static int ShortTimeoutSeconds = TimeoutSeconds/10;

	private final String		path;
	private final PredCalc		fol;
	
	public IProver(Settings settings, PredCalc pcalc) {
		this.fol  = pcalc;
		this.path = settings.get("SemAnTE","Prover","Location");
	}

	@Override
	public boolean prove(Formula text, Formula hypothesis) throws ProverException {
		return prove(text, hypothesis, "");
	}	
	
	@Override
	public boolean prove(Formula txt, Formula hyp, String subsumptionRules) throws ProverException  {

		IProverArgs proverArgs = null;
		File tempFile = null;
		
		try{
			val proofInput = toProofInput(txt, hyp, subsumptionRules);
			System.err.println(proofInput);
			proverArgs = new IProverArgs(txt.toString(), hyp.toString());
			
			String fileName;
			try {
				tempFile = File.createTempFile("prover",".in");
				fileName = tempFile.getAbsolutePath();
				@Cleanup
				val out = new BufferedWriter(new FileWriter(fileName));
				log.trace("Prover input=["+proofInput+"]");
				out.write(proofInput);
			} catch (IOException e) {
				log.trace("failed to create input file for prover");
				throw new ProverException(e);
			}

			ThreadController controller = new ThreadController();

			val exes = new HashMap<String, OutputParser>();
			exes.put("prover9", new IOutputParser.IProver9OutputRecognizer()); 
			exes.put("mace4", new IOutputParser.IMace4OutputRecognizer());

			val threads = new ArrayList<IProverRunner>();
			for (val exe : exes.keySet() ) {
				val file = new File(this.path, exe).getAbsolutePath();
				threads.add(new IProverRunner(new String[] { file, "-f",
						fileName }, exe, controller, TimeoutSeconds * 3 / 4,
						exes.get(exe)));
			}

			for (val thread : threads) {
				log.trace("Running thread (" + thread.getProcessName() + ")");
				thread.start();
			}
			
			log.trace("main thread executed working threads");
			
			// TODO some documentation on the meaning of these variables would be nice.
			boolean isInterrupted = false;
			boolean isTimeout     = false;
			long currentTime      = System.currentTimeMillis();
			long endLongTimeout   = currentTime + TimeoutSeconds*1000;
			long endShortTimeout  = currentTime + ShortTimeoutSeconds*1000;
			long endTimeout       = 0;
			long timeout          = 0;
			int numInformants     = 0; 
			
			log.trace("main thread goes into wait() loop");

			// we loop as long as one of the threads is still waiting for its process to finish AND we 
			// haven't got a determined result from a finished thread AND the timeout hasn't expired
			while ((numInformants = controller.getInformantsCount()) < 2
				&& controller.isUndetermined() && !isTimeout) {
				try {
					// long timeout if none of the threads has informed yet,
					// short if one of them has.
					if (numInformants==0){
						log.trace("Setting long timeout");
						endTimeout = endLongTimeout;
					} else {
						log.trace("Setting short timeout");
						endTimeout = endShortTimeout;
					} 
					timeout = Math.max(endTimeout - System.currentTimeMillis(), 0);
	
					if (timeout > 0) {
						log.trace("main thread is going into a wait("+timeout+") call");
						synchronized(controller) {
							controller.wait(timeout);
						}
						log.trace("main thread is out of wait()");
					} else {
						isTimeout = true;
						log.trace("No time to wait anymore, getting out of wait() loop");					
					}
				} catch (InterruptedException e) {
					// in case we were interrupted unexpectedly we try to close the running threads (and processes) and then throw an exception
					// (note: we are not supposed to get here regularly)
					log.trace("main thread interrupted");
					isInterrupted = true;
					e.printStackTrace();
				}
			}

				
			// either if we were interrupted, if a thread notified, or if a timeout expired, we will close the threads now 
			// (although one or the two of them might ended already)
			for (IProverRunner thread : threads) {
				if (thread.getExitStatus()==ExitStatus.UNSET) {
					log.trace("Interuppting a working thread (" + thread.getProcessName()+")");
					thread.interrupt();
				}
				try {
					log.trace("Waiting for the thread to end (" + thread.getProcessName()+")");
					thread.join(TimeoutSeconds*1000);
					log.trace("Thread seems to be ended (" + thread.getProcessName()+")");
				} catch (InterruptedException e) {
					// another weird case - for some reason we were interrupted ...
					log.trace("main thread interrupted while waiting for another thread to end");
					e.printStackTrace();
					isInterrupted = true;
				}
			}
		
			if (isInterrupted) {
				throw new ProverException("Error in theorem prover's main thread interupppted unexpectedly");
			} else if (isTimeout) {
				throw new ProverException("Timeout expired while waiting for theorem provers's processes to respond");
			} else {

				log.trace("Both threads ended, checking for IO errors");
				for (IProverRunner thread : threads) {
					if (thread.getExitStatus()==ExitStatus.ERROR) {
						log.trace("IO error found ("+thread.getProcessName()+")");
						throw new ProverException("Error executing " + thread.getProcessName() + " executable",thread.geIOtException());
					}
				}
				
				// after both threads ended and prover processes were ended we can analyze the results
				log.trace("No errors, now analyzing results");
				
				// loop over the results, finding a non-undetermined one, making sure there are no contradictory results 
				ResultType finalRec = ResultType.Undetermined;
				List<ResultType> resSet1 = Arrays.asList(ResultType.ProofFound,ResultType.NoCounterexampleCanBeFound);
				List<ResultType> resSet2 = Arrays.asList(ResultType.CounterexampleFound,ResultType.NoProofCanBeFound);
				for (ResultType currentRec : controller.getResults()) {
					log.trace("Checking result: "+currentRec.toString());
					if (currentRec!=finalRec && currentRec!=ResultType.Undetermined) {
						if (finalRec==ResultType.Undetermined) {
							finalRec = currentRec;
							log.trace("Updating final result to: " + finalRec.toString());
						} else {
							// so currentRec != finalRec and both aren't Undetermined 								
							if (resSet1.containsAll(Arrays.asList(currentRec,finalRec)) ||
								resSet2.containsAll(Arrays.asList(currentRec,finalRec))) {
								log.trace("Second output recognized in line with the first");
							} else {
								log.trace("Strangely enough we got an ambiguous recognition");
								throw new ProverException("Ambiguous recognition by Prover9 and Mace4");
							}
						}						
					}
				}

				if (finalRec==ResultType.Undetermined) {
					log.trace("The result is undetermined for both threads"); 
					throw new ProverException("Failed to determine result based on prover's output");
				}
				
				proverArgs.setResultType(finalRec);
				
				log.trace("Reached a final decision: " + proverArgs.getResultType().toString());
				
				return 	resSet1.contains(finalRec) ? true : false;
			}
		} finally {
			if (tempFile!=null) {
				tempFile.delete();
			}
		}
	}

	public String toProofInput(Formula txt, Formula hyp, String subs) {
		val out = new StringBuilder();
		out.append("formulas(assumptions).\n");
		out.append(fol.format(txt) + ".\n");
		if (subs != null) {
			out.append(subs + "\n");
		}
		out.append("end_of_list.\n");
		out.append("\n");
		out.append("formulas(goals).\n");
		out.append(fol.format(hyp) + ".\n");
		out.append("end_of_list.");
		return out.toString();
	}
	
	@Getter
	public static final class ThreadController {
		
		int						informantsCount	= 0;
		boolean					undetermined	= true;
		final List<ResultType>	results			= new ArrayList<ResultType>();
		
		public final synchronized void inform(final ResultType res) {
			informantsCount++;
			results.add(res);
			undetermined = undetermined && (res==ResultType.Undetermined);
		}
	}
}
