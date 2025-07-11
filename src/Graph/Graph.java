package Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//This class implements graph data structure by using adjacency matrix representation

public class Graph {
	private ArrayList<String> vertices; // to keep vertex names
	private int[][] adjacency; // to keep edges
	private int size;

	public Graph(int size) {
		vertices = new ArrayList<String>();
		adjacency = new int[size][size];
		this.size = size;
	}

	public void addEdge(String source, String destination, int weight) {//O(1)

		int sourceIndex;
		int destIndex;
		if (!vertices.contains(source))
			vertices.add(source);

		if (!vertices.contains(destination))
			vertices.add(destination);

		sourceIndex = vertices.indexOf(source);
		destIndex = vertices.indexOf(destination);

		if (size > sourceIndex && size > destIndex) {
			adjacency[sourceIndex][destIndex] = weight;
		}
		

	}

	public int size() {
		return this.size;
	}//O(1)

	public int[][] getAdjacency() {
		return adjacency;
	}//O(1)

	public ArrayList<String> getVertices() {
		return vertices;
	}//O(1)

	public void print() {//O(n^2)
		for (String v : vertices) {
			System.out.print("\t(" + v + ")");
		}
		System.out.println();
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print("(" + vertices.get(i) + ")\t");
			for (int j = 0; j < adjacency.length; j++) {
				System.out.print(adjacency[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("Edges");
		int edge_count = 0;
		int total_weight = 0;
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				if (adjacency[i][j] > 0) {
					System.out.println(vertices.get(i) + "-" + vertices.get(j) + ":" + adjacency[i][j]);
					edge_count++;
					total_weight += adjacency[i][j];
				}
			}
		}
		System.out.println("Total " + edge_count + " edges.");
		System.out.println("Total weight is " + total_weight);
		System.out.println();
	}

	public void BFS(String searched) {//O(V + E)
		Queue<Integer> queue = new LinkedList<>();
		int root = vertices.indexOf(searched);

		queue.add(root);
		int[] visited = new int[size];
		visited[root] = 1;

		System.out.println("Breadthfirst search for " + searched + " vertex:");
		while (!queue.isEmpty()) {
			int current_vertex = queue.poll(); // the top element is selected and removed from queue
			System.out.print(vertices.get(current_vertex) + " ");

			int v;
			while ((v = unvisitedNeighbor(current_vertex, visited)) != -1) {
				queue.add(v);
				visited[v] = 1;
			}
		}
	}

	public int unvisitedNeighbor(int index, int[] visited) {//O(n)
		for (int i = 0; i < adjacency.length; i++) {
			if (adjacency[index][i] != 0 && visited[i] == 0)
				return i;
		}
		return -1;
	}

	public void DFS(String searched) {//O(V + E)
		int root = vertices.indexOf(searched);
		boolean[] visited = new boolean[size];

		System.out.println("Depthfirst search for " + searched + " vertex:");

		solve_DFS(root, visited);
	}

	public void solve_DFS(int index, boolean[] visited) {//O(V + E)
		if (!visited[index]) {
			visited[index] = true; // Mark node as "visited"
			System.out.print(vertices.get(index) + " ");

			for (int j = 0; j < adjacency[index].length; j++) {
				if (adjacency[index][j] != 0 && !visited[j]) {
					solve_DFS(j, visited); // Visit node
				}
			}
		}
	}

}
