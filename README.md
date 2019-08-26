### Final project for `MET CS 526: Data Structures and Algorithms`
#### Graph Algorithm

The program reads two input files: (1) graph_input.txt and (2) direct_distance.txt

- The graph_input.txt file contains a textual representation of a graph
<br><br>
![alt text](https://github.com/mhbrickley/Graph-Algorithm/blob/master/graph.PNG)

- The direct_ distance.txt file contains the direct distance from each node to the destination node Z
  - The direct distance from a node n to node Z is the distance measured along an imaginary straight line (or geographically straight line)
from node n to node Z 

The program implements two heuristic algorithms which find the shortest path from a given input node to node Z
  - In a shortest path, a node may appear at most once (i.e., a node cannot appear twice or more in a path)
  - Both algorithms start with a given input node and iteratively determine the next node in a shortest path
    - In determining which node to choose as the next node, they use different heuristics
<br><br>
![alt text](https://github.com/mhbrickley/Graph-Algorithm/blob/master/cmd.png)
