package com.dsa.general_theory.dp.dynamic_programming_2d;

import java.util.Arrays;

public class EditDistance {

    /*
    ============================
    BOTTOM-UP DP (TABULATION)
    ============================
    Already implemented above: minDistance(text1, text2)
    */

    /*
    ============================
    TOP-DOWN DP (RECURSION + MEMOIZATION)
    ============================
    */
    public int minDistanceTopDown(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // memo[i][j] stores edit distance for text1[0..i-1], text2[0..j-1]
        int[][] memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        return helper(text1, text2, m - 1, n - 1, memo);
    }

    private int helper(String s1, String s2, int i, int j, int[][] memo) {
        // base cases
        if (i < 0) return j + 1; // insert all remaining chars of s2
        if (j < 0) return i + 1; // delete all remaining chars of s1

        if (memo[i][j] != -1) return memo[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = helper(s1, s2, i - 1, j - 1, memo); // no operation needed
        } else {
            memo[i][j] = 1 + Math.min(
                helper(s1, s2, i - 1, j - 1, memo), // replace
                Math.min(
                    helper(s1, s2, i - 1, j, memo),   // delete
                    helper(s1, s2, i, j - 1, memo)    // insert
                )
            );
        }
        return memo[i][j];
    }
    

    /*
    ============================
    INTUITION:
    ============================
    Edit Distance asks: "What's the minimum number of operations (insert, delete, replace)
    required to convert text1 to text2?"

    Approach:
    - Use DP to break problem into prefixes.
    - dp[i][j] = min operations to convert text1[0..i-1] → text2[0..j-1]

    Transition:
    1. If chars match: dp[i][j] = dp[i-1][j-1]
    2. Else: dp[i][j] = 1 + min(
         dp[i-1][j]   // delete
         dp[i][j-1]   // insert
         dp[i-1][j-1] // replace
    )

    Base cases:
    - dp[0][j] = j  (insert all chars)
    - dp[i][0] = i  (delete all chars)

    Time & Space: O(m*n)
    */

    public int minDistance(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        // initialize base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1], // replace
                        Math.min(
                            dp[i - 1][j],   // delete
                            dp[i][j - 1]    // insert
                        )
                    );
                }
            }
        }

        return dp[m][n];
    }

    /*
    ============================
    MAIN METHOD (TESTING BOTH)
    ============================
    */
    public static void main(String[] args) {
        EditDistance obj = new EditDistance();

        String text1 = "horse";
        String text2 = "ros";

        int resultBottomUp = obj.minDistance(text1, text2);
        int resultTopDown = obj.minDistanceTopDown(text1, text2);

        System.out.println("Bottom-up DP Edit Distance = " + resultBottomUp);
        System.out.println("Top-down DP Edit Distance = " + resultTopDown);

        // Expected Output for both: 3
    }
}