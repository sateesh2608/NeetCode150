package com.neetcode150.arrays_and_hashing;

public class Q2ValidAnagram {

	public static void main(String[] args) {
		
		String s ="cat";
		String t ="tca";
		
		// BF - take each charater from s and check that charater in t and if found remove from both strings and finally
		//you end up in empty both strings if that means its anagram 
		//time complexity will be O(n^2)
		
		
		// optimal approach
		// check lenght of both strings if not equal no need to move further say its not valid anagrams
		// frequency of each character is same in both strings then we could say both are valid anagrams
		// time complexity is O(n) and space complexity is O(1)
		
		
		System.out.println(isValidAnagram(s,t));
		
		
		
	}

	private static boolean isValidAnagram(String s, String t) {
		
		if(s.length() != t.length()) {
			return false;
		}
		
		int[] inputArray = new int[26];

//		Arrays.fill(inputArray, 0);
		
		for (int i=0;i<s.length();i++) {
			
			inputArray[s.charAt(i)-'a']++;
			inputArray[t.charAt(i)-'a']--;
		}
		
		for (int i : inputArray) {
			if(i!=0)
				return false;
		}
		return true;
		
	}
}
