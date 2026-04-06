package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q3CombinationSumII {

	public static void main(String[] args) {

		int[] candidates = {10,1,2,7,6,1,5};
		int target = 8;
		System.out.println(combinationSum(candidates, target));
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {

		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> tempList = new ArrayList<Integer>();
		
		Arrays.sort(candidates);
		backtrack(resultList, tempList, candidates, 0, target);

		return resultList;

	}

	private static void backtrack(List<List<Integer>> resultList, List<Integer> tempList, int[] candidates, int index,
			int target) {
		
		if(target == 0) {
			resultList.add(new ArrayList<Integer>(tempList));
			return;
		}
		
		for (int i = index; i < candidates.length; i++) {
			
			if(i> index && candidates[i] == candidates[i-1]) continue;
			
			if(candidates[i] > target ) break;
			
			
			tempList.add(candidates[i]);
			
			backtrack(resultList, tempList, candidates, i+1, target-candidates[i]);
			
			tempList.remove(tempList.size()-1);
			
		}
		

	}
}
