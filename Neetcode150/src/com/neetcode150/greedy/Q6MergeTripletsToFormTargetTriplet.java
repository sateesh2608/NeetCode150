package com.neetcode150.greedy;

public class Q6MergeTripletsToFormTargetTriplet {

	/**
	 * ========================================================= APPROACH: Greedy →
	 * O(n) =========================================================
	 *
	 * INTUITION: We want to form target = [x, y, z]
	 *
	 * Operation: We can merge triplets → take max of each index
	 *
	 * So we need: - One triplet contributing x - One triplet contributing y - One
	 * triplet contributing z
	 *
	 * BUT: We must IGNORE triplets that exceed target
	 *
	 * WHY: If any value > target → cannot fix it later
	 *
	 * STRATEGY: - Track if we can achieve each index (0,1,2) - Only consider valid
	 * triplets
	 *
	 * TC: O(n) SC: O(1)
	 */
	public static boolean mergeTriplets(int[][] triplets, int[] target) {

		// Track whether we can achieve each position
		boolean match0 = false;
		boolean match1 = false;
		boolean match2 = false;

		for (int[] t : triplets) {

			/*
			 * Skip invalid triplets: If any value exceeds target → useless
			 */
			if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) {
				continue;
			}

			/*
			 * Check if this triplet helps achieve target
			 */
			if (t[0] == target[0])
				match0 = true;
			if (t[1] == target[1])
				match1 = true;
			if (t[2] == target[2])
				match2 = true;

			/*
			 * If all positions are achievable → success
			 */
			// EARLY EXIT → stop as soon as all 3 are found
			if (match0 && match1 && match2)
				return true;
		}

		// if nothing matched in loop no success
		return false;
	}

	public static void main(String[] args) {

		int[][] triplets = { { 2, 5, 3 }, { 1, 8, 4 }, { 1, 7, 5 } };

		int[] target = { 2, 7, 5 };

		System.out.println("Can form target: " + mergeTriplets(triplets, target)); // true

		int[] target2 = { 2, 9, 5 };

		System.out.println("Can form target: " + mergeTriplets(triplets, target2)); // false
	}
}