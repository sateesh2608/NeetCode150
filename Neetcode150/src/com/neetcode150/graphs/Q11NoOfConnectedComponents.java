package com.neetcode150.graphs;

public class Q11NoOfConnectedComponents {

    static int[] parent;
    static int[] rank;

    public static void main(String[] args) {

        int n = 5;
        int[][] edges = {{0,1},{1,2},{3,4}};

        System.out.println("Basic UF: " + countComponentsBasic(n, edges));      // 2
        System.out.println("UF + Rank: " + countComponentsByRank(n, edges));   // 2
    }

    /* ============================================================
       APPROACH 1: BASIC UNION-FIND (Path Compression Only)
       ============================================================

       INTUITION:
       - Start with n separate components.
       - For each edge, merge the two nodes.
       - If merge happens, reduce component count.

       NOTE:
       - No rank optimization.
       - Can form tall trees in worst case.
    */
    public static int countComponentsBasic(int n, int[][] edges) {

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int components = n;

        for (int[] edge : edges) {
            if (unionBasic(edge[0], edge[1])) {
                components--;
            }
        }

        return components;
    }

    public static int findBasic(int node) {
        if (parent[node] != node) {
            parent[node] = findBasic(parent[node]); // path compression
        }
        return parent[node];
    }

    public static boolean unionBasic(int u, int v) {

        int rootU = findBasic(u);
        int rootV = findBasic(v);

        if (rootU == rootV) return false;

        parent[rootU] = rootV;  // simple attach
        return true;
    }


    /* ============================================================
       APPROACH 2: UNION-FIND WITH RANK (Optimized)
       ============================================================

       INTUITION:
       - Always attach smaller tree under bigger tree.
       - Prevents tall trees.
       - Combined with path compression → nearly O(1).

       WHY THIS IS BEST:
       - Handles large graphs efficiently.
       - Industry standard implementation.
    */
    public static int countComponentsByRank(int n, int[][] edges) {

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int components = n;

        for (int[] edge : edges) {
            if (unionByRank(edge[0], edge[1])) {
                components--;
            }
        }

        return components;
    }

    public static int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]); // path compression
        }
        return parent[node];
    }

    public static boolean unionByRank(int u, int v) {

        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV) return false;

        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } 
        else if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } 
        else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }

        return true;
    }
}
