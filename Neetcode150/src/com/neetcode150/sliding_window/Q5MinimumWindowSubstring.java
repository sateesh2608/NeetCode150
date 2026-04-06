package com.neetcode150.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class Q5MinimumWindowSubstring {

	public static void main(String[] args) {
		
		String s = "abaxcbac";
		String t = "abc";
		System.out.println(minWindow(s,t));
	}

	private static String minWindow(String s, String t) {

		if(s.length() == 0 || t.length()==0 || t.length()>s.length())
			return "";
		
		HashMap<Character, Integer> mapFrequencyT = new HashMap<Character, Integer>();
		
		for (int i = 0; i < t.length(); i++) {
			mapFrequencyT.put(t.charAt(i), mapFrequencyT.getOrDefault(t.charAt(i), 0)+1);
		}
		
		int required = mapFrequencyT.size();
		int left = 0, right = 0, createdCharacters = 0;
		int[] ans = {s.length(),left,right};
		
		Map<Character,Integer> substringFreqMap = new HashMap<>();
		
		while(right<s.length()) {
			char val = s.charAt(right);
			substringFreqMap.put(val, substringFreqMap.getOrDefault(substringFreqMap, 0)+1);
			if(mapFrequencyT.containsKey(val) && mapFrequencyT.get(val).intValue() == substringFreqMap.get(val).intValue()) {
				createdCharacters++;
			}
			
			while(left<= right && createdCharacters == required) {
				
				val = s.charAt(left);
				ans[0] = Math.min(ans[0], right-left+1);
				ans[1] = left;
				ans[2] = right;
				
				//Shrink window
				substringFreqMap.put(val, substringFreqMap.get(val)-1);
				
				if(mapFrequencyT.containsKey(val) && mapFrequencyT.get(val).intValue() > substringFreqMap.get(val).intValue()) {
					createdCharacters--;
				}
				
				left++;
				
			}
			right++;
		}
		
		if(ans[0] == -1)
			return "";
		else
			return s.substring(ans[1],ans[2]+1);
	}
}
