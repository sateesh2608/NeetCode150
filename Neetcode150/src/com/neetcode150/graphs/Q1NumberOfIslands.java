package com.neetcode150.graphs;

public class Q1NumberOfIslands {

	public static void main(String[] args) {
		char[][] grid = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };

		System.out.println(numIslandsUsingDFS(grid));
	}

	public static int numIslandsUsingDFS(char[][] grid) {

		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;

		int count = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					dfs(i, j, grid);
					count++;
				}
			}

		}

		return count;
	}

	private static void dfs(int i, int j, char[][] grid) {

		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0')
			return;

			// Mark cell as visited
			grid[i][j] = '0';
	
			// dfs calls to its neighbors, call all four sides
			dfs(i - 1, j, grid);
			dfs(i + 1, j, grid);
			dfs(i, j - 1, grid);
			dfs(i, j + 1, grid);

	}
}
