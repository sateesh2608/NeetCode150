package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2SubsetsII {

	public static void main(String[] args) {
		int[] nums = { 1,2,2 };
		System.out.println(subsets(nums));

	}

	public static List<List<Integer>> subsets(int[] nums) {

		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> tempList = new ArrayList<Integer>();
		Arrays.sort(nums);
		backtrack(resultList, tempList, nums, 0);
		
		return resultList;
	}

	private static void backtrack(List<List<Integer>> resultList, List<Integer> tempList, int[] nums, int start) {

		//only added this line compared to previous subset question to remove duplicate entries.
		// this will work but not optimal as we are back tracking means looping and creating list and then eliminate
		// try to eliminate before that
//		if(resultList.contains(tempList)) return;
		
		resultList.add(new ArrayList<Integer>(tempList));
		for (int i = start; i < nums.length; i++) {

			// this is the line for subset II
			// checking before if we have previous same element dont run loop/backtrack and create new list
			// for this prerequisite is sorting added one more line for sorting nums
			if(i> start && nums[i] == nums[i-1]) continue;
			
			// Case of including number
			tempList.add(nums[i]);

			// backtracking new subset
			backtrack(resultList, tempList, nums, i + 1);

			//Case of removing number
			tempList.remove(tempList.size() - 1);

		}

	}
}
