package com.neetcode150.advanced_graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q6CheapestFlightsWithinKStops {

	public static void main(String[] args) {
        // Example input: flights represented as [source, destination, cost]
        int[][] flights = {
            {0, 1, 100},
            {1, 2, 100},
            {0, 2, 500}
        };

        int n = 3;      // number of cities
        int src = 0;    // starting city
        int dst = 2;    // destination city
        int K = 1;      // maximum number of stops allowed

        int cheapest = findCheapestPrice(n, flights, src, dst, K);
        System.out.println("Cheapest price: " + cheapest);
        // Expected output: 200
    }

    /**
     * Finds the cheapest price from src to dst with at most k stops.
     *
     * Intuition:
     * - We want the minimum cost path from src to dst, but with a constraint on stops.
     * - Standard Dijkstra works for shortest paths, but we need to track number of stops.
     * - Use a PriorityQueue to always expand the **cheapest accumulated cost first**.
     * - Each element in the queue stores [currentCity, totalCostSoFar, stopsSoFar].
     * - We ignore paths exceeding K stops.
     */
    private static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        // 1️⃣ Build the graph as adjacency list: graph[u] contains [v, price]
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] f : flights) {
            int u = f[0], v = f[1], w = f[2];
            graph.get(u).add(new int[] {v, w});
        }

        // PriorityQueue to explore cheapest cost first
        // Each element: [currentCity, totalCostSoFar, stopsSoFar]
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        pq.add(new int[] {src, 0, 0}); // start from source

        // 3️⃣ Dijkstra-like traversal with stop constraint
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int city = curr[0];  // current city
            int cost = curr[1];  // total cost so far
            int stops = curr[2]; // stops used so far

            // 3a️⃣ If we reached the destination, return the cost
            if (city == dst) return cost;

            // 3b️⃣ If we exceeded the maximum allowed stops, skip this path
            if (stops > K) continue;

            // 3c️⃣ Explore neighbors
            for (int[] edge : graph.get(city)) {
                int nextCity = edge[0];
                int price = edge[1];
                pq.offer(new int[] {nextCity, cost + price, stops + 1});
            }
        }

        // 4️⃣ If destination not reachable within K stops
        return -1;
    }
}