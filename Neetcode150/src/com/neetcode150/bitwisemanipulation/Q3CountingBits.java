package com.neetcode150.bitwisemanipulation;

import java.util.Arrays;

public class Q3CountingBits {

	public static void main(String[] args) {

		int n = 5;
		System.out.println(Arrays.toString(countBits(n)));
	}

	public static int[] countBits(int n) {

		int[] result = new int[n+1];
		
		for (int i = 0; i <= n; i++) {
			int x = i;
			int counter = 0;
			while(x>0) {
				counter += (x&1);
				x = x>>1;
			}
			// This line is not important, just added for concept - converting decimal to integer.
			System.out.println(Integer.toBinaryString(i));
			result[i] = counter;
		}
		
		return result;
	}
}
