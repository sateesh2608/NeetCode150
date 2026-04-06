package com.neetcode150.advanced_graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Q1NetworkDelayTime {

    public static void main(String[] args) {

        int[][] times = {
                {2,1,1},
                {2,3,1},
                {3,4,1}
        };

        int n = 4;
        int k = 2;

        System.out.println(networkDelayTime(times, n, k));
        // Expected: 2
    }

    /**
     * INTUITION:
     * We want shortest time from node k to all nodes.
     *
     * This is Single Source Shortest Path.
     *
     * Since graph has weights → use Dijkstra.
     */
    public static int networkDelayTime(int[][] times, int n, int k) {

        // Step 1: Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph
                .computeIfAbsent(u, x -> new ArrayList<>())
                .add(new int[]{v, w});
        }

        // Step 2: Min-Heap (distance, node)
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        minHeap.offer(new int[]{0, k});  // distance 0 at start node

        // Step 3: Distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        while (!minHeap.isEmpty()) {

            int[] current = minHeap.poll();
            int currDist = current[0];
            int node = current[1];

            // If we already found better path, skip
            if (currDist > dist[node]) continue;

            if (!graph.containsKey(node)) continue;

            for (int[] neighbor : graph.get(node)) {

                int nextNode = neighbor[0];
                int weight = neighbor[1];

                int newDist = currDist + weight;

                // Relaxation step
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    minHeap.offer(new int[]{newDist, nextNode});
                }
            }
        }

        // Step 4: Find maximum distance
        int maxTime = 0;

        // Make sure you exclude i = 0 as index starts from 1
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;  // unreachable node
            }
            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }
}
