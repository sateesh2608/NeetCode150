package com.neetcode150.bitwisemanipulation;

public class Q2NumberOf1Bits {

	public static void main(String[] args) {

		int n = 11;
		System.out.println(hammingWeight(n));
	}

	public static int hammingWeight(int n) {

		int counter = 0;
		while (n > 0) {
			n = n & (n - 1);
			counter++;
		}

		return counter;
	}

}
