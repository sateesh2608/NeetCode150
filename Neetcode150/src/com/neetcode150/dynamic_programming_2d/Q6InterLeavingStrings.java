package com.neetcode150.dynamic_programming_2d;

import java.util.Arrays;

public class Q6InterLeavingStrings {

    // =========================================================
    // 1️⃣ BRUTE FORCE (RECURSION)
    // =========================================================
    /*
    INTUITION:
    At each step, we try to match current character of s3
    with either s1 OR s2.

    If it matches:
        → move pointer in that string

    This forms a recursion tree (2 choices at each step)

    TC: O(2^(m+n))  ❌ exponential
    SC: O(m+n)      recursion stack
    */
    public static boolean bruteForce(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        return dfs(s1, s2, s3, 0, 0);
    }

    private static boolean dfs(String s1, String s2, String s3, int i, int j) {
        if (i == s1.length() && j == s2.length()) return true;

        boolean res = false;

        // take from s1
        if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j)) {
            res = res || dfs(s1, s2, s3, i + 1, j);
        }

        // take from s2
        if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j)) {
            res = res || dfs(s1, s2, s3, i, j + 1);
        }

        return res;
    }


    // =========================================================
    // 2️⃣ MEMOIZATION (TOP-DOWN DP)
    // =========================================================
    /*
    INTUITION:
    Same recursion BUT cache results of (i, j)

    WHY?
    Same state repeats many times in recursion tree

    dp[i][j] = whether s3[i+j...] can be formed

    TC: O(m * n)
    SC: O(m * n)
    */
    public static boolean memoization(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return dfsMemo(s1, s2, s3, 0, 0, dp);
    }

    private static boolean dfsMemo(String s1, String s2, String s3, int i, int j, int[][] dp) {

        if (dp[i][j] != -1) return dp[i][j] == 1;

        if (i == s1.length() && j == s2.length()) return true;

        boolean res = false;

        if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j)) {
            res = res || dfsMemo(s1, s2, s3, i + 1, j, dp);
        }

        if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j)) {
            res = res || dfsMemo(s1, s2, s3, i, j + 1, dp);
        }

        dp[i][j] = res ? 1 : 0;
        return res;
    }


    // =========================================================
    // 3️⃣ TABULATION (BOTTOM-UP DP)
    // =========================================================
    /*
    INTUITION (KEY 🔥):

    dp[i][j] =
    Can we form s3[0 ... i+j-1]
    using s1[0...i-1] and s2[0...j-1]

    TRANSITION:
    From top (i-1, j) → take from s1
    From left (i, j-1) → take from s2

    FORMULA:
    dp[i][j] =
       (dp[i-1][j] && s1[i-1] == s3[i+j-1])
    || (dp[i][j-1] && s2[j-1] == s3[i+j-1])

    TC: O(m * n)
    SC: O(m * n)
    */
    public static boolean tabulation(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();

        if (m + n != s3.length()) return false;

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // first row (only s2)
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] &&
                    s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // first column (only s1)
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] &&
                    s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // fill rest
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                char c = s3.charAt(i + j - 1);

                dp[i][j] =
                        (dp[i - 1][j] && s1.charAt(i - 1) == c) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == c);
            }
        }

        return dp[m][n];
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================
    public static void main(String[] args) {

        String s1 = "ab";
        String s2 = "cd";
        String s3 = "acbd";

        System.out.println("Brute Force: " + bruteForce(s1, s2, s3));
        System.out.println("Memoization: " + memoization(s1, s2, s3));
        System.out.println("Tabulation: " + tabulation(s1, s2, s3));
    }
}


/*
=========================================================
🧪 DRY RUN
=========================================================

s1 = "ab"
s2 = "cd"
s3 = "acbd"

---------------------------------------------------------
BRUTE FORCE TREE (partial)
---------------------------------------------------------

(i=0,j=0) → 'a' matches s3[0]
    (1,0)
        'c' matches s2
        (1,1)
            'b' matches s1
            (2,1)
                'd' matches s2
                (2,2) → TRUE

---------------------------------------------------------
MEMOIZATION
---------------------------------------------------------
States reused:
(1,1), (2,1), etc.

dp[i][j] stores result → avoids recomputation

---------------------------------------------------------
TABULATION MATRIX
---------------------------------------------------------

      j→   0     1     2
           ""    c     d
i ↓
0 ""      T     T     F
1 a       T     T     F
2 b       F     T     T

Final answer = dp[2][2] = TRUE

---------------------------------------------------------
FLOW INTUITION
---------------------------------------------------------
Each cell asks:
"Did I come from TOP (s1) OR LEFT (s2)?"

Example:
dp[2][2]:
    from dp[2][1] (left) → 'd' matches → TRUE

---------------------------------------------------------
FINAL OUTPUT
---------------------------------------------------------
Brute Force: true
Memoization: true
Tabulation: true

=========================================================
KEY TAKEAWAY
=========================================================

Brute Force → explores all paths  
Memoization → avoids recomputation  
Tabulation → builds answers bottom-up  

ALL use SAME logic, just different execution style
=========================================================
*/