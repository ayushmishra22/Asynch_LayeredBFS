# Asynchronous Systems - LayeredBFS Algorithm Implementation

## Description
Implementation of LayeredBFS Algorithm in Asynchronous network. The message transmission time for each link for each message is to be randomly chosen using a uniform distribution in the range 1 to 12 “time units.” All links are bidirectional and FIFO. 

## Input Description:
The first line has a single integer and it represents the total number of processes in the system.
The second third line is the index (1..n) of the root of the BFS tree to be created.
Lines 3 to n+2 represent the connectivity matrix as a set of n 0’s and 1.s: Line 3+i represents the neighbors of process i: the jth component of this line is a 1 if i and j are neighbors and is 0 of i and j are not neighbors.

## Output Description:
1. When the tree has been built, each process should output, on the screen, one line of data containing its index and its list of children (as indices, again) separated by one space.
2. The total number of messages sent for the run and output the result.

## Project Structure
```
proj2_layeredbfs
    |--src
        |-- FileIO.java
        |-- Logger.java
        |-- main.java
        |-- Master.java
        |-- Message.java
        |-- Process.java
        |-- inputdata1.txt
        |-- inputdata2.txt
        |-- inputdata3.txt
    |-- README.md
```

## Run Commands
```
cd  /path-to/proj2_layeredbfs
mkdir build
javac -sourcepath src -d build src/*.java 
java -cp build main src/main <input_file_name>
Ex: java -cp build main ./src/inputdata.txt 
Ex: java -cp build main ./src/inputdata1.txt
Ex: java -cp build main ./src/inputdata2.txt
Ex: java -cp build main /user/xxxx/proj2_layeredbfs/src/inputdata.txt
```


## Input & Output
```
Output with inputdata.txt:
8
4
1 1 1 0 0 0 0 0
1 1 0 0 1 0 0 0
1 0 1 0 0 1 0 0
0 0 0 1 0 1 0 0
0 1 0 0 1 0 0 1
0 0 1 1 0 1 0 1
0 0 0 0 0 0 1 1
0 0 0 0 1 1 1 1
Index:1 Children:
Index:2 Children:
Index:3 Children:1 
Index:4 Children:6 
Index:5 Children:2 
Index:6 Children:8 3 
Index:7 Children:
Index:8 Children:5 7 
Messages count=59
```
```
Output with inputdata1.txt:
7
5
1 1 0 0 0 1 0 
1 1 1 0 1 0 1 
0 1 1 1 1 1 0 
0 0 1 1 0 0 1 
0 1 1 0 1 0 0 
1 0 1 0 0 1 0 
0 1 0 1 0 0 1 
Index:1 Children:
Index:2 Children:1 7 
Index:3 Children:4 6 
Index:4 Children:
Index:5 Children:3 2 
Index:6 Children:
Index:7 Children:
Messages count=46
```
```
9
5
1 0 1 0 1 0 1 0 0 
0 1 0 0 0 0 0 0 1 
1 0 1 0 0 1 0 0 0 
0 0 0 1 0 0 0 1 1 
1 0 0 0 1 1 0 0 0 
0 0 1 0 1 1 0 0 1 
1 0 0 0 0 0 1 0 0 
0 0 0 1 0 0 0 1 0 
0 1 0 1 0 1 0 0 1 
Index:1 Children:7 
Index:2 Children:
Index:3 Children:
Index:4 Children:8 
Index:5 Children:1 6 
Index:6 Children:3 9 
Index:7 Children:
Index:8 Children:
Index:9 Children:4 2 
Messages count=72
```
