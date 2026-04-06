package com.neetcode150.arrays_and_hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q4GroupAnagrams {

	public static void main(String[] args) {
		
		String[] inputArray = {"eat","tea","tan","atc","nat","bat"};
		
		 System.out.println(getGroupAnagrams(inputArray));
		 
	}

	private static List<List<String>> getGroupAnagrams(String[] inputArray) {
		
		if(inputArray.length == 0 || inputArray == null)
			return new ArrayList<List<String>>();
		
		
		HashMap<String,List<String>> resultMap = new HashMap<>();
		
		for (String string : inputArray) {
			
			int[] seenArray = new int[26];
//			Arrays.fill(seenArray, 0);
			
			for (char string2 : string.toCharArray()) {
				seenArray[string2-'a']++;
			}
			
			StringBuilder sb = new StringBuilder("");
			for(int i=0; i<26; i++){
                sb.append(seenArray[i]);
            }
			
//			if(!resultMap.containsKey(sb.toString()))
//				resultMap.put(sb.toString(), new ArrayList<String>());
//			
//			resultMap.get(sb.toString()).add(string);

			//replaced above lines with single line
			resultMap
			.computeIfAbsent(sb.toString(), k->new ArrayList<>()).add(string);
			
			
//			System.out.println("Array after converting to string is: "+sb.toString());
			
		}
		
		
		return new ArrayList<List<String>>(resultMap.values());
	}
}
