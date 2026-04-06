package com.neetcode150.advanced_graphs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q4ASwimInRisingWater {

	    public static void main(String[] args) {
	
	        int[][] grid = {
	            {0, 2},
	            {1, 3}
	        };

	        int result = swimInWater(grid);

	        System.out.println("Minimum time to reach bottom-right: " + result);
	    }

	    /*
         * INTUITION:
         * We treat each cell as a node.
         * Moving to a cell costs the maximum height seen so far along the path.
         * We want to minimize that maximum height.
         * 
         * This is a Dijkstra-like problem:
         * Instead of minimizing SUM of weights,
         * we minimize the MAX value along the path.
         */
	    private static int swimInWater(int[][] grid) {

	        // Min-Heap ordered by the "time" (max height so far)
	        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

	        int m = grid.length;
	        int n = grid[0].length;

	        /*
	         * Each element in queue:
	         * [row, col, maxHeightSoFar]
	         */

	        // Start at (0,0)
	        // Initial time = grid[0][0] because water must at least reach this level
	        queue.add(new int[]{0, 0, grid[0][0]});

	        boolean[][] visited = new boolean[m][n];

	        // 4-directional movement
	        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

	        while (!queue.isEmpty()) {

	            // Always expand the cell with smallest maxHeight so far
	            int[] curr = queue.poll();

	            int row = curr[0];
	            int col = curr[1];
	            int pathMax = curr[2];

	            /*
	             * If we reached bottom-right,
	             * this is guaranteed to be the minimum possible time
	             * because of min-heap ordering (Dijkstra property).
	             */
	            if (row == m - 1 && col == n - 1)
	                return pathMax;

	            if (visited[row][col])
	                continue;

	            visited[row][col] = true;

	            // Explore all 4 directions
	            for (int[] direction : directions) {

	                int newRow = row + direction[0];
	                int newCol = col + direction[1];

	                // Check boundaries and visited
	                if (newRow >= 0 && newCol >= 0 &&
	                    newRow < m && newCol < n &&
	                    !visited[newRow][newCol]) {

	                    /*
	                     * IMPORTANT:
	                     * New time = max(current path max, next cell height)
	                     * 
	                     * Because water must be high enough for BOTH:
	                     * - previous highest cell
	                     * - new cell
	                     */
	                    int newMax = Math.max(pathMax, grid[newRow][newCol]);

	                    queue.offer(new int[]{newRow, newCol, newMax});
	                }
	            }
	        }

	        return -1; // Should never happen
	    }
}
