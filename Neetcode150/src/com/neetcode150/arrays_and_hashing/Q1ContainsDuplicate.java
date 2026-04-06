package com.neetcode150.arrays_and_hashing;

import java.util.HashSet;

public class Q1ContainsDuplicate {

	public static void main(String[] args) {
		
		int[] inputArray = {1,-2,-2,4};

		//Brute Force
		//Iterate through each element for every corresponding element and return if you get duplicate element
		//time complexity will be o(n^2)
		
		//NExt Best sort and check for next element and return true if you find any duplicate
		//time complexity will be O(nlogn)
		
		//Optimal solution use hash set
		//Time Complexity O(n)
		System.out.println(CheckContainsDuplicate(inputArray));
		System.out.println("we have duplicates and duplicate number is: "+getDuplicateNumber(inputArray));
		
	}

	private static boolean CheckContainsDuplicate(int[] inputArray) {
		
		HashSet<Integer> seenNumbers = new HashSet<Integer>();

		for (int i : inputArray) {
			
//			if(seenNumbers.contains(i)) 
//				return true;
//			else
//				seenNumbers.add(i);
			
			if(!seenNumbers.add(i))
				return true;
				
			
		}
		
		return false;

	}
	
private static int getDuplicateNumber(int[] inputArray) {
		
		HashSet<Integer> seenNumbers = new HashSet<Integer>();

		for (int i : inputArray) {
			
//			if(seenNumbers.contains(i)) 
//				return true;
//			else
//				seenNumbers.add(i);
			
			if(!seenNumbers.add(i))
				return i;
				
			
		}
		
		return -1;

	}
}
