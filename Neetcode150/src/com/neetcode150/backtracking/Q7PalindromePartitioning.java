package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q7PalindromePartitioning {

	public static void main(String[] args) {
		
		String s = "aab";
		System.out.println(partition(s));
	}

	public static List<List<String>> partition(String s) {

		List<List<String>> result = new ArrayList<List<String>>();
		List<String> temp = new ArrayList<String>();
		backtrack(s,result,temp);
		
		return result;
	}

	private static void backtrack(String s, List<List<String>> result, List<String> temp) {

		if(s.length() == 0) {
			result.add(new ArrayList<String>(temp));
		}
		for(int i=0; i<s.length(); i++) {
			
			String substring = s.substring(0, i+1);
			if(isPalindrome(s.substring(0, i+1))) {
				temp.add(substring);
				backtrack(s.substring(i+1), result, temp);
				temp.remove(temp.size()-1);
			}
		}
	}

	private static boolean isPalindrome(String substring) {

		int start = 0;
		int end = substring.length()-1;
		
		while(start < end) {
			if(substring.charAt(start) != substring.charAt(end)) return false;
			start++;
			end--;
		}
		return true;
	}

}
