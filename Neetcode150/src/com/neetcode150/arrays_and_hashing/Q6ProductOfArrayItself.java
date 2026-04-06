package com.neetcode150.arrays_and_hashing;

import java.util.Arrays;

public class Q6ProductOfArrayItself {

	public static void main(String[] args) {
		
		int[] nums = {1,2,3,4};
		
		//best approach with prefix and postfix array 
		System.out.println(Arrays.toString(productExceptSelf(nums)));
		
		//optimal without too many arrays to reduce space complexity
		System.out.println(Arrays.toString(productExceptSelfOptimal(nums)));
	}

	private static int[] productExceptSelfOptimal(int[] nums) {

		int[] result = new int[nums.length];
		int prefix =1,postfix =1;
		
		for (int i = 0; i < nums.length; i++) {
			result[i] = prefix;
			prefix = prefix*nums[i];
		}
		
		// result -- 1 1 2 6 
		// prefix -- 1 2 6 24
		
		for (int i = nums.length-1; i >= 0 ; i--) {
			result[i] = result[i]*postfix;
			postfix = postfix*nums[i];
		}
		
		// result -- 6 8 
		// postfix -- 4 12 
		
		return result;
	}

	private static int[] productExceptSelf(int[] nums) {

		
		int[] prefixProduct = new int[nums.length];
		prefixProduct[0] = 1;
		
		for (int i = 1; i < nums.length; i++) {
			prefixProduct[i] = prefixProduct[i-1]*nums[i-1];
		}
		
		int[] postProduct = new int[nums.length];
		postProduct[nums.length-1] = 1;
		
		for (int i = nums.length-2; i>=0; i--) {
			postProduct[i] = postProduct[i+1]*nums[i+1];
		}
		
		int[] result = new int[nums.length];
		
		for (int i = 0; i < nums.length; i++) {
			result[i] = prefixProduct[i]*postProduct[i];
		}
		
		return result;
	}
	
}
