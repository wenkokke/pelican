package semante.prover.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Cleanup;
import lombok.val;
import predcalc.ExprForm;
import predcalc.FOLExpr.Formula;
import predcalc.PredCalc;
import semante.prover.Prover;
import semante.prover.ProverOutput;
import semante.prover.ProverOutput.ResultType;
import semante.prover.ProverResult;
import semante.settings.Settings;

public class IProver implements Prover {

	private static final int P9_EC_MAX_PROOFS 	= 0;
	private static final int P9_EC_FATAL 		= 1;
	private static final int P9_EC_SOS_EMPTY 	= 2;
	private static final int P9_EC_MAX_MEGS 	= 3;
	private static final int P9_EC_MAX_SECONDS 	= 4;
	private static final int P9_EC_MAX_GIVEN 	= 5;
	private static final int P9_EC_MAX_KEPT 	= 6;
	private static final int P9_EC_ACTION 		= 7;
	private static final int P9_EC_SIGINT 		= 101;
	private static final int P9_EC_SIGSEGV 		= 102;

	private final static int FULL_TIMEOUT_SEC = 60;
	private final static String PROVER9_EXE_NAME = "prover9";

	private PredCalc fol;
	private String proverPath;

	public IProver(Settings settings, PredCalc pcalc) {
		this.fol  = pcalc;
		this.proverPath = settings.get("SemAnTE","Prover","Location");
	}

	@Override
	public ProverResult prove(ExprForm<Formula> textExp, ExprForm<Formula> hypoExp) {
		return prove(textExp,hypoExp,"");
	}

	@Override
	public ProverResult prove(ExprForm<Formula> textExp, ExprForm<Formula> hypoExp,
			String subsumptionRules) {

		File 			tempFile = null;
		Process 		process = null;
		Timer 			timer = null;
		ProverOutput 	proverOutputPF = null;
		StringBuffer 	prover9OutputBuffer = new StringBuffer();
		
		StreamGobbler 	errorGobbler = null;
		StreamGobbler 	outputGobbler = null;

		ResultType 		resultType = null;
		
		String prooverInput = toProverInput(textExp, hypoExp, subsumptionRules);

		String fileName;
		
		try {
			tempFile = File.createTempFile("prover",".in");
			fileName = tempFile.getAbsolutePath();
			
			@Cleanup
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(prooverInput);
			out.flush();
			
			val prover9 = new File(proverPath,PROVER9_EXE_NAME);
			val timeout = FULL_TIMEOUT_SEC * 3/4;
			val command = new String[] { prover9.getPath(), "-t", Integer.toString(timeout), "-f", fileName};
			
			timer = new Timer(true);
			val interrupter = new InterruptTimerTask(Thread.currentThread());
			timer.schedule(interrupter, timeout * 1000);

			System.out.println("Running Prover process ["+prover9+"]");
			process = Runtime.getRuntime().exec(command);

			System.out.println("Prover is running");

			// readers for the error and output stream (executed in separated threads)
			errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR", prover9OutputBuffer);            
			outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT", prover9OutputBuffer);

			// kick them off
			errorGobbler.start();
			outputGobbler.start();
			
			System.out.println("Waiting for prover to end");
			int exitVal = process.waitFor();

			String resultDesc = prover9exitCodeToString(exitVal);
			resultType = prover9exitCodeToResultEnum(exitVal);
			
			// the process has ended gracefully (otherwise - the InterruptedException would have been thrown)
			System.out.println("Prover ended with exit value: [" + exitVal + "], meaning: [" + resultDesc + "], type: [" + resultType + "]");

		} catch(IOException e) {
			proverOutputPF = new IProverOutput("Failed to create input file or to execute the process - " + e.getMessage(),ResultType.Error);
		} catch(InterruptedException e) {
			// in case of a timeout or interruption by the main thread
			process.destroy();
			proverOutputPF = new IProverOutput("Prover process was interuppted" + (e.getMessage()!=null ? " - " + e.getMessage() : ""),ResultType.Interuppted);
		} finally {

			// if the gobblers were created we will wait for them to end 
			// (they are supposed to because the process is ended/destroyed) 
			if (errorGobbler!=null && outputGobbler!=null) {
				List<StreamGobbler> streamGobblers = Arrays.asList(errorGobbler,outputGobbler);
				for (StreamGobbler streamGobbler : streamGobblers) {
					if (streamGobbler.isAlive()) {
						try {
							System.out.println("Waiting for globber " + streamGobbler.type + " to end: ");
							streamGobbler.join();
						} catch(InterruptedException e) {
							System.out.println("Interuppted while waiting for stream gobbler to end");
						}
					}
				}
			}

			//System.out.println("Globbers have ended");
			
			// if the process ended gracefully or some unknown exception was thrown
			if (proverOutputPF==null) {

				// in case of graceful termination - now that the gobblers have ended we can read the output buffer and parse it
				if (resultType!=null) {
					String proverOutput = prover9OutputBuffer.toString();
					if (resultType==ResultType.NoProofCanBeFound && proverOutput.matches("(?s).*Exiting with [1-9]+ proofs?.*")) {
						resultType = ResultType.ProofFound;
						System.out.println("Prover stdout indicates that a proof WAS found; dismissing exit code indication, type is set to: [" + resultType + "]");
					}
					proverOutputPF = new IProverOutput(proverOutput,resultType);
				} else {
					// in case of some unknown exception - report error
					proverOutputPF = new IProverOutput("Unknown error",ResultType.Error);
				}
			}


			if (timer!=null) {
				timer.cancel(); // If the process returns within the timeout period, we have to stop the interrupter
				// so that it does not unexpectedly interrupt some other code later.
			}

			Thread.interrupted();   // We need to clear the interrupt flag on the current thread just in case
			// interrupter executed after waitFor had already returned but before timer.cancel
			// took effect. Oh, and there's also Sun bug 6420270 to worry about here.

			if (tempFile!=null) {
				boolean del = true;

				if (del) { 
					//System.out.println("Deleting temp input file: " + tempFile.toString()); 
					tempFile.delete();
				} else {
					System.out.println("NOT deleting temp input file: " + tempFile.toString()); 
				}
			}

		}

		ProverOutput proverOutputCMF = new IProverOutput(null,ResultType.NotRun);
		return new IProverResult(prooverInput, proverOutputPF, proverOutputCMF);
	}


