package com.neetcode150.arrays_and_hashing;

import java.util.Arrays;
import java.util.HashMap;

public class Q3TwoSum {

	
	public static void main(String[] args) {
		
		int[] nums = {15,7,2,11};
		int target = 22;
		
		//BF - iterate through nums and and subtract target and nums and check that target value is present in the
		//array then return indices if present
		//TC - O(n^2) 
		
		// Next Best Approach
		// Sort the numbers and check the a = target-nums(each element) and using Binary search we can take mid value and
		// see if a is greater or less than mid value and you can search only one part of array by ignoring other part.
		// to have better time complexity to search. 
		// TC - O(nlogn) for sort + O(logn) for binary search so final TC is O(nlogn)
		
		//Optimal solution
		System.out.println(Arrays.toString(getIndicesOfTwoSum(nums,target)));
		
	}

	private static int[] getIndicesOfTwoSum(int[] nums, int target) {
		
		HashMap<Integer,Integer> seenNumbers = new HashMap<>();
		for (int i =0;i<nums.length;i++) {
			int remainder = target-nums[i];
			
			if(seenNumbers.containsKey(remainder)) {
				return new int[]{i,seenNumbers.get(remainder)};
			}

			seenNumbers.put(nums[i], i);
			
		}
		
		return new int[] {};
	}
}
