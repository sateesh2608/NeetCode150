package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q4Permutations {

	public static void main(String[] args) {

		int[] nums = { 1, 2, 3 };
		System.out.println(permute(nums));
	}

	public static List<List<Integer>> permute(int[] nums) {

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> temp = new ArrayList<Integer>();

		backTrack(resultList, temp, nums);

		return resultList;

	}

	private static void backTrack(List<List<Integer>> resultList, List<Integer> temp, int[] nums) {

		if (temp.size() == nums.length) {
			resultList.add(new ArrayList<Integer>(temp));
			return;
		}

		for (int j = 0; j < nums.length; j++) {

			if (temp.contains(nums[j]))
				continue;

			temp.add(nums[j]);
			backTrack(resultList, temp, nums);
			temp.remove(temp.size() - 1);
		}
	}
}
