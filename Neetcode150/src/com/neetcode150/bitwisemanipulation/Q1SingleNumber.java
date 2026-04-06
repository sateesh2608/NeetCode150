package com.neetcode150.bitwisemanipulation;

public class Q1SingleNumber {

	public static void main(String[] args) {

		int[] nums = {2,2,1};
		System.out.println(singleNumber(nums));
	}

	public static int singleNumber(int[] nums) {

		int uniqueNumber = 0;
		
		for (int i : nums) {
			uniqueNumber = i^uniqueNumber;
		}
		
		return uniqueNumber;
	}
}
