package com.neetcode150.binary_search;

public class Q4FindMinimumInRotatedSortedArray {

	public static void main(String[] args) {

		int[] nums = { 11,13,15,17 };
		System.out.println(findMin(nums));
	}

	public static int findMin(int[] nums) {

		int left = 0;
		int right = nums.length-1;
		
		while(left<right) {
			
			int mid = left+(right-left)/2;
			if (nums[right] < nums[mid]) {
				left = mid + 1;
			} else{
				right = mid;
			}
		}
		
		return nums[left];
	}

}
