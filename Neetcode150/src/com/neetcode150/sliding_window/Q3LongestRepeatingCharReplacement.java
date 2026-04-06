package com.neetcode150.sliding_window;

public class Q3LongestRepeatingCharReplacement {

	public static void main(String[] args) {

		String s = "hphph";
		int k = 2;
		System.out.println(getLongestSubstringLength(s,k));
	}

	private static int getLongestSubstringLength(String s, int k) {


		int[] freq = new int[26];
		
		int maxFreq= 0;
		int maxWindow = 0;
		int left = 0;
		
		for (int right = 0; right < s.length(); right++) {
			
			//Updates the frequency of each charater
			freq[s.charAt(right)-'a']++;
			
			//updates maxFreq of all the strings
			maxFreq = Math.max(maxFreq, freq[s.charAt(right)-'a']);
			int windowSize = right-left+1;
			
			//if the condition met, it means we dont have enough operations to replace
			// then shrink the window means move one step forward start pointer and update frequency accordingly 
			if(windowSize - maxFreq > k) {
				freq[s.charAt(right)-'a']--;
				left++;
			}
			
			windowSize = right-left+1;
			maxWindow = Math.max(maxWindow, windowSize);
		}
		return maxWindow;
	}

}
