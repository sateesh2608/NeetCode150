package com.neetcode150.sliding_window;

import java.util.HashSet;

public class Q2LongestSubstring {

	public static void main(String[] args) {

		String s = "pwwkewxpw";
		System.out.println(getLongestSubstringLength(s));
	}

	private static int getLongestSubstringLength(String s) {

		HashSet<Character> set = new HashSet<Character>();
		int maxLength = 0;
		int left = 0;
		
		for (int right = 0; right < s.length(); right++) {
	
			while(set.contains(s.charAt(right))) {				
				set.remove(s.charAt(left));
				left++;
			}
			
			set.add(s.charAt(right));
			maxLength = Math.max(maxLength, right-left+1);
		}
		return maxLength;
	}

}
