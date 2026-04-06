package com.neetcode150.advanced_graphs;

import java.util.*;

public class Q2ReconstructItinerary {

	public static void main(String[] args) {

        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC","LHR"));
        tickets.add(Arrays.asList("JFK","MUC"));
        tickets.add(Arrays.asList("SFO","SJC"));
        tickets.add(Arrays.asList("LHR","SFO"));

        System.out.println("Recursive: " + findItinerary(tickets));
        System.out.println("Iterative: " + findItineraryIterative(tickets));
    }

    /* =========================================================
       RECURSIVE VERSION (Hierholzer)
       ========================================================= */
    /**
     * INTUITION:
     *
     * We must use every ticket exactly once.
     * That means: traverse every edge exactly once.
     *
     * This is Eulerian Path problem.
     *
     * WHY POSTORDER ADD?
     * Because we add node after exhausting all outgoing edges.
     * That ensures correct path construction.
     */
    public static List<String> findItinerary(List<List<String>> tickets) {

        // Step 1: Build graph
    	Map<String, PriorityQueue<String>> graph = buildGraph(tickets);

        List<String> result = new LinkedList<>();

        // Step 2: DFS from JFK
        dfs("JFK", graph, result);

        return result;
    }

    /**
     * DFS using Hierholzer's algorithm
     *
     * WHY PriorityQueue?
     * To ensure lexicographically smallest order.
     *
     * WHY add at front?
     * Because we are building path in reverse (postorder).
     */
    private static void dfs(String airport,
                            Map<String, PriorityQueue<String>> graph,
                            List<String> result) {

        while (graph.containsKey(airport) &&
               !graph.get(airport).isEmpty()) {

            String next = graph.get(airport).poll();
            dfs(next, graph, result);
        }

        // Add after exploring all edges
        result.add(0, airport);
    }

    /* =========================================================
       ITERATIVE STACK VERSION (Preferred in interviews)
       ========================================================= */
    public static List<String> findItineraryIterative(List<List<String>> tickets) {

        Map<String, PriorityQueue<String>> graph = buildGraph(tickets);

        Stack<String> stack = new Stack<>();
        List<String> result = new LinkedList<>();

        stack.push("JFK");

        /*
         * INTUITION:
         * Keep going deeper while edges exist.
         * When stuck, add to result and backtrack.
         */
        while (!stack.isEmpty()) {

            String current = stack.peek();

            if (graph.containsKey(current)
                    && !graph.get(current).isEmpty()) {

                // Still have outgoing edges → go deeper
                stack.push(graph.get(current).poll());

            } else {

                // No outgoing edges → finalize this node
                result.add(0, stack.pop());
            }
        }

        return result;
    }

    /* =========================================================
       Helper to build graph
       ========================================================= */
    private static Map<String, PriorityQueue<String>> buildGraph(
            List<List<String>> tickets) {

        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {

            String from = ticket.get(0);
            String to = ticket.get(1);

            graph
                .computeIfAbsent(from, x -> new PriorityQueue<>())
                .offer(to);
        }

        return graph;
    }
}