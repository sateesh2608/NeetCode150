package com.neetcode150.arrays_and_hashing;

import java.util.HashSet;

public class Q7ValidSudoku {

	public static void main(String[] args) {
		
		char[][] board = {
			    {'5','3','.','.','7','.','.','.','.'},
			    {'6','.','.','1','9','5','.','.','.'},
			    {'.','9','8','.','.','.','.','6','.'},
			    {'8','.','.','.','6','.','.','.','3'},
			    {'4','.','.','8','.','3','.','.','1'},
			    {'7','.','.','.','2','.','.','.','6'},
			    {'.','6','.','.','.','.','2','8','.'},
			    {'.','.','.','4','1','9','.','.','5'},
			    {'.','.','.','.','8','.','.','7','9'}
			};
		System.out.println(isValidSudoku(board));
	}

	public static boolean isValidSudoku(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {

				HashSet<Character> rows = new HashSet<Character>(); 
				HashSet<Character> columns = new HashSet<Character>(); 
				
				 // Row check
                char rowVal = board[i][j];
                if (rowVal != '.') {
                    if (!rows.add(rowVal))
                        return false;
                }
                
                // Column check
                char colVal = board[j][i];
                if (colVal != '.') {
                    if (!columns.add(colVal))
                        return false;
                }
				
			}
		}
		
		// Check 3x3 boxes
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {

                HashSet<Character> box = new HashSet<>();

                for (int i = boxRow * 3; i < boxRow * 3 + 3; i++) {
                    for (int j = boxCol * 3; j < boxCol * 3 + 3; j++) {

                        char val = board[i][j];
                        if (val != '.') {
                            if (!box.add(val))
                                return false;
                        }
                    }
                }
            }
        }
        
        return true;
	}
}
