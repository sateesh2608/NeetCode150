package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q8LetterCombinationsofPhoneNo {

	public static void main(String[] args) {
		String digits = "23";
		System.out.println(letterCombinations(digits));
	}

	public static List<String> letterCombinations(String digits) {
		
		List<String> result = new ArrayList<String>();
		
		if(digits == null || digits.length() == 0) return result;
		
		String[] phone = {
	            "",     // 0
	            "",     // 1
	            "abc",  // 2
	            "def",  // 3
	            "ghi",  // 4
	            "jkl",  // 5
	            "mno",  // 6
	            "pqrs", // 7
	            "tuv",  // 8
	            "wxyz"  // 9
	        };
		
		backTrack(phone, new StringBuilder() ,result, digits, 0);
		
		return result;
		
	}

	private static void backTrack(String[] phone, StringBuilder current,  List<String> result, String digits, int index) {

		if(index == digits.length()) {
			result.add(current.toString());
			return;
		}
		
		String characters = phone[digits.charAt(index)-'0'];
		
		for (int i = 0; i < characters.length(); i++) {
			
			current.append(characters.charAt(i));
			backTrack(phone, current, result, digits, index+1);
			current.deleteCharAt(current.length()-1);
		}
		
	}
}
