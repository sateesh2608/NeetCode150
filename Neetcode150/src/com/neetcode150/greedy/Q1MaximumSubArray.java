package com.neetcode150.greedy;

import java.util.Arrays;

public class Q1MaximumSubArray {

    /**
     * =========================================================
     * APPROACH 1: Kadane (Greedy / Optimized DP) → O(n)
     * =========================================================
     */
    public static int maxSubArrayGreedy(int[] nums) {

        int bestEnding = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {

            bestEnding = Math.max(nums[i], bestEnding + nums[i]);
            ans = Math.max(ans, bestEnding);
        }

        return ans;
    }


    /**
     * =========================================================
     * APPROACH 2: DP (Tabulation) → O(n)
     * =========================================================
     *
     * INTUITION:
     * dp[i] = maximum subarray sum ending at index i
     *
     * At each index:
     * - either extend previous subarray
     * - or start new from current element
     *
     * WHY THIS WORKS:
     * We store all intermediate states instead of compressing them
     */
    public static int maxSubArrayDP(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];

        // Base case
        dp[0] = nums[0];

        int ans = dp[0];

        for (int i = 1; i < n; i++) {

            /*
             * Option 1: extend previous subarray
             * Option 2: start new from current element
             */
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);

            /*
             * Track global maximum
             */
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }


    public static void main(String[] args) {

        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("Array: " + Arrays.toString(nums1));

        System.out.println("\nGreedy (Kadane): " + maxSubArrayGreedy(nums1));
        System.out.println("DP (Tabulation): " + maxSubArrayDP(nums1));
    }
}