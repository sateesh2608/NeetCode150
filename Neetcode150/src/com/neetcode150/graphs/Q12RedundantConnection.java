package com.neetcode150.graphs;

public class Q12RedundantConnection {

    /*
     * ============================================================
     * INTUITION
     * ============================================================
     *
     * A tree with n nodes must have exactly n - 1 edges.
     * If we add one extra edge, a cycle is created.
     *
     * Union-Find (Disjoint Set) helps us detect cycles efficiently.
     *
     * Idea:
     * - Each node initially belongs to its own set.
     * - For each edge (u, v):
     *      1) Find root of u
     *      2) Find root of v
     *      3) If roots are same → cycle detected → redundant edge
     *      4) Otherwise, union the sets
     *
     * We implement two versions:
     *  1) Union-Find with Path Compression ONLY (no rank)
     *  2) Union-Find with Path Compression + Union by Rank
     *
     * Time Complexity (both):
     *  Amortized O(alpha(N)) ≈ constant
     *
     * ============================================================
     */

    public static void main(String[] args) {

        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 3}
        };

        System.out.println("Using Union-Find WITHOUT rank:");
        int[] redundant1 = findRedundantConnectionWithoutRank(edges);
        System.out.println(redundant1[0] + ", " + redundant1[1]);

        System.out.println("Using Union-Find WITH rank:");
        int[] redundant2 = findRedundantConnectionWithRank(edges);
        System.out.println(redundant2[0] + ", " + redundant2[1]);
    }

    /*
     * ============================================================
     * UNION-FIND WITHOUT RANK
     * ============================================================
     *
     * Uses:
     *  - Path Compression
     *  - Simple union (attach one root to another)
     *
     * Rank is not used.
     * Tree may become slightly unbalanced,
     * but path compression keeps it efficient.
     */

    static class UnionFindWithoutRank {

        int[] parent;

        UnionFindWithoutRank(int n) {
            parent = new int[n + 1];  // 1-based indexing as provided in question nodes are from 1 to n.

            // Initially each node is its own parent
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        // Find with path compression
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // flatten tree
            }
            return parent[x];
        }

        // Returns false if cycle detected
        boolean union(int x, int y) {

            int rootX = find(x);
            int rootY = find(y);

            // If same root → already connected → cycle
            if (rootX == rootY) {
                return false;
            }

            // Attach rootY under rootX
            parent[rootY] = rootX;

            return true;
        }
    }

    public static int[] findRedundantConnectionWithoutRank(int[][] edges) {

        UnionFindWithoutRank uf =
                new UnionFindWithoutRank(edges.length);

        for (int[] edge : edges) {

            // If union fails → cycle found
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }

        return new int[0];
    }

    /*
     * ============================================================
     * UNION-FIND WITH RANK
     * ============================================================
     *
     * Uses:
     *  - Path Compression
     *  - Union by Rank (tree height approximation)
     *
     * Rank helps keep trees shallow by always attaching
     * smaller height tree under larger height tree.
     */

    static class UnionFindWithRank {

        int[] parent;
        int[] rank;

        UnionFindWithRank(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0; // initial tree height is 0
            }
        }

        // Find with path compression
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // flatten tree
            }
            return parent[x];
        }

        // Union by rank
        boolean union(int x, int y) {

            int rootX = find(x);
            int rootY = find(y);

            // Cycle detected
            if (rootX == rootY) {
                return false;
            }

            // Attach smaller rank tree under larger
            if (rank[rootX] < rank[rootY]) {

                parent[rootX] = rootY;

            } else if (rank[rootX] > rank[rootY]) {

                parent[rootY] = rootX;

            } else {
                // Same rank → attach and increase rank
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }

    public static int[] findRedundantConnectionWithRank(int[][] edges) {

        UnionFindWithRank uf =
                new UnionFindWithRank(edges.length);

        for (int[] edge : edges) {

            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }

        return new int[0];
    }
}
