package com.neetcode150.dynamic_programming_2d;

public class Q7EditDistance{

    public static void main(String[] args) {

        String word1 = "horse";
        String word2 = "ros";

        System.out.println("Recursion: " + minDistanceRec(word1, word2));

        System.out.println("Memoization: " + minDistanceMemo(word1, word2));

        System.out.println("Tabulation: " + minDistanceTab(word1, word2));
    }

    // ------------------------------------------------------------
    // 1. PURE RECURSION (Exponential)
    // ------------------------------------------------------------
    /*
    DRY RUN (word1="ab", word2="ac")

    f(1,1) → 'b' != 'c'
        insert  → f(1,0)
        delete  → f(0,1)
        replace → f(0,0)

    replace path:
        f(0,0) → 'a' == 'a'
            → f(-1,-1) = 0

    answer = 1
    */

    public static int minDistanceRec(String w1, String w2) {
        return solveRec(w1, w2, w1.length() - 1, w2.length() - 1);
    }

    private static int solveRec(String w1, String w2, int i, int j) {

        // BASE CASES
        if (i < 0) return j + 1; // insert all remaining chars of w2
        if (j < 0) return i + 1; // delete all remaining chars of w1

        // MATCH
        if (w1.charAt(i) == w2.charAt(j)) {
            return solveRec(w1, w2, i - 1, j - 1);
        }

        // NOT MATCH → try all operations
        int insert = solveRec(w1, w2, i, j - 1);     // insert
        int delete = solveRec(w1, w2, i - 1, j);     // delete
        int replace = solveRec(w1, w2, i - 1, j - 1);// replace

        return 1 + Math.min(replace, Math.min(insert, delete));
    }


    // ------------------------------------------------------------
    // 2. MEMOIZATION (Top-Down DP)
    // ------------------------------------------------------------
    /*
    IDEA:
    Store results of f(i,j) so we don't recompute.

    Example overlap:
    f(0,0) is computed multiple times in recursion tree
    */

    public static int minDistanceMemo(String w1, String w2) {

        int m = w1.length();
        int n = w2.length();

        int[][] dp = new int[m][n];

        // initialize with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return solveMemo(w1, w2, m - 1, n - 1, dp);
    }

    private static int solveMemo(String w1, String w2, int i, int j, int[][] dp) {

        // BASE CASES
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;

        // CHECK CACHE
        if (dp[i][j] != -1) return dp[i][j];

        // MATCH
        if (w1.charAt(i) == w2.charAt(j)) {
            return dp[i][j] = solveMemo(w1, w2, i - 1, j - 1, dp);
        }

        // NOT MATCH
        int insert = solveMemo(w1, w2, i, j - 1, dp);
        int delete = solveMemo(w1, w2, i - 1, j, dp);
        int replace = solveMemo(w1, w2, i - 1, j - 1, dp);

        return dp[i][j] = 1 + Math.min(replace, Math.min(insert, delete));
    }


    // ------------------------------------------------------------
    // 3. TABULATION (Bottom-Up DP)
    // ------------------------------------------------------------
    /*
    BUILD TABLE FROM SMALL → BIG

    Example: word1="horse", word2="ros"

    dp[i][j] = answer for first i chars of word1 and j chars of word2

    BASE:
    dp[0][j] = j  (insert all)
    dp[i][0] = i  (delete all)

    DRY RUN (partial table):

        ""  r  o  s
    ""  0  1  2  3
    h   1
    o   2
    r   3
    s   4
    e   5

    Fill row by row
    */

    public static int minDistanceTab(String w1, String w2) {

        int m = w1.length();
        int n = w2.length();

        int[][] dp = new int[m + 1][n + 1];

        // BASE CASES
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 0; i <= m; i++) dp[i][0] = i;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // MATCH
                if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                // NOT MATCH
                else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(replace, Math.min(insert, delete));
                }
            }
        }

        return dp[m][n];
    }
}