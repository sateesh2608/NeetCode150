package com.neetcode150.dynamic_programming_1d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Q11LongestIncreasingSubsequence {

    // ===================== MAIN =====================
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println("Brute Force: " + lisBrute(nums));
        System.out.println("Memoization: " + lisMemo(nums));
        System.out.println("Tabulation: " + lisTab(nums));
        System.out.println("Optimal (Binary Search): " + lisOptimal(nums));

        dryRun();
    }

    // ===================== BRUTE FORCE =====================
    /*
     Intuition:
     At each index:
        - take it (if valid increasing)
        - skip it
     Track previous index

     Time: O(2^n)
    */
    public static int lisBrute(int[] nums) {
        return helperBrute(nums, 0, -1);
    }

    private static int helperBrute(int[] nums, int index, int prevIndex) {
        if (index == nums.length) return 0;

        // not take
        int notTake = helperBrute(nums, index + 1, prevIndex);

        // take
        int take = 0;
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            take = 1 + helperBrute(nums, index + 1, index);
        }

        return Math.max(take, notTake);
    }

    // ===================== MEMOIZATION =====================
    /*
     dp[index][prevIndex+1]
     (prevIndex = -1 handled by shifting +1)

     Time: O(n^2)
    */
    public static int lisMemo(int[] nums) {
        int n = nums.length;
        Integer[][] dp = new Integer[n][n + 1];

        return helperMemo(nums, 0, -1, dp);
    }

    private static int helperMemo(int[] nums, int index, int prevIndex, Integer[][] dp) {
        if (index == nums.length) return 0;

        if (dp[index][prevIndex + 1] != null)
            return dp[index][prevIndex + 1];

        int notTake = helperMemo(nums, index + 1, prevIndex, dp);

        int take = 0;
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            take = 1 + helperMemo(nums, index + 1, index, dp);
        }

        return dp[index][prevIndex + 1] = Math.max(take, notTake);
    }

    // ===================== TABULATION =====================
    /*
     dp[i] = LIS ending at index i

     Transition:
     dp[i] = max(dp[j] + 1) for all j < i where nums[j] < nums[i]

     Time: O(n^2)
    */
    public static int lisTab(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    // ===================== OPTIMAL (BINARY SEARCH) =====================
    /*
     Maintain a list:
     tails[i] = smallest ending element of LIS of length i+1

     Use binary search to replace elements

     Time: O(n log n)
    */
    public static int lisOptimal(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            int idx = Collections.binarySearch(list, num);

            if (idx < 0) idx = -(idx + 1);

            if (idx == list.size()) {
                list.add(num);
            } else {
                list.set(idx, num);
            }
        }

        return list.size();
    }

    // ===================== DRY RUN =====================
    /*
     Example: [10, 9, 2, 5, 3, 7, 101, 18]

     Optimal method walkthrough:

     Start: []

     10 → [10]
     9  → [9]          (replace 10)
     2  → [2]
     5  → [2,5]
     3  → [2,3]
     7  → [2,3,7]
     101→ [2,3,7,101]
     18 → [2,3,7,18]

     Length = 4

     LIS = [2,3,7,101] (one possible answer)
    */
    public static void dryRun() {
        System.out.println("\n--- Dry Run ---");
        System.out.println("Input: [10,9,2,5,3,7,101,18]");
        System.out.println("LIS length = 4");
    }
}
