import java.util.*;

class Graph { // Graph class for creating and performing BFS on created graph.

    class customPair { // customPair class for storing integers.
        // nodeVal = value of vertex,
        // nodeLevel = Level of vertex with respect to the source vertex.
        int nodeVal, nodeLevel;
        customPair(int x, int y) { // Constructor for customPair class.
            nodeVal = x;
            nodeLevel = y;
        }
    }

    static ArrayList<ArrayList<Integer>> gr; // Adjacency List type graph representation.
    int[][] matrix; // Adjacency matrix type graph representation.
    int n; // Number of vertices in graph.

    Graph(int N) { // Constructor for initializing Adjacency List,
        n = N + 1;
        gr = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; ++i) {
            gr.add(new ArrayList<Integer>());
        }
    }

    Graph(int N, Boolean isMatrix) { // Constructor for initializing Adjacency Matrix.
        n = N;
        matrix = new int[N + 1][N + 1];
    }

    void createEdge(int u, int v, int op) { // Function for creating an edge in Adjacency List.
        // It takes three arguments, two arguments u and v, vertices having edge between.
        // third argument for directed or undirected edge, 0 -> for undirected edges and 1 -> for directed edges.
        gr.get(u).add(v);
        if (op == 0)
            gr.get(v).add(u);
    }

    void matrixCreateEdge(int u, int v, int op) { // Function for creating an edge in Adjacency Matrix;
        // It takes three arguments, two arguments u and v, vertices having edge between.
        // third argument for directed or undirected edge, 0 -> for undirected edges and 1 -> for directed edges.
        matrix[u][v] = 1;
        if (op == 0)
            matrix[v][u] = 1;
    }

    void printAdjList() { // Function for printing Adjacency List.
        for (int i = 0; i < gr.size(); ++i) {
            System.out.print(i + " : ");
            for (int j = 0; j < gr.get(i).size(); ++j) {
                System.out.print(gr.get(i).get(j) + " -> ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void printMatGraph() { // Function for printing Adjacency Matrix.
        for (int i = 0; i <= n; ++i) {
            if (i == 0) {
                System.out.print("  " + i + " ");
            } else
                System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i <= n; ++i) {
            System.out.print(i + " ");
            for (int j = 0; j <= n; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void printBFS(int source) { // Function for performing BFS from a given source on Adjacency List.
        Queue<customPair> referQueue = new LinkedList<>(); // Queue for maintaing sequence in which vertices are processed.
        Boolean[] visited = new Boolean[n + 1]; // Boolean type of array for tracking down the visited vertices.
        for (int i = 0; i <= n; ++i) // Marking value to false for all the vertices initially.
            visited[i] = false;
        int level = 0; // Level variable depicting the current level of vertices.
        customPair temp = new customPair(source, level); // creating a vriable of customPair type initializing with values of source vertex.
        visited[source] = true; // marking source vertex visited.
        referQueue.add(temp); // Pushing source vertex in queue.
        System.out.print("Level " + 0 + " : ");
        while (!referQueue.isEmpty()) {
            temp = referQueue.peek(); // Getting front element from the queue.
            referQueue.remove();
            int currNodeVal = temp.nodeVal, currNodeLevel = temp.nodeLevel;
            visited[currNodeVal] = true; // marking vertex as visited.
            if (currNodeLevel != level) { // If level changes then start printing new Level.
                System.out.println();
                System.out.print("Level " + currNodeLevel + " : " + currNodeVal + ", ");
                level = currNodeLevel;
            } else {
                System.out.print(currNodeVal + ", ");
            }
            for (int i = 0; i < gr.get(currNodeVal).size(); ++i) { // Getting all the neighbour vertices of the vertex currently being processed.
                int neighbour = gr.get(currNodeVal).get(i);
                if (!visited[neighbour]) { // if neighbour vertex is not visited before mark it as visited and push to queue.
                    visited[neighbour] = true;
                    customPair newNode = new customPair(neighbour, currNodeLevel + 1);
                    referQueue.add(newNode);
                }
            }
        }
        System.out.println("\n\n");
    }

    void printMatrixBFS(int source) { // Function for performing BFS from a given source on Adjacency Matrix.
        Queue<customPair> referQueue = new LinkedList<>(); // Queue for maintaing sequence in which vertices are processed.
        Boolean[] visited = new Boolean[n + 1]; // Boolean type of array for tracking down the visited vertices.
        for (int i = 0; i <= n; ++i) // Setting value to false for all the vertices initially.
            visited[i] = false;
        int level = 0; // Level variable depicting the current level of vertices.
        customPair temp = new customPair(source, level); // creating a vriable of customPair type initializing with values of source vertex.
        visited[source] = true; // marking source vertex visited.
        referQueue.add(temp); // Pushing source vertex in queue.
        System.out.print("Level " + 0 + " : ");
        while (!referQueue.isEmpty()) {
            temp = referQueue.peek(); // Getting front element from the queue.
            referQueue.remove();
            int currNodeVal = temp.nodeVal, currNodeLevel = temp.nodeLevel;
            visited[currNodeVal] = true; // marking vertex as visited.
            if (currNodeLevel != level) { // If level changes then start printing new Level
                System.out.println();
                System.out.print("Level " + currNodeLevel + " : " + currNodeVal + ", ");
                level = currNodeLevel;
            } else {
                System.out.print(currNodeVal + ", ");
            }
            for (int i = 0; i <= n; ++i) { // Getting all the neighbour vertices of the vertex currently being processed.
                if (matrix[currNodeVal][i] == 1 && (!visited[i])) { // if neighbour vertex is not visited before mark it as visited and push to queue.
                    visited[i] = true;
                    customPair newNode = new customPair(i, currNodeLevel + 1);
                    referQueue.add(newNode);
                }
            }
        }
        System.out.println();
    }

}

class GraphBFS {

    public static void main(String[] args) {
        int v = 5;
        Graph gra = new Graph(v); // Creating Graph object of Graph class.
        gra.createEdge(0, 1, 0); // Creating edges.
        gra.createEdge(0, 4, 0);
        gra.createEdge(1, 2, 0);
        gra.createEdge(1, 3, 0);
        gra.createEdge(1, 4, 0);
        gra.createEdge(2, 3, 0);
        gra.createEdge(3, 4, 0);
        gra.printAdjList(); // Printing Adjacency List.
        gra.printBFS(0); // BFS starting from 0th node.

        Graph matGraph = new Graph(v, true); // Creating Graph object of Graph class.
        matGraph.matrixCreateEdge(0, 1, 0); // Creating edges.
        matGraph.matrixCreateEdge(0, 4, 0);
        matGraph.matrixCreateEdge(1, 2, 0);
        matGraph.matrixCreateEdge(1, 3, 1);
        matGraph.matrixCreateEdge(1, 4, 0);
        matGraph.matrixCreateEdge(2, 3, 0);
        matGraph.matrixCreateEdge(3, 4, 0);
        matGraph.printMatGraph(); // Printing Adjacency Matrix.
        matGraph.printMatrixBFS(0); // BFS starting from 0th node.
    }

}
