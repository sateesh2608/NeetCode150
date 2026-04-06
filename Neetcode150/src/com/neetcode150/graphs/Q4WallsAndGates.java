package com.neetcode150.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Q4WallsAndGates {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] grid = {
            {INF, -1, 0, INF},
            {INF, INF, INF, -1},
            {INF, -1, INF, -1},
            {0, -1, INF, INF}
        };

        islandsAndTreasure(grid);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void islandsAndTreasure(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return;

        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };

        // Add all gates (0) to queue
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // Multi-source BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newCol >= 0 &&
                    newRow < grid.length && newCol < grid[0].length &&
                    grid[newRow][newCol] == INF) {

                    grid[newRow][newCol] = grid[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
}
