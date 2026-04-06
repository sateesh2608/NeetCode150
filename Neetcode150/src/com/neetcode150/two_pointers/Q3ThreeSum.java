package com.neetcode150.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q3ThreeSum {

	public static void main(String[] args) {
		
		int[] nums = {-1,0,1,2,-1,-4};
		int target = 9;
		
		Arrays.sort(nums);
		
		/*
		  as for our input output is [[-1, -1, 2], [-1, 0, 1], [-1, 0, 1]], array at index 1 and 2 are duplicate
		  to remove duplicate we use Hashset, better approach is removing hashset and add 2 conditions.
		  below are 2 methods with and without hashset.
		 */
		
		System.out.println((getIndicesOfThreeSumWithHashset(nums,target)));
		System.out.println((getIndicesOfThreeSumWithoutHashset(nums,target)));
	}

	private static List<List<Integer>> getIndicesOfThreeSumWithHashset(int[] nums, int target) {

		//List<List<Integer>> result = new ArrayList<>();

		Set<List<Integer>> result = new HashSet<>();
		
        if (nums == null || nums.length < 3)
            return new ArrayList<>(result);
        
        for(int i=0; i<nums.length-2;i++) {
        	
        	int left = i+1;
        	int right = nums.length-1;
        	
        	while(left<right) {
        		
        		int sum = nums[i]+nums[left]+nums[right];
        		
        		if(sum>0)
        			right--;
        		else if(sum<0)
        			left++;
        		else {
        			result.add(List.of(nums[i],nums[left],nums[right]));
        			left++;
        			right--;
        		}
        			
        	}
        }
        
		return new ArrayList<List<Integer>>(result);
	}
	
	private static List<List<Integer>> getIndicesOfThreeSumWithoutHashset(int[] nums, int target) {

		List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3)
            return result;
        
        for(int i=0; i<nums.length-2;i++) {
        	
        	// Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
        	
        	int left = i+1;
        	int right = nums.length-1;
        	
        	while(left<right) {
        		
        		int sum = nums[i]+nums[left]+nums[right];
        		
        		if(sum>0)
        			right--;
        		else if(sum<0)
        			left++;
        		else {
        			result.add(List.of(nums[i],nums[left],nums[right]));
        			
        			// Skip duplicates for left & right
        			 while (left < right && nums[left] == nums[left + 1]) left++;
                     while (left < right && nums[right] == nums[right - 1]) right--;

                     left++;
                     right--;
        		}
        			
        	}
        }
        
		return result;
	}
	
}
