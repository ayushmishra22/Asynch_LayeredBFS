Team Members:
1. Ayush Mishra (axm200011)
2. 
3.


Run the program:

cd  /path-to/src
javac src/*.java
java src/Main <input_file_name>
Ex: java src/Main inputdata.txt


Input and Output:
Input 1:
8
1
1 1 1 0 0 0 0 0
1 1 0 0 1 0 0 0
1 0 1 0 0 1 0 0
0 0 0 1 0 1 0 0
0 1 0 0 1 0 0 1
0 0 1 1 0 1 0 1
0 0 0 0 0 0 1 1
0 0 0 0 1 1 1 1
Output:
Index:1 Children:3 2 
Index:2 Children:5 
Index:3 Children:6 
Index:4 Children:
Index:5 Children:8 
Index:6 Children:4 
Index:7 Children:
Index:8 Children:7 
Messages count=63

Input 2:
5
3
1 1 1 0 0
1 1 1 0 0
1 1 1 1 1 
0 0 1 1 1
0 0 1 1 1
Output:
Index:1 Children:
Index:2 Children:
Index:3 Children:4 5 1 2 
Index:4 Children:
Index:5 Children:
Messages count=28


Contributions:
Ayush Mishra:
Implementation of Master, Process, Message, Logger classes
Implementation of Transmission delay
Implementation of LayeredBFS Algorithm
Communication between processes
Termination of algorithm
Printing BFS Tree and calculating total messages
Debugging
Testing