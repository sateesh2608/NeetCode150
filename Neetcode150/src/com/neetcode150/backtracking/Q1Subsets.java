package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q1Subsets {

	public static void main(String[] args) {

		int[] nums = { 1, 2, 3 };
		System.out.println(subsets(nums));

	}

	public static List<List<Integer>> subsets(int[] nums) {

		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> tempList = new ArrayList<Integer>();
		backtrack(resultList, tempList, nums, 0);
		
		return resultList;
	}

	private static void backtrack(List<List<Integer>> resultList, List<Integer> tempList, int[] nums, int start) {

		resultList.add(new ArrayList<Integer>(tempList));
		for (int i = start; i < nums.length; i++) {

			// Case of including number
			tempList.add(nums[i]);

			// backtracking new subset
			backtrack(resultList, tempList, nums, i + 1);

			//Case of removing number
			tempList.remove(tempList.size() - 1);

		}

	}
}
