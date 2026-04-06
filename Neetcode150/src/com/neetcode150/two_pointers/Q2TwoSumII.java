package com.neetcode150.two_pointers;

import java.util.Arrays;

public class Q2TwoSumII {

	public static void main(String[] args) {
		
		int[] nums = {2,7,11,15};
		int target = 9;
		
		System.out.println(Arrays.toString(getIndicesOfTwoSum(nums,target)));
		
	}

	private static int[] getIndicesOfTwoSum(int[] nums, int target) {
		
		int left = 0;
		int right = nums.length-1;
		
		while(left<right) {
			
			int sum = nums[left]+nums[right];
			if(sum>target) {
				right--;
			}else if(sum<target) {
				left++;
			}else {
				return new int[] {++left,++right};
			}
		}
		
		return new int[] {};
	}

}
