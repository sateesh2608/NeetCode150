package com.dsa.general_theory;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	int V; // number of vertices
	private LinkedList<Integer>[] adj;

	public Graph() {

	}
	
	// Constructor
	@SuppressWarnings("unchecked")
	Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	// Add directed edge
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	// BFS traversal starting from all nodes (handles disconnected graphs)
	void bfsAll() {
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i])
				bfs(i, visited);
		}
	}

	private void bfs(int start, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			System.out.print(curr + " ");

			for (int neighbor : adj[curr]) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
				}
			}
		}
	}

	// DFS traversal starting from all nodes (handles disconnected graphs)
	void dfsAll() {
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i])
				dfs(i, visited);
		}
	}

	private void dfs(int curr, boolean[] visited) {
		visited[curr] = true;
		System.out.print(curr + " ");

		for (int neighbor : adj[curr]) {
			if (!visited[neighbor])
				dfs(neighbor, visited);
		}
	}

	private void printAllPaths(int curr, boolean[] visitedArray, int target, String path) {

		path += curr+" ";
		visitedArray[curr] = true;

		// base case
		if(curr == target) {			
			System.out.println(path);
		}
		
		for (int neighbour : adj[curr]) {
			if(!visitedArray[neighbour]) {
				printAllPaths(neighbour,visitedArray,target,path);
			}
		}
		visitedArray[curr] = false;
	}

	// Test example
	public static void main(String[] args) {
		Graph g = new Graph(8);

		// Add edges (strange order)
		g.addEdge(0, 5);
		g.addEdge(0, 3);
		g.addEdge(0, 1);
		g.addEdge(5, 7);
		g.addEdge(5, 2);
		g.addEdge(3, 6);
		g.addEdge(1, 4);
		g.addEdge(7, 4);
		g.addEdge(2, 6);

		System.out.println("BFS traversal (all components):");
		g.bfsAll();

		System.out.println("\nDFS traversal (all components):");
		g.dfsAll();

		int source = 0;
		int target = 4;
		boolean[] visitedArray = new boolean[g.V];
		System.out.println("\nAll paths from " + source + " and " + target + " are: ");
		g.printAllPaths(source, visitedArray, target,"");
	}

}
