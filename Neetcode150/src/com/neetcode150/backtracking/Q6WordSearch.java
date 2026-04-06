package com.neetcode150.backtracking;

public class Q6WordSearch {

	public static void main(String[] args) {

		char[][] board = { { 'A', 'B', 'C', 'D' }, { 'S', 'A', 'A', 'T' }, { 'A', 'C', 'A', 'E' } };
		String word = "CAT";
		System.out.println(exist(board, word));
	}

	public static boolean exist(char[][] board, String word) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (backTrack(i, j, board, word, 0)) {
					return true;
				}

			}
		}
		return false;
	}

	public static boolean backTrack(int r, int c, char[][] board, String word, int index) {

		if (index == word.length())
			return true;

		if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != word.charAt(index))
			return false;

		// visited array to true
		char temp = board[r][c];
		board[r][c] = '#';

		// backtrack and resolve at each cell all 4 sides
		boolean found = backTrack(r - 1, c, board, word, index + 1) || backTrack(r + 1, c, board, word, index + 1)
				|| backTrack(r, c - 1, board, word, index + 1) || backTrack(r, c + 1, board, word, index + 1);

		// change back the visited value
		board[r][c] = temp;

		return found;
	}
}
