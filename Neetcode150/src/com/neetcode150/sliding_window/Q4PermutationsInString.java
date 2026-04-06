package com.neetcode150.sliding_window;

public class Q4PermutationsInString {

	public static void main(String[] args) {
		
		String s1 = "aba";
		String s2 = "abcdefghxbaa";
		
		System.out.println(checkInclusion(s1,s2));
		
	}

	private static boolean checkInclusion(String s1, String s2) {

		if(s1.length() > s2.length())
			return false;
		
		int[] s1FreqMap = new int[26];
		int[] s2FreqMap = new int[26];
		
		for (int i=0; i<s1.length();i++) {
			s1FreqMap[s1.charAt(i)-'a']++;
			s2FreqMap[s2.charAt(i)-'a']++;
		}

		for (int i = 0; i < s2.length()-s1.length(); i++) {
			
			if(matches(s1FreqMap,s2FreqMap)) {
				return true;
			}
			
			s2FreqMap[s2.charAt(i+s1.length()) - 'a']++;
			s2FreqMap[s2.charAt(i)-'a']--;
			
		}
		
		return matches(s1FreqMap,s2FreqMap);
	}

	private static boolean matches(int[] s1FreqMap, int[] s2FreqMap) {

		for (int i = 0; i < 26; i++) {
			if(s1FreqMap[i] != s2FreqMap[i])
				return false;
		}
		return true;
	}
}
