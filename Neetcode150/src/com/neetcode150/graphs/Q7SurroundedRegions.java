package com.neetcode150.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Q7SurroundedRegions {

	public static void main(String[] args) {

		char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		
		System.out.println("Before Modifying matrix\n");
		printMatrix(board);
		solve(board);
		System.out.println("\nAfter Modifying matrix(DFS)\n");
		printMatrix(board);
	
		char[][] board2 = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		
		solveByBfs(board2);
		System.out.println("\nAfter Modifying matrix(BFS)\n");
		printMatrix(board2);
		
		
	}

	public static void solve(char[][] board) {

		
		if (board == null || board.length == 0) return;
		
		int m = board.length;
		int n = board[0].length;
		
		// Step 1: Identify all the O's and replace with 'V' as visited.
		for (int i = 0; i < n; i++) {
			floodFill(0,i,m,n,board); // top row
			floodFill(m-1,i,m,n,board); // bottom row 
		}
		
		for (int i = 0; i < m; i++) {
			floodFill(i,0,m,n,board); // left column
			floodFill(i,n-1,m,n,board); // right column
		}
		
		
		// Step 2: replace left O's with X and V's with O's again 
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				 if (board[i][j] == 'O') {
	                    board[i][j] = 'X';
	                } else if (board[i][j] == 'T') {
	                    board[i][j] = 'O';
	                }
			}
		}
	}
	
	private static void floodFill(int row, int column, int m, int n, char[][] board) {

		
		if(row<0 || column<0 || row>=m || column>=n || board[row][column] !='O')
			return;
		
		board[row][column] = 'T';
		
		floodFill(row-1, column, m, n, board);
		floodFill(row+1, column, m, n, board);
		floodFill(row, column-1, m, n, board);
		floodFill(row, column+1, m, n, board);
		
	}
	
	
	public static void solveByBfs(char[][] board) {
	    if (board == null || board.length == 0) return;

	    int m = board.length;
	    int n = board[0].length;

	    Queue<int[]> queue = new LinkedList<>();

	    // Add all border O's to queue
	    for (int i = 0; i < n; i++) {
	        if (board[0][i] == 'O') queue.offer(new int[]{0, i});
	        if (board[m - 1][i] == 'O') queue.offer(new int[]{m - 1, i});
	    }

	    for (int i = 0; i < m; i++) {
	        if (board[i][0] == 'O') queue.offer(new int[]{i, 0});
	        if (board[i][n - 1] == 'O') queue.offer(new int[]{i, n - 1});
	    }

	    // BFS to mark safe regions
	    while (!queue.isEmpty()) {
	        int[] cell = queue.poll();
	        int r = cell[0];
	        int c = cell[1];

	        if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] != 'O')
	            continue;

	        board[r][c] = 'T';

	        queue.offer(new int[]{r + 1, c});
	        queue.offer(new int[]{r - 1, c});
	        queue.offer(new int[]{r, c + 1});
	        queue.offer(new int[]{r, c - 1});
	    }

	    // Flip and restore
	    for (int i = 0; i < m; i++) {
	        for (int j = 0; j < n; j++) {
	            if (board[i][j] == 'O') board[i][j] = 'X';
	            else if (board[i][j] == 'T') board[i][j] = 'O';
	        }
	    }
	}

	private static void printMatrix(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]+ " ");
			}
			System.out.println();
		}
	} 

}
