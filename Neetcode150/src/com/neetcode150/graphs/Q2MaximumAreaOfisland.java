package com.neetcode150.graphs;

public class Q2MaximumAreaOfisland {

	public static void main(String[] args) {

		int[][] grid = {
			    {0,0,1,0,0,0,0,1,0,0,0,0,0},
			    {0,0,0,0,0,0,0,1,1,1,0,0,0},
			    {0,1,1,0,1,0,0,0,0,0,0,0,0},
			    {0,1,0,0,1,1,0,0,1,0,1,0,0},
			    {0,1,0,0,1,1,0,0,1,1,1,0,0},
			    {0,0,0,0,0,0,0,0,0,0,1,0,0},
			    {0,0,0,0,0,0,0,1,1,1,0,0,0},
			    {0,0,0,0,0,0,0,1,1,0,0,0,0}
			};

		System.out.println(maxAreaOfIsland(grid));

	}

	public static int maxAreaOfIsland(int[][] grid) {

		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		
		int maxArea = 0;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] == 1) {
					int area = dfs(i,j,grid);
					maxArea = Math.max(maxArea, area);
				}
			}
		}
		return maxArea;
	}

	private static int dfs(int i, int j, int[][] grid) {

		if(i <0 || j<0 || i>= grid.length || j >= grid[0].length || grid[i][j] == 0) return 0;
		
		grid[i][j] = 0;
		
		return 1 + dfs(i-1, j,grid) + dfs(i+1, j,grid)
        + dfs(i, j-1,grid) + dfs(i, j+1,grid);
	}

}
