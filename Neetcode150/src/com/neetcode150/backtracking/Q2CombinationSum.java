package com.neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q2CombinationSum {

	public static void main(String[] args) {

		int[] candidates = {2,3,6,7};
		int target = 7;	
		System.out.println(combinationSum(candidates, target));
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {

		
		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> tempList = new ArrayList<Integer>();
		backtrack(resultList, tempList, candidates, 0, target);
		
		return resultList;
		
		
	}

	private static void backtrack(List<List<Integer>> resultList, List<Integer> tempList, int[] candidates, int i, int target) {

		
		// base/exit condition for recursion
		if(target == 0){
			resultList.add(new ArrayList<>(tempList));
            return;
		}
		
		if(i == candidates.length || target < 0)
			return;
		
		tempList.add(candidates[i]);
		
		
		// 2 cases 1) reuse that means no changes in index but target will be target - previous value.
		backtrack(resultList, tempList, candidates, i, target-candidates[i]);
		
		// 2) part a)one exclude/remove recently inserted element
		tempList.remove(tempList.size() - 1);
		
		// part b) increment index by 1 and target is as is because we have excluded/removed 2.
		backtrack(resultList, tempList, candidates, i+1, target); 
		
		/****  This is very clear and easy explanation but getting duplicate results, need to put in set to get correct answer so not using this
		
		// 3 cases 1) include each element of candidates array single time , in this case i should be incremented to next value
		// and target should be updated target-first value
		backtrack(resultList, tempList, candidates, i+1, target-candidates[i]);
		
		// 2) include each element of candidates array multiple times , in this case i should be stay as is as we need to add first element again
		// and target should be updated target-first value
		backtrack(resultList, tempList, candidates, i, target-candidates[i]);
		
		// 3) previously value is not in result to get target, this is done in 2 steps, one exclude/remove recently inserted element
		tempList.remove(tempList.size() - 1);
		// two increment index by and target is as is.
		backtrack(resultList, tempList, candidates, i+1, target); 
		
		*/
		
	}
}
