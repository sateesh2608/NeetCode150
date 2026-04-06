package com.neetcode150.dynamic_programming_1d;

public class Q1ClimbingStairs {

    public static void main(String[] args) {

        int n = 5;

        Q1ClimbingStairs solution = new Q1ClimbingStairs();

        System.out.println("Brute Force Recursion: " + solution.climbStairsRecursive(n));
        System.out.println("Top-Down DP (Memoization): " + solution.climbStairsMemo(n));
        System.out.println("Bottom-Up DP (Tabulation): " + solution.climbStairsTabulation(n));
        System.out.println("Space Optimized DP: " + solution.climbStairsOptimized(n));
    }

    // ------------------------------------------------------------
    // 1️⃣ BRUTE FORCE RECURSION
    // ------------------------------------------------------------
    /*
     * INTUITION:
     * At every step, you have 2 choices:
     *   - Take 1 step
     *   - Take 2 steps
     *
     * So total ways to reach step n =
     * ways to reach (n-1) + ways to reach (n-2)
     *
     * This forms a Fibonacci-like recurrence:
     * f(n) = f(n-1) + f(n-2)
     *
     * PROBLEM:
     * This recalculates same subproblems multiple times.
     * Time Complexity: O(2^n)  ❌ (very slow)
     * Space Complexity: O(n)   (recursion stack)
     */
    public int climbStairsRecursive(int n) {

        if (n == 0 || n == 1)
            return 1;

        return climbStairsRecursive(n - 1)
                + climbStairsRecursive(n - 2);
    }


    // ------------------------------------------------------------
    // 2️⃣ TOP-DOWN DP (MEMOIZATION)
    // ------------------------------------------------------------
    /*
     * INTUITION:
     * Same recursive thinking as brute force,
     * BUT we store previously computed results.
     *
     * If we already solved f(n), we reuse it.
     *
     * This avoids recomputation of overlapping subproblems.
     *
     * Time Complexity: O(n)   ✅
     * Space Complexity: O(n)  (memo array + recursion stack)
     */
    public int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        return memoHelper(n, memo);
    }

    private int memoHelper(int n, int[] memo) {

        if (n == 0 || n == 1)
            return 1;

        // If already computed, return stored value
        if (memo[n] != 0)
            return memo[n];

        memo[n] = memoHelper(n - 1, memo)
                + memoHelper(n - 2, memo);

        return memo[n];
    }


    // ------------------------------------------------------------
    // 3️⃣ BOTTOM-UP DP (TABULATION)
    // ------------------------------------------------------------
    /*
     * INTUITION:
     * Instead of solving recursively from top,
     * we build solution from bottom.
     *
     * We know:
     * f(0) = 1
     * f(1) = 1
     *
     * Then compute:
     * f(2), f(3), ..., f(n)
     *
     * Each value depends only on previous two values.
     *
     * Time Complexity: O(n)   ✅
     * Space Complexity: O(n)
     */
    public int climbStairsTabulation(int n) {

        if (n == 0 || n == 1)
            return 1;

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    // ------------------------------------------------------------
    // 4️⃣ SPACE OPTIMIZED DP
    // ------------------------------------------------------------
    /*
     * INTUITION:
     * We observe that:
     * f(n) only depends on f(n-1) and f(n-2)
     *
     * So we don’t need full array.
     * Just keep track of last two values.
     *
     * This reduces space from O(n) to O(1).
     *
     * Time Complexity: O(n)   ✅
     * Space Complexity: O(1)  🔥
     */
    public int climbStairsOptimized(int n) {

        if (n == 0 || n == 1)
            return 1;

        int prev2 = 1;  // f(0)
        int prev1 = 1;  // f(1)

        for (int i = 2; i <= n; i++) {

            int current = prev1 + prev2;

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}