package com.neetcode150.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q3CloneGraph {

	
	static class Node {
	    public int val;
	    public List<Node> neighbors;
	    public Node() {
	        val = 0;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int _val) {
	        val = _val;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int _val, ArrayList<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	}
	
	
	public static void main(String[] args) {
		
		// Create sample graph
	    // Graph:
	    // 1 -- 2
	    // |    |
	    // 4 -- 3

	    Node node1 = new Node(1);
	    Node node2 = new Node(2);
	    Node node3 = new Node(3);
	    Node node4 = new Node(4);

	    node1.neighbors.add(node2);
	    node1.neighbors.add(node4);

	    node2.neighbors.add(node1);
	    node2.neighbors.add(node3);

	    node3.neighbors.add(node2);
	    node3.neighbors.add(node4);

	    node4.neighbors.add(node1);
	    node4.neighbors.add(node3);

	    // Clone the graph
	    Node clonedGraph = cloneGraph(node1);

	    // Print original and cloned graph to verify
	    System.out.println("Original Graph:");
	    printGraph(node1, new HashSet<>());

	    System.out.println("\nCloned Graph:");
	    printGraph(clonedGraph, new HashSet<>());
	}
	
	
	
	private static Node cloneGraph(Node node1) {

		if(node1 == null) return null;
		
		HashMap<Node,Node> map = new HashMap<>();
		return cloneutil(node1,map);
	}



	private static Node cloneutil(Node node, HashMap<Node, Node> map) {
		
		//base case
		if (map.containsKey(node)) {
	        return map.get(node);
	    }

		// new node adding logic without neighbors
	    Node newNode = new Node(node.val);
	    //maintain map before recursion as we avoid infinite loop in scenarios like 1->2->1
	    map.put(node, newNode);

	    // adding neighbors logic 
	    for (Node neighbor : node.neighbors) {
	        newNode.neighbors.add(cloneutil(neighbor, map));
	    }

	    return newNode;
	}




	public static void printGraph(Node node, Set<Integer> visited) {
	    if (node == null || visited.contains(node.val)) return;
	    visited.add(node.val);

	    System.out.print("Node " + node.val + " -> Neighbors: ");
	    for (Node neighbor : node.neighbors) {
	        System.out.print(neighbor.val + " ");
	    }
	    System.out.println();

	    for (Node neighbor : node.neighbors) {
	        printGraph(neighbor, visited);
	    }
	}
}
