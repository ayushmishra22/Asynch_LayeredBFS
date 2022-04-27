/*
 * The main structure of the program
*/
import java.util.*;

public class main {
	public static void main(String args[]) throws InterruptedException{
		fileIO f = new fileIO(args[0]);
		
		// Testing the Num nodes
		System.out.println(f.numProcesses);
		
		// Testing starting node
		System.out.println(f.nodeToCreateTreeAt);
		
		// Testing the Adjacency 
		for(int i = 0; i < f.numProcesses; i++) {
			for(int j = 0; j < f.numProcesses; j++) {
				//System.out.print(f.adjInformation[i][j] + " ");
			//System.out.println();
		}
		}
		Master master = new Master(f.numProcesses, f.adjInformation, f.nodeToCreateTreeAt);
		
		master.start();
		
	}
}
