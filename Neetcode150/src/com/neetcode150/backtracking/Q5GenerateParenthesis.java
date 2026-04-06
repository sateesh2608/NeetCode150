package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q5GenerateParenthesis {

	public static void main(String[] args) {

		int n = 2;
		System.out.println(generateParenthesis(n));
	}

	public static List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();

		int open =0;
		int close = 0;
		
		backtrack(n,result, open,close,"");
		
		return result;
	}

	private static void backtrack(int n, List<String> result, int open, int close, String parenthesis) {
		
		if(parenthesis.length() == 2*n) {
			result.add(parenthesis);
			return;
		}
			
		
		if(open<n) {
			backtrack(n, result, open+1, close, parenthesis+"(");
		}
		
		if(close<open)
			backtrack(n, result, open, close+1, parenthesis+")");
		
	}
}
