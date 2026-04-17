package com.neetcode150.dynamic_programming_1d;

import java.util.Arrays;

public class Q12PartitionEqualSubsetSum {

    // ===================== MAIN METHOD =====================
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};

        System.out.println("Brute Force: " + canPartitionBrute(nums));
        System.out.println("Memoization: " + canPartitionMemo(nums));
        System.out.println("Tabulation: " + canPartitionTab(nums));
        System.out.println("Space Optimized: " + canPartitionOptimal(nums));

        dryRunExample();
    }

    // ===================== BRUTE FORCE =====================
    /*
     Intuition:
     Try all subsets → for each element:
        - pick it
        - don't pick it
     Check if we can reach target sum = totalSum/2
     Time: O(2^n)
    */
    public static boolean canPartitionBrute(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;

        return helperBrute(nums, nums.length - 1, sum / 2);
    }

    private static boolean helperBrute(int[] nums, int index, int target) {
        // base cases
        if (target == 0) return true;
        if (index == 0) return nums[0] == target;

        // not take
        boolean notTake = helperBrute(nums, index - 1, target);

        // take
        boolean take = false;
        if (nums[index] <= target) {
            take = helperBrute(nums, index - 1, target - nums[index]);
        }

        return take || notTake;
    }

    // ===================== MEMOIZATION =====================
    /*
     Same as brute, but store results
     dp[index][target] = result
     Time: O(n * target)
    */
    public static boolean canPartitionMemo(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        Boolean[][] dp = new Boolean[nums.length][target + 1];

        return helperMemo(nums, nums.length - 1, target, dp);
    }

    private static boolean helperMemo(int[] nums, int index, int target, Boolean[][] dp) {
        if (target == 0) return true;
        if (index == 0) return nums[0] == target;

        if (dp[index][target] != null) return dp[index][target];

        boolean notTake = helperMemo(nums, index - 1, target, dp);

        boolean take = false;
        if (nums[index] <= target) {
            take = helperMemo(nums, index - 1, target - nums[index], dp);
        }

        return dp[index][target] = take || notTake;
    }

    // ===================== TABULATION =====================
    /*
     Bottom-up DP
     dp[i][t] = can we form sum t using first i elements?
    */
    public static boolean canPartitionTab(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;

        int n = nums.length;
        int target = sum / 2;

        boolean[][] dp = new boolean[n][target + 1];

        // base cases
        for (int i = 0; i < n; i++) dp[i][0] = true;

        if (nums[0] <= target)
            dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= target; t++) {

                boolean notTake = dp[i - 1][t];

                boolean take = false;
                if (nums[i] <= t) {  // IMPORTANT FIX
                    take = dp[i - 1][t - nums[i]];
                }

                dp[i][t] = take || notTake;
            }
        }

        return dp[n - 1][target];
    }

    // ===================== SPACE OPTIMIZED =====================
    /*
     We only need previous row → use 1D array
     Traverse backwards to avoid overwriting
    */
    public static boolean canPartitionOptimal(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (nums[0] <= target)
            dp[nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int t = target; t >= 0; t--) {

                boolean notTake = dp[t];

                boolean take = false;
                if (nums[i] <= t) {
                    take = dp[t - nums[i]];
                }

                dp[t] = take || notTake;
            }
        }

        return dp[target];
    }

    // ===================== DRY RUN =====================
    /*
     Example: nums = [1, 5, 11, 5]

     Total sum = 22 → target = 11

     Goal: Find subset with sum = 11

     Steps (Tabulation idea):

     Start:
     dp[0][1] = true

     After processing 1:
     possible sums = {0,1}

     After 5:
     possible sums = {0,1,5,6}

     After 11:
     possible sums include 11 → FOUND

     So answer = true

     Subset example:
     {11} and {1,5,5}
    */
    public static void dryRunExample() {
        System.out.println("\n--- Dry Run Explanation ---");
        System.out.println("Input: [1, 5, 11, 5]");
        System.out.println("Total Sum = 22 → Target = 11");
        System.out.println("We try to form subset sum = 11");
        System.out.println("We succeed → Partition possible");
    }
}
