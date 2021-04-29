import java.util.*;

class Graph { // Graph class for creating and performing DFS on created graph.

    static ArrayList<ArrayList<Integer>> gr; // Adjacency List type graph representation.
    static Boolean[] visited = new Boolean[1000000]; // Array of Boolean type for tracking down the visited vertices.
    int[][] matrix; // Adjacency matrix type graph representation.
    int n; // Number of vertices in graph.

    Graph(int N) { // Constructor for initializing Adjacency List,
        n = N + 1;
        gr = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i <= n; ++i) // Marking value to false for all the vertices initially.
            visited[i] = false;
        for (int i = 0; i < n; ++i)
            gr.add(new ArrayList<Integer>());
    }

    Graph(int N, Boolean isMatrix) { // Constructor for initializing Adjacency Matrix.
        n = N;
        matrix = new int[N + 1][N + 1];
        for (int i = 0; i <= n; ++i) // Marking value to false for all the vertices initially.
            visited[i] = false;
    }

    void createEdge(int a, int b, int op) { // Function for creating an edge in Adjacency List.
        // It takes three arguments, two arguments u and v, vertices having edge between.
        // third argument for directed or undirected edge, 0 -> for undirected edges and 1 -> for directed edges.
        gr.get(a).add(b);
        if (op == 0)
            gr.get(b).add(a);
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

    void printMatrixGraph() { // Function for printing Adjacency Matrix.
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

    void printDFS(int node) { // A recursive function for performing DFS on Adjacency List from a give source vertex.
        visited[node] = true; // marking the currently being processed vertex as visited.
        System.out.print(node + " ");
        for (int i = 0; i < gr.get(node).size(); ++i) { // Getting all the neighbour vertices of the vertex currently being processed from Adjacency List.
            if (!visited[gr.get(node).get(i)]) { // If vertex is not visited before process the vertex.
                printDFS(gr.get(node).get(i));
            }
        }
    }

    void matrixPrintDFS(int node) { // A recursive function for performing DFS on Adjacency Matrix from a give source vertex.
        visited[node] = true; // marking the currently being processed vertex as visited.
        System.out.print(node + " ");
        for (int i = 0; i <= n; ++i) { // Getting all the neighbour vertices of the vertex currently being processed from Adjacency Matrix.
            if ((!visited[i]) && matrix[node][i] == 1) { // If vertex is not visited before process the vertex.
                matrixPrintDFS(i);
            }
        }
    }

}

public class GraphDFS {

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
        gra.printDFS(0); // DFS starting from 0th node.
        System.out.println("\n");

        Graph matrixGraph = new Graph(v, true); // Creating Graph object of Graph class.
        matrixGraph.matrixCreateEdge(0, 1, 0); // Creating edges.
        matrixGraph.matrixCreateEdge(0, 4, 0);
        matrixGraph.matrixCreateEdge(1, 3, 0);
        matrixGraph.matrixCreateEdge(1, 2, 0);
        matrixGraph.matrixCreateEdge(1, 4, 0);
        matrixGraph.matrixCreateEdge(3, 4, 0);
        matrixGraph.matrixCreateEdge(2, 3, 0);
        matrixGraph.printMatrixGraph(); // Printing Adjacency Matrix.
        matrixGraph.matrixPrintDFS(0); // DFS starting from 0th node.
    }

}
