package com.neetcode150.graphs;

import java.util.*;

public class Q10GraphValidTree {

	/**
     * ============================================================
     * APPROACH 1: DFS (Connectivity + Edge Count Check)
     * ============================================================
     *
     * INTUITION:
     * A graph is a valid tree if:
     * 1) It has exactly (n - 1) edges.
     * 2) It is fully connected.
     *
     * WHY edges == n - 1?
     * - Less than n-1 → disconnected.
     * - More than n-1 → must contain cycle.
     *
     * So after checking edge count,
     * we only need to verify connectivity.
     */
    public static boolean validTreeDFS(int n, int[][] edges) {

        // Tree must have exactly n-1 edges
        if (edges.length != n - 1) {
            return false;
        }

        // Build adjacency list (undirected graph)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        // Start DFS from node 0
        dfs(0, graph, visited);

        // If any node is unvisited → disconnected
        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    private static void dfs(int node,
                            List<List<Integer>> graph,
                            boolean[] visited) {

        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    /**
     * ============================================================
     * APPROACH 2: UNION-FIND (Best Approach)
     * ============================================================
     *
     * WHY Union-Find is Best Here?
     *
     * 1) We only need to detect cycle.
     * 2) If edges == n - 1 AND no cycle → automatically connected.
     *
     * Union-Find detects cycle in O(α(n)) time per edge.
     * No adjacency list needed.
     *
     * STRATEGY:
     * - Check edges == n - 1
     * - For each edge:
     *     If both nodes already share same root → cycle
     *     Else union them
     *
     * If no cycle → valid tree.
     */
    public static boolean validTreeUnionFind(int n, int[][] edges) {

        // Tree must have exactly n-1 edges
        if (edges.length != n - 1) {
            return false;
        }

        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {

            // If union fails → cycle detected
            if (!uf.union(edge[0], edge[1])) {
                return false;
            }
        }

        return true;
    }

    /**
     * ============================================================
     * UNION-FIND DATA STRUCTURE
     * ============================================================
     *
     * parent[i] = parent of node i
     * rank[i]   = tree height approximation (for balancing)
     *
     * Optimizations:
     * - Path Compression
     * - Union by Rank
     *
     * Time Complexity:
     * Nearly O(1) per operation.
     */
    static class UnionFind {

        int[] parent;
        int[] rank;

        public UnionFind(int n) {

            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;  // each node is its own parent initially
                rank[i] = 1;
            }
        }

        /**
         * FIND with Path Compression
         *
         * WHY?
         * Makes tree flatter for faster future queries.
         */
        public int find(int node) {

            if (parent[node] != node) {
                parent[node] = find(parent[node]); // path compression
            }

            return parent[node];
        }

        /**
         * UNION by Rank
         *
         * Returns false if cycle detected.
         */
        public boolean union(int u, int v) {

            int rootU = find(u);
            int rootV = find(v);

            // If both share same root → cycle
            if (rootU == rootV) {
                return false;
            }

            // Attach smaller tree under larger tree
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }

            return true;
        }
    }

    /**
     * ============================================================
     * MAIN METHOD WITH TEST CASES
     * ============================================================
     */
    public static void main(String[] args) {

        int n1 = 5;
        int[][] edges1 = {
                {0,1},{0,2},{0,3},{1,4}
        };

        int n2 = 5;
        int[][] edges2 = {
                {0,1},{1,2},{2,3},{1,3},{1,4}
        };

        int n3 = 4;
        int[][] edges3 = {
                {0,1},{2,3}
        };

        System.out.println("===== DFS Approach =====");
        System.out.println("Valid Tree: " + validTreeDFS(n1, edges1));
        System.out.println("Cycle: " + validTreeDFS(n2, edges2));
        System.out.println("Disconnected: " + validTreeDFS(n3, edges3));

        System.out.println("\n===== Union-Find Approach =====");
        System.out.println("Valid Tree: " + validTreeUnionFind(n1, edges1));
        System.out.println("Cycle: " + validTreeUnionFind(n2, edges2));
        System.out.println("Disconnected: " + validTreeUnionFind(n3, edges3));
    }
}