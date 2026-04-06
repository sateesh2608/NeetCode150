package com.dsa.general_theory.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestCommonSubsequence {

	public static void main(String[] args) {

		String X = "ABCBDAB";
		String Y = "BDCABA";

		// ---------------- MEMOIZATION ----------------
		int memoResult = lcsMemoization(X, Y);
		System.out.println("LCS Length (Memoization): " + memoResult);

		// ---------------- TABULATION ----------------
		int tabResult = lcsTabulation(X, Y);
		System.out.println("LCS Length (Tabulation): " + tabResult);

		// ---------------- SPACE OPTIMIZED ----------------
		int spaceResult = lcsSpaceOptimized(X, Y);
		System.out.println("LCS Length (Space Optimized): " + spaceResult);

		// -------- PRINT LCS --------
		String lcsString = printLCS(X, Y);
		System.out.println("Actual LCS String: " + lcsString);

		Set<String> result = printAllLCS(X, Y);

		System.out.println("All LCS strings:");
		for (String s : result) {
			System.out.println(s);
		}
	}

	/*
	 * ============================================================ METHOD 1 :
	 * RECURSION + MEMOIZATION (TOP DOWN DP)
	 * ============================================================
	 *
	 * INTUITION: LCS compares characters from the end of both strings.
	 *
	 * If characters match: include that character in LCS move both pointers left
	 *
	 * If characters don't match: we try two possibilities: 1. ignore character from
	 * first string 2. ignore character from second string
	 *
	 * We take the maximum result.
	 *
	 * Memoization avoids recomputing overlapping subproblems.
	 *
	 * Time Complexity : O(m*n) Space Complexity : O(m*n) + recursion stack
	 */

	public static int lcsMemoization(String x, String y) {

		int m = x.length();
		int n = y.length();

		// memo table initialized with -1
		int[][] memo = new int[m][n];

		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}

		return lcsHelper(x, y, m - 1, n - 1, memo);
	}

	private static int lcsHelper(String x, String y, int i, int j, int[][] memo) {

		// BASE CASE
		// If any string becomes empty
		if (i < 0 || j < 0)
			return 0;

		// If result already computed, return stored value
		if (memo[i][j] != -1)
			return memo[i][j];

		// If characters match
		if (x.charAt(i) == y.charAt(j)) {

			// include this character and move both indices
			memo[i][j] = 1 + lcsHelper(x, y, i - 1, j - 1, memo);
		} else {

			// try skipping one character from either string
			int skipX = lcsHelper(x, y, i - 1, j, memo);
			int skipY = lcsHelper(x, y, i, j - 1, memo);

			memo[i][j] = Math.max(skipX, skipY);
		}

		return memo[i][j];
	}

	/*
	 * ============================================================ METHOD 2 :
	 * TABULATION (BOTTOM UP DP)
	 * ============================================================
	 *
	 * INTUITION:
	 *
	 * Instead of solving recursively, we build a DP table.
	 *
	 * dp[i][j] = LCS length of: first i characters of string X first j characters
	 * of string Y
	 *
	 * If characters match: dp[i][j] = 1 + dp[i-1][j-1]
	 *
	 * If characters don't match: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
	 *
	 * Row 0 and column 0 represent empty strings.
	 *
	 * Time Complexity : O(m*n) Space Complexity : O(m*n)
	 */

	public static int lcsTabulation(String x, String y) {

		int m = x.length();
		int n = y.length();

		// DP table
		int[][] dp = new int[m + 1][n + 1];

		// Build the DP table
		for (int i = 1; i <= m; i++) {

			for (int j = 1; j <= n; j++) {

				// If characters match
				if (x.charAt(i - 1) == y.charAt(j - 1)) {

					// take diagonal value + 1
					dp[i][j] = 1 + dp[i - 1][j - 1];

				} else {

					// take max of top or left
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		// final cell contains answer
		return dp[m][n];
	}

	/*
	 * ============================================================ METHOD 3 : SPACE
	 * OPTIMIZED DP ============================================================
	 *
	 * INTUITION:
	 *
	 * In tabulation we use a full matrix (m+1)*(n+1).
	 *
	 * But if we observe carefully: dp[i][j] depends only on: dp[i-1][j] (previous
	 * row) dp[i][j-1] (current row) dp[i-1][j-1] (previous row)
	 *
	 * So we only need two rows at a time.
	 *
	 * This reduces space from:
	 *
	 * O(m*n) -> O(n)
	 *
	 * Time Complexity : O(m*n) Space Complexity : O(n)
	 */

	public static int lcsSpaceOptimized(String x, String y) {

		int m = x.length();
		int n = y.length();

		// previous row
		int[] prev = new int[n + 1];

		// current row
		int[] curr = new int[n + 1];

		for (int i = 1; i <= m; i++) {

			for (int j = 1; j <= n; j++) {

				// If characters match
				if (x.charAt(i - 1) == y.charAt(j - 1)) {

					curr[j] = 1 + prev[j - 1];

				} else {

					curr[j] = Math.max(prev[j], curr[j - 1]);
				}
			}

			// Move current row to previous for next iteration
			prev = curr.clone();
		}

		return prev[n];
	}

	/*
	 * ============================================================ METHOD 4 : PRINT
	 * ACTUAL LCS STRING
	 * ============================================================
	 *
	 * INTUITION ---------- First build the DP table like tabulation.
	 *
	 * Then start from dp[m][n] and move backwards:
	 *
	 * If characters match: this character is part of LCS move diagonally
	 *
	 * If characters don't match: move to the cell which has larger value (top or
	 * left)
	 *
	 * Continue until i==0 or j==0
	 */

	public static String printLCS(String x, String y) {

		int m = x.length();
		int n = y.length();

		int[][] dp = new int[m + 1][n + 1];

		// Step 1: Build DP table
		for (int i = 1; i <= m; i++) {

			for (int j = 1; j <= n; j++) {

				if (x.charAt(i - 1) == y.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];

				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}

		// Step 2: Reconstruct LCS from DP table
		int i = m;
		int j = n;

		StringBuilder lcs = new StringBuilder();

		while (i > 0 && j > 0) {

			// If characters match, include it in LCS
			if (x.charAt(i - 1) == y.charAt(j - 1)) {

				lcs.append(x.charAt(i - 1));

				i--;
				j--;

			} else if (dp[i - 1][j] > dp[i][j - 1]) {

				// move up
				i--;

			} else {

				// move left
				j--;
			}
		}

		// We built the string backwards, so reverse it
		return lcs.reverse().toString();
	}

	/*
	 * INTUITION ---------- 1. Build the LCS DP table 2. Use recursion to backtrack
	 * from dp[m][n] 3. Collect all possible sequences
	 */

	public static Set<String> printAllLCS(String x, String y) {

		int m = x.length();
		int n = y.length();

		int[][] dp = new int[m + 1][n + 1];

		// Step 1: Build DP table
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {

				if (x.charAt(i - 1) == y.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

			}
		}

		// Step 2: Backtrack to collect all sequences
		return backtrack(x, y, m, n, dp);
	}

	private static Set<String> backtrack(String x, String y, int i, int j, int[][] dp) {

		Set<String> result = new HashSet<>();

		// Base case
		if (i == 0 || j == 0) {
			result.add("");
			return result;
		}

		// If characters match
		if (x.charAt(i - 1) == y.charAt(j - 1)) {

			Set<String> temp = backtrack(x, y, i - 1, j - 1, dp);

			for (String s : temp) {
				result.add(s + x.charAt(i - 1));
			}

		} else {

			// explore up direction
			if (dp[i - 1][j] >= dp[i][j - 1]) {
				result.addAll(backtrack(x, y, i - 1, j, dp));
			}

			// explore left direction
			if (dp[i][j - 1] >= dp[i - 1][j]) {
				result.addAll(backtrack(x, y, i, j - 1, dp));
			}
		}

		return result;
	}
}