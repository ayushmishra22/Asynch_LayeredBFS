import java.util.*;
public class Master extends Thread{
	//This class is used to create, start and terminate child processes
	int numOfChildProcesses;
	int[][] adjInfo;
	static int root;
	Process[] processes;
	volatile static int clock = 0;
	static Random generator = new Random(123456789);
	static boolean terminate = false;
	static int[][] BFStree;
	static int msg_count = 0;
	
	public Master(int n, int[][] adjInformation, int RootNode) {
		this.numOfChildProcesses = n;
		this.BFStree = new int[n][];
		root = RootNode;
		adjInfo = new int[numOfChildProcesses][numOfChildProcesses];
		processes = new Process[numOfChildProcesses];
		int i=0, j=0;
		for(i=0; i<numOfChildProcesses; i++) {
			for(j=0; j<numOfChildProcesses; j++) {
				this.adjInfo[i][j] = adjInformation[i][j];
				System.out.print(adjInfo[i][j] + " ");
			}
		System.out.println();
		}
	}
	
	public static synchronized int generateRandom() {
		int w = generator.nextInt(12) + 1;
		Logger.Level.log(Logger.Level.TRACE,"Wait time:"+w);
		msg_count += 1;
		return w;
	}
	
	public void run() {
		Logger.Level.log(Logger.Level.TRACE, "This is master thread");
		int i = 0;
		for(i=0; i<numOfChildProcesses; i++) {
			processes[i] = new Process(i+1);
		}
		for(i=0; i<numOfChildProcesses; i++) {
			int j=0;
			ArrayList<Process> tmp = new ArrayList<Process>();
			for(j=0; j<numOfChildProcesses; j++) {
				if(i == j) {
					continue;
				}
				if(adjInfo[i][j] == 1) {
					tmp.add(processes[j]);
				}
				processes[i].neighbors = tmp;
			}
		}
		for(i=0; i<numOfChildProcesses; i++) {
			processes[i].start();
		}
		while(true) {
		for(i=0; i<this.numOfChildProcesses; i++) {
			if(processes[i].isInterrupted() == true) {
				continue;
				}
			if(processes[i].ready_round != clock+1) {
				while(processes[i].ready_round != clock+1) {}
			} 
		}
		clock += 1;
		Logger.Level.log(Logger.Level.TRACE,"-----------Round: "+clock+"---------------");
		int count = 0;
		for(int l=0; l< this.numOfChildProcesses; l++) {
			if(this.processes[l].terminate == true) {
				count += 1;
			}
		}
		if(count == this.numOfChildProcesses  || clock == 100) {
			for(int m=0; m<this.numOfChildProcesses; m++) {
				System.out.print("Index:"+(m+1)+" Children:");
				if(BFStree[m].length == 0) {
					System.out.println();
					continue;
				}
				for(int n=0; n<BFStree[m].length; n++) {
					System.out.print(BFStree[m][n]+" ");
				}
				System.out.println();
			}
			System.out.println("Messages count="+Master.msg_count);
			Master.terminate = true;
		}
		if(terminate) { // Thread termination condition.
			int j = 0;
			for(j=0; j < this.numOfChildProcesses; j++) {
				this.processes[j].interrupt();
				try {
					processes[j].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		}
		Logger.Level.log(Logger.Level.TRACE,"All threads have terminated. Master thread exits");
	}
}

