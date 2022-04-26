/*
 * This class handles the input and output of the file and returns values to the algorithm
 *	First line from the file is a single integer - the total number of processes in the system
 *	Second line is the index (1...n) of the root of the BFS to be created
 *	Line 3 to n+2 represents the connectivity matrix of n 0's and 1's
 *		Line 3 + i represents the neighbor of process i: the jth component of
 *		this line is a 1 if i and j are neighbors and is 0 of i and j if not 
 *
 * I also partially believe the example given is incorrect as there is no way 
 * 	That the first line can stand without a neighbor connection to 3, as there
 * 	is a neighbor connection to 1 from 3 -Erik
 */

import java.io.File;						// The Java File Class
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileIO {
	// Variables
	String filename = "example.txt";		// Name of the filename
	int numProcesses = 0;					// The number of processes
	int nodeToCreateTreeAt = 0;				// The node 1...n to make the tree at
	int adjInformation[][];					// How the nodes are connected 1...n
	
	// Constructors
	public fileIO(String fileName) {
		filename = fileName;				// Set the new file name
		gatherFileInformation(filename);		// Set the information
	}
	
	// functions
	int[] breakStringIntoArray (String s, int numProcesses){
		// A way to read in a string and parse the information so that it can be applied to an array 
		// in a bitmap format
		int []parsedArray = new int[numProcesses];	// An array that holds the integers
		String[] splitString = s.split("\\s+");		// Split by the string
		
		for(int i = 0; i < splitString.length; i++) {
			//System.out.print(splitString[i]);
			parsedArray[i] = Integer.parseInt(splitString[i]);
			//System.out.print(parsedArray[i] + " ");
		}
		//System.out.println();
		
		return parsedArray;
	}
	
	void gatherFileInformation(String fileName){
		// a function to parse the information from the file
		String s = "", data = "";			// information to return / data to be held temporarily
		int inFileCounter = 0;				// Know which line we are at in the file
		File fileToCheck = new File(fileName);	// Create a new file to check

		// We need to check that the file works and then keep the raw data
		try {
			Scanner reader = new Scanner(fileToCheck);
			if(fileToCheck.exists()) {		// if the file exists, then we can read it
				while (reader.hasNextLine()) {
					data = reader.nextLine(); 	// read the next line and store it
					s += data;					// Add to sting output
					
					switch(inFileCounter) {
					case 0:					// Used to determine the number of processes in program
						numProcesses = Integer.parseInt(data);	
						adjInformation = new int[numProcesses][];
						break;
					case 1: 				// Used for which node we are building the tree from
						nodeToCreateTreeAt = Integer.parseInt(data);
						break;
					default:				// Used for the rest of the lines for adjacency information
						adjInformation[inFileCounter - 2] = breakStringIntoArray(data, numProcesses);
						break;
					}
				inFileCounter++; 			// Increment the file line 
				}
				reader.close();				// Close the reader
				
			} else 
				s = "Warning: File could not be loaded!";
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// At this point in the function we should have the entire text in s
		//System.out.println(s);
	}
}
