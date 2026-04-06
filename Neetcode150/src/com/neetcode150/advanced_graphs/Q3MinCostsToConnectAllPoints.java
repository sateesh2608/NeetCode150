package com.neetcode150.advanced_graphs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q3MinCostsToConnectAllPoints {

    /*
     * Helper class to store:
     * - node index
     * - cost to connect to that node
     */
    static class Point {
        int node;
        int cost;

        public Point(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {

        // Example input: points in 2D plane
        int[][] points = {
                {0, 0},
                {2, 2},
                {3, 10},
                {5, 2},
                {7, 0}
        };

        int minCost = minCostConnectPoints(points);

        System.out.println("Minimum cost to connect all points: " + minCost);
        // Expected Output: 20
    }

    /*
     * Function to calculate Minimum Cost to Connect All Points
     * Using Prim's Algorithm (Minimum Spanning Tree)
     */
    private static int minCostConnectPoints(int[][] points) {

        int n = points.length;

        // To track which nodes are already included in MST
        boolean[] visited = new boolean[n];

        // Min-heap (PriorityQueue) ordered by cost
        Queue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        // Start from node 0 with cost 0
        pq.add(new Point(0, 0));

        int minCost = 0;          // Stores total MST cost
        int pointsConnected = 0;  // Count of nodes added to MST

        // Continue until all points are connected
        while (pointsConnected < n) {

            // Extract the node with minimum cost
            Point curr = pq.poll();

            // If already visited, skip (avoid cycles)
            if (visited[curr.node]) continue;

            // Mark node as visited (add to MST)
            visited[curr.node] = true;

            // Add cost of connecting this node
            minCost += curr.cost;

            pointsConnected++;

            // Try connecting this node to all unvisited nodes
            for (int i = 0; i < n; i++) {

                if (!visited[i]) {

                    // Calculate Manhattan distance
                    int distance = Math.abs(points[curr.node][0] - points[i][0])
                            + Math.abs(points[curr.node][1] - points[i][1]);

                    // Push into min-heap
                    pq.offer(new Point(i, distance));
                }
            }
        }

        return minCost;
    }
}