package com.neetcode150.mathgeometry;

public class Q5PlusOne {

	public static void main(String[] args) {

		int[][] testCases = { { 1, 2, 3 }, // 123 → 124
				{ 4, 3, 2, 1 }, // 4321 → 4322
				{ 9 }, // 9 → 10
				{ 9, 9, 9 }, // 999 → 1000
				{ 1, 2, 9 } // 129 → 130
		};

		for (int[] digits : testCases) {
			System.out.print("Input:  ");
			printArray(digits);

			int[] result = plusOne(digits);

			System.out.print("Output: ");
			printArray(result);
			System.out.println();
		}
	}

	/**
	 * Method: plusOne ---------------- Intuition: We simulate how addition works
	 * manually.
	 *
	 * Start from the last digit: - If digit < 9 → just add 1 and we are done. - If
	 * digit == 9 → it becomes 0 and we carry 1 to the next digit.
	 *
	 * If all digits are 9 (e.g., 999): - We convert all to 0 and add a new leading
	 * 1 → 1000
	 */
	public static int[] plusOne(int[] digits) {

		int n = digits.length;

		// Traverse from last digit to first
		for (int i = n - 1; i >= 0; i--) {

			// Case 1: digit is less than 9 → no carry needed
			// my explanation below
			// last digit 1 e.g. 1234 or 5453
			// no carry expected just add last digit and return
			if (digits[i] < 9) {
				digits[i]++; // simply add 1
				return digits; // done, return immediately
			}

			// Case 2: digit is 9 → becomes 0 and carry moves left
			// my explanation below
			// one digit 9 and remaining non 9's e.g. 1239 or 1299
			// expect carry, add to previous digit(above lines in next subsequent iterations handles ) and make last digit as 0 (below line will handle)
			digits[i] = 0;
		}

		// If we reach here → all digits were 9
		// Example: 999 → 000 → need to add leading 1 - this explantion is for below 2 lines
		
		//my explanation below for each line 
		// if all digits are 9's eg 999 or 9999, result should be 1000 or 10000
        // In this case entire loop executes by making all digits as 0
        // we need make new array of size initial array size + 1 so to accomadate additional 1
		int[] result = new int[n + 1];
		
		//my explanation below
		// already initial values of result array is 0 just need to add 1 in the begining.
		result[0] = 1; // leading 1

		// rest are already 0 by default
		return result;
	}

	// Helper method to print array
	public static void printArray(int[] arr) {
		System.out.print("[ ");
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println("]");
	}
}