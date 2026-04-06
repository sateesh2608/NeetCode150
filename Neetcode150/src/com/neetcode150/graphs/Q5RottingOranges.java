package com.neetcode150.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Q5RottingOranges {
	 public static void main(String[] args) {
	     
	        int[][] grid1 = {
	            {2,1,1},
	            {1,1,0},
	            {0,1,1}
	        };

	        int[][] grid2 = {
	            {2,1,1},
	            {0,1,1},
	            {1,0,1}
	        };

	        int[][] grid3 = {
	            {0,2}
	        };

	        System.out.println("Minutes to rot all oranges (grid1): " + orangesRotting(grid1));
	        System.out.println("Minutes to rot all oranges (grid2): " + orangesRotting(grid2));
	        System.out.println("Minutes to rot all oranges (grid3): " + orangesRotting(grid3));
	    }

	private static int orangesRotting(int[][] grid) {

		int freshCount = 0;
        Queue<int[]> q = new LinkedList<>();

        // Step 1: Initialize queue and count fresh oranges
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2){
                    q.offer(new int[]{i, j});
                } else if(grid[i][j] == 1){
                    freshCount++;
                }
            }
        }

        if(freshCount == 0) return 0;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int minutes = 0;

        // Step 2: BFS level by level
        while(!q.isEmpty()) {
            int size = q.size();  // number of oranges that rot neighbors this minute
            boolean rottedThisMinute = false;

            for(int i = 0; i < size; i++) {
                int[] current = q.poll();
                int row = current[0];
                int col = current[1];

                for(int[] dir: directions){
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if(newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length 
                       && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        q.offer(new int[]{newRow, newCol});
                        freshCount--;
                        rottedThisMinute = true;
                    }
                }
            }

            if(rottedThisMinute) minutes++;
        }

        return freshCount == 0? minutes:-1;	
	}
}
