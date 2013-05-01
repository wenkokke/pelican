package semante.prover.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import lombok.extern.slf4j.Slf4j;

import semante.prover.OutputParser;
import semante.prover.ProverArgs.ResultType;
import semante.prover.impl.IProver.ThreadController;

@Slf4j
public final class IProverRunner extends Thread {

	enum ExitStatus {SUCCESS, INTERRUPTED, TIMEOUT, ERROR, UNSET}

	private String[]		command;
	private StringBuffer	outputBuffer;
	ThreadController		controller;
	int						exitVal;
	private ExitStatus		exitStatus;
	private Timer			timer;
	private IOException		ex;
	private String			processName;
	private int				timeout;
	private OutputParser	outputParser;
	private ResultType		resultType;
	
	public IOException geIOtException() {
		return ex;
	}

	public void setEx(IOException ex) {
		this.ex = ex;
	}

	public IProverRunner(String[] command, String processName, ThreadController controller, int timeout, OutputParser outputParser) {
		this.command = command;
		this.controller = controller;
		this.outputBuffer = new StringBuffer();
		this.exitStatus = ExitStatus.UNSET;
		this.processName = processName;
		this.timer = null;
		this.timeout = timeout;
		this.outputParser = outputParser;
		this.resultType = ResultType.Undetermined;
	}

	@Override
	public void run() {
		
		log.trace("Working thread is running ["+getProcessName()+"]");
		
		Process process = null;

		try {
			timer = new Timer(true);
			InterruptTimerTask interrupter = new InterruptTimerTask(Thread.currentThread());
			timer.schedule(interrupter, timeout /*seconds*/ * 1000 /*milliseconds per second*/);

			log.trace("Running process ("+getProcessName()+")");
			process = Runtime.getRuntime().exec(command);

			// readers for the error and output stream (executed in separated threads)
			StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR", outputBuffer);            
			StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT", outputBuffer);

			log.trace("Starting streams readers ("+getProcessName()+")");
			
			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			log.trace("Waiting for the process to end ("+getProcessName()+")");
			
			// wait for the process to end
			exitVal = process.waitFor();

			// the process has ended gracefully (otherwise - the InterruptedException would have been thrown)
			log.trace("Process (" + command[0] + ") ended with exit value: [" + exitVal + "]");

			//log.trace("Process output: ["+getOutputStream()+"] (" +getProcessName()+")");
			setResultType(outputParser.recognizeOutput(outputBuffer.toString()));

			log.trace("Recognized output as: " + getResultType() +" (" +getProcessName()+")");
			
			setExitStatus(ExitStatus.SUCCESS);

			
		} catch(InterruptedException e) {
			// in case of a timeout or interruption by the main thread
			log.trace("Thread (" + getProcessName() + ") was interuppted");
			//log.trace("Process output: ["+getOutputStream()+"] (" +getProcessName()+")");
			
			process.destroy();
			setExitStatus(ExitStatus.INTERRUPTED);

		} catch (IOException e) {
			// failed to execute the process, nothing special to do
			ex = e;
			e.printStackTrace();
			setExitStatus(ExitStatus.ERROR);
		}
		finally
		{
			timer.cancel();     // If the process returns within the timeout period, we have to stop the interrupter
			// so that it does not unexpectedly interrupt some other code later.

			Thread.interrupted();   // We need to clear the interrupt flag on the current thread just in case
			// interrupter executed after waitFor had already returned but before timer.cancel
			// took effect.
			//
			// Oh, and there's also Sun bug 6420270 to worry about here.
			
			// notify the controller that this thread is done (no matter what happened) 
			synchronized(controller) {
				log.trace("Thread (" + getProcessName() + ") informs [" + resultType + "] and signals to main thread");
				controller.inform(resultType);
				controller.notify();
			}
			
			log.trace("Thread (" + getProcessName() + ") ended");
		}		
	}

	public synchronized ExitStatus getExitStatus() {
		return exitStatus;
	}
	
	private synchronized void setExitStatus(ExitStatus exitStatus) {
		this.exitStatus = exitStatus;
	}

	public synchronized String getProcessName() {
		return processName;
	}

	private void setResultType(ResultType recognizedResult) {
		resultType = recognizedResult;
	}

	public ResultType getResultType() {
		return resultType;
	}		

	public String getOutputStream() {
		return outputBuffer.toString();
	}
	
	class InterruptTimerTask extends TimerTask {

		private Thread thread;


		public InterruptTimerTask(Thread t)
		{
			this.thread = t;
		}

		public void run()
		{
			log.trace("Timeout expired!");
			thread.interrupt();
		}
	}

	class StreamGobbler extends Thread {
		InputStream is;
		String type;
		StringBuffer os;

		StreamGobbler(InputStream is, String type)
		{
			this(is, type, null);
		}

		StreamGobbler(InputStream is, String type, StringBuffer os)
		{
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
					if (os != null) {
						os.append(line);
					}
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();  
			}
		}
	}

}