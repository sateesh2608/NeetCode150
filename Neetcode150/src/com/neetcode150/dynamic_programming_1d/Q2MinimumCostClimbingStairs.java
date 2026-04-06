package com.neetcode150.dynamic_programming_1d;

public class Q2MinimumCostClimbingStairs {

    public static void main(String[] args) {

        int[] cost = {10, 15, 20};

        Q2MinimumCostClimbingStairs solution = new Q2MinimumCostClimbingStairs();

        System.out.println("Recursion: " + solution.minCostRecursive(cost));
        System.out.println("Memoization: " + solution.minCostMemo(cost));
        System.out.println("Tabulation: " + solution.minCostTabulation(cost));
        System.out.println("Space Optimized: " + solution.minCostOptimized(cost));
    }

    // ------------------------------------------------------------
    // 1️⃣ BRUTE FORCE RECURSION
    // ------------------------------------------------------------
    /*
     * INTUITION:
     * To reach step i,
     * you must come from:
     *    i-1 or i-2
     *
     * So:
     * dp(i) = cost[i] + min(dp(i-1), dp(i-2))
     *
     * Final answer:
     * min(dp(n-1), dp(n-2))
     *
     * PROBLEM:
     * Overlapping subproblems → exponential time
     *
     * Time: O(2^n)
     * Space: O(n) recursion stack
     */
    public int minCostRecursive(int[] cost) {
        int n = cost.length;
        return Math.min(
                recursiveHelper(cost, n - 1),
                recursiveHelper(cost, n - 2)
        );
    }

    private int recursiveHelper(int[] cost, int i) {

        if (i == 0) return cost[0];
        if (i == 1) return cost[1];

        return cost[i] + Math.min(
                recursiveHelper(cost, i - 1),
                recursiveHelper(cost, i - 2)
        );
    }


    // ------------------------------------------------------------
    // 2️⃣ TOP-DOWN DP (MEMOIZATION)
    // ------------------------------------------------------------
    /*
     * INTUITION:
     * Same recurrence as recursion,
     * but store results to avoid recomputation.
     *
     * dp(i) = cost[i] + min(dp(i-1), dp(i-2))
     *
     * Time: O(n)
     * Space: O(n)
     */
    public int minCostMemo(int[] cost) {
        int n = cost.length;
        Integer[] memo = new Integer[n];

        return Math.min(
                memoHelper(cost, n - 1, memo),
                memoHelper(cost, n - 2, memo)
        );
    }

    private int memoHelper(int[] cost, int i, Integer[] memo) {

        if (i == 0) return cost[0];
        if (i == 1) return cost[1];

        if (memo[i] != null)
            return memo[i];

        memo[i] = cost[i] + Math.min(
                memoHelper(cost, i - 1, memo),
                memoHelper(cost, i - 2, memo)
        );

        return memo[i];
    }


    // ------------------------------------------------------------
    // 3️⃣ BOTTOM-UP DP (TABULATION)
    // ------------------------------------------------------------
    /*
     * INTUITION:
     * Build solution from bottom.
     *
     * dp[0] = cost[0]
     * dp[1] = cost[1]
     *
     * For i >= 2:
     * dp[i] = cost[i] + min(dp[i-1], dp[i-2])
     *
     * Final answer:
     * min(dp[n-1], dp[n-2])
     *
     * Time: O(n)
     * Space: O(n)
     */
    public int minCostTabulation(int[] cost) {

        int n = cost.length;

        int[] dp = new int[n];

        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }


    // ------------------------------------------------------------
    // 4️⃣ SPACE OPTIMIZED DP (BEST SOLUTION)
    // ------------------------------------------------------------
    /*
     * INTUITION:
     * dp[i] only depends on dp[i-1] and dp[i-2]
     *
     * So we don't need full array.
     * Just keep last two values.
     *
     * Time: O(n)
     * Space: O(1)
     */
    public int minCostOptimized(int[] cost) {

        int n = cost.length;

        int prev2 = cost[0]; // dp[0]
        int prev1 = cost[1]; // dp[1]

        for (int i = 2; i < n; i++) {

            int current = cost[i] + Math.min(prev1, prev2);

            prev2 = prev1;
            prev1 = current;
        }

        return Math.min(prev1, prev2);
    }
}