	public String toProverInput(ExprForm<Formula> txt, ExprForm<Formula> hyp, String subs) {
		StringBuilder out = new StringBuilder();
		out.append("formulas(assumptions).\n");
		out.append("% Pragmatics:\n");
		for (Formula prg : txt.getPragmatics()) {
			out.append(fol.format(prg) + ".\n");
		}
		for (Formula prg : hyp.getPragmatics()) {
			out.append(fol.format(prg) + ".\n");
		}
		out.append("\n% Semantics:\n");
		out.append(fol.format(txt.getSemantics()) + ".\n");
		if (subs != null) {
			out.append(subs + "\n");
		}
		out.append("end_of_list.\n");
		out.append("\n");
		out.append("formulas(goals).\n");
		out.append(fol.format(hyp.getSemantics()) + ".\n");
		out.append("end_of_list.");
		System.err.println(out);
		return out.toString();
	}


	class InterruptTimerTask extends TimerTask {

		private Thread thread;

		public InterruptTimerTask(Thread t) {
			this.thread = t;
		}

		public void run() {
			System.out.println("Timeout expired!");
			thread.interrupt();
		}
	}

	private String prover9exitCodeToString(int exitCode) {
		switch (exitCode){	
		case P9_EC_MAX_PROOFS: 	return "The specified number of proofs (max_proofs) was found.";
		case P9_EC_FATAL: 		return "A fatal error occurred (user's syntax error or Prover9's bug).";
		case P9_EC_SOS_EMPTY: 	return "Prover9 ran out of things to do (sos list exhausted).";
		case P9_EC_MAX_MEGS: 	return "The max_megs (memory limit) parameter was exceeded.";
		case P9_EC_MAX_SECONDS: return "The max_seconds parameter was exceeded.";
		case P9_EC_MAX_GIVEN: 	return "The max_given parameter was exceeded.";
		case P9_EC_MAX_KEPT: 	return "The max_kept parameter was exceeded.";
		case P9_EC_ACTION: 		return "A Prover9 action terminated the search.";
		case P9_EC_SIGINT: 		return "Prover9 received an interrupt signal.";
		case P9_EC_SIGSEGV: 	return "Prover9 crashed, most probably due to a bug.";
		default:
			return "Unrecognized exit code";
		}
	}

	private ResultType prover9exitCodeToResultEnum(int exitCode) {
		switch (exitCode){	
		case P9_EC_MAX_PROOFS: 	return ResultType.ProofFound;
		case P9_EC_FATAL: 		return ResultType.Error;
		case P9_EC_SOS_EMPTY: 	return ResultType.NoProofCanBeFound;
		case P9_EC_MAX_MEGS: 	
		case P9_EC_MAX_SECONDS: 
		case P9_EC_MAX_GIVEN: 	
		case P9_EC_MAX_KEPT: 	
		case P9_EC_ACTION: 		
		case P9_EC_SIGINT: 		return ResultType.Interuppted;
		case P9_EC_SIGSEGV: 	return ResultType.Error;
		default:
			return ResultType.Unset;
		}
	}

	class StreamGobbler extends Thread {
		InputStream is;
		String type;
		StringBuffer os;

		StreamGobbler(InputStream is, String type) {
			this(is, type, null);
		}

		StreamGobbler(InputStream is, String type, StringBuffer os) {
			this.is = is;
			this.type = type;
			this.os = os;
		}

		/** creates readers to handle the text created by the external program
		 */		
		public void run() {
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line=null;
				while ( (line = br.readLine()) != null) {
					synchronized (os) {
						if (os != null) {
							os.append(line + "\n");
						}
					}
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();  
			}
		}
	}

}
