package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q9NQueens {

	public static void main(String[] args) {

		int n = 4;
		System.out.println(solveNQueens(n));
	}

	public static List<List<String>> solveNQueens(int n) {

		List<List<String>> result = new ArrayList<>();
		char[][] board = new char[n][n];
//		for (int i = 0; i < n; i++) {
//		    for (int j = 0; j < n; j++) {
//		    	for (char[] row : board) Arrays.fill(row, '.');
//		    }
//		}		
		//Replace commented lines with one line
		for (char[] row : board) Arrays.fill(row, '.');
		
		backTrack(0, board, result, n);
		return result;

	}

	private static void backTrack(int row, char[][] board, List<List<String>> result, int n) {
		// TODO Auto-generated method stub

		if (row == n) {
			result.add(new ArrayList<>(buildBoard(board)));
			return;
		}

		for (int i = 0; i < n; i++) {
			if (isQueensSafe(row, i, board, n)) {
				board[row][i] = 'Q';
				backTrack(row + 1, board, result, n);
				board[row][i] = '.';
			}
		}

	}

	private static boolean isQueensSafe(int row, int col, char[][] board, int n) {

		// check row
		for(int i=0;i<row;i++) {
			if(board[i][col] == 'Q') return false;
		}

		// check diagonal -- upper left
		for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 'Q')
				return false;
		}

		// continue diagonal -- upper right
		for (int i = row-1, j = col+1; i >= 0 && j < n; i--, j++) {
			if (board[i][j] == 'Q')
				return false;
		}

		//No need to check lower left and lower right as our process start by row from top to current row. 
		return true;
	}

	private static List<String> buildBoard(char[][] board) {
		List<String> res = new ArrayList<>();
		for (char[] row : board) {
			res.add(new String(row));
		}
		return res;
	}
}
