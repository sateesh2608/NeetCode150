package com.neetcode150.graphs;

import java.util.*;

/*
    Pacific Atlantic Water Flow
    ------------------------------------
    Key Idea:
    Instead of starting DFS/BFS from every cell (which would be O((m*n)^2)),
    we reverse the water flow direction.

    Water normally flows from higher -> lower or equal height.
    So we start from ocean borders and move to neighbors with
    height >= current cell (reverse condition).

    Each cell is processed at most twice (once per ocean),
    giving Time Complexity: O(m * n)
*/

public class Q6PacificAtlanticWaterFlow {

    // Reusable direction array to avoid recreating every call
    private static final int[][] DIRECTIONS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    /* =========================================================
       DFS APPROACH
       Time Complexity: O(m * n)
       Space Complexity: O(m * n) (visited arrays + recursion stack)
    ========================================================= */
    public static List<List<Integer>> pacificAtlanticDFS(int[][] heights) {

        List<List<Integer>> result = new ArrayList<>();

        // Edge case check
        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return result;

        int rows = heights.length;
        int cols = heights[0].length;

        // visited arrays for each ocean
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Start DFS from Pacific (top row and left column)
        for (int col = 0; col < cols; col++) {
            dfs(heights, pacific, 0, col);          // Top border
            dfs(heights, atlantic, rows - 1, col);  // Bottom border
        }

        for (int row = 0; row < rows; row++) {
            dfs(heights, pacific, row, 0);          // Left border
            dfs(heights, atlantic, row, cols - 1);  // Right border
        }

        // Collect cells reachable by BOTH oceans
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (pacific[row][col] && atlantic[row][col]) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }

        return result;
    }

    /*
        DFS helper method
        Moves in reverse water direction:
        Only move to neighbor if its height >= current height.
    */
    private static void dfs(int[][] heights, boolean[][] visited, int row, int col) {

        // If already visited, stop recursion
        if (visited[row][col]) return;

        visited[row][col] = true;

        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if valid next move
            if (isValid(heights, visited, row, col, newRow, newCol)) {
                dfs(heights, visited, newRow, newCol);
            }
        }
    }

    /* =========================================================
       BFS APPROACH
       Time Complexity: O(m * n)
       Space Complexity: O(m * n)
    ========================================================= */
    public static List<List<Integer>> pacificAtlanticBFS(int[][] heights) {

        List<List<Integer>> result = new ArrayList<>();

        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return result;

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Queues for BFS traversal
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        // Initialize Pacific (top row) and Atlantic (bottom row)
        for (int col = 0; col < cols; col++) {
            pacificQueue.offer(new int[]{0, col});
            atlanticQueue.offer(new int[]{rows - 1, col});
            pacific[0][col] = true;
            atlantic[rows - 1][col] = true;
        }

        // Initialize Pacific (left column) and Atlantic (right column)
        for (int row = 0; row < rows; row++) {
            pacificQueue.offer(new int[]{row, 0});
            atlanticQueue.offer(new int[]{row, cols - 1});
            pacific[row][0] = true;
            atlantic[row][cols - 1] = true;
        }

        // Perform BFS traversal for both oceans
        bfs(heights, pacificQueue, pacific);
        bfs(heights, atlanticQueue, atlantic);

        // Collect intersection cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (pacific[row][col] && atlantic[row][col]) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }

        return result;
    }

    /*
        BFS traversal
        Expands outward from ocean borders.
        Only moves to neighbors with height >= current.
    */
    private static void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValid(heights, visited, row, col, newRow, newCol)) {
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }

    /*
        Common validation logic:
        1. Within bounds
        2. Not visited
        3. Reverse flow condition: neighbor height >= current height
    */
    private static boolean isValid(int[][] heights,
                                   boolean[][] visited,
                                   int row, int col,
                                   int newRow, int newCol) {

        int rows = heights.length;
        int cols = heights[0].length;

        return newRow >= 0 && newCol >= 0
                && newRow < rows && newCol < cols
                && !visited[newRow][newCol]
                && heights[newRow][newCol] >= heights[row][col];
    }

    /* =========================================================
       MAIN METHOD (Sample Test)
    ========================================================= */
    public static void main(String[] args) {

        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        System.out.println("DFS Result:");
        System.out.println(pacificAtlanticDFS(heights));

        System.out.println("BFS Result:");
        System.out.println(pacificAtlanticBFS(heights));
    }
}
