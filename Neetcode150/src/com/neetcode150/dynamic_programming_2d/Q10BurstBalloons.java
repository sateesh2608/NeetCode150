package com.neetcode150.dynamic_programming_2d;

public class Q10BurstBalloons {

    // ===================== MAIN =====================
    public static void main(String[] args) {

        int[] nums = {3,1,5,8};

        System.out.println("Brute Force: " + brute(nums));
        System.out.println("Memoization: " + memo(nums));
        System.out.println("Tabulation: " + tabulation(nums));

        dryRun();
    }

    // ===================== PREPROCESS =====================
    private static int[] prepare(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n+1] = 1;

        for(int i=0;i<n;i++){
            arr[i+1] = nums[i];
        }
        return arr;
    }

    // ===================== 1. BRUTE FORCE =====================
    /*
     Try all possible last balloons

     Time: O(n!)
     Space: O(n)
    */
    public static int brute(int[] nums) {

        int[] arr = prepare(nums);
        int n = arr.length;

        return dfs(1, n-2, arr);
    }

    private static int dfs(int i, int j, int[] arr){

        if(i > j) return 0;

        int max = 0;

        for(int k = i; k <= j; k++){

            int coins = arr[i-1] * arr[k] * arr[j+1]
                      + dfs(i, k-1, arr)
                      + dfs(k+1, j, arr);

            max = Math.max(max, coins);
        }

        return max;
    }

    // ===================== 2. MEMOIZATION =====================
    /*
     dp[i][j] = max coins from i..j

     Time: O(n^3)
     Space: O(n^2)
    */
    public static int memo(int[] nums) {

        int[] arr = prepare(nums);
        int n = arr.length;

        int[][] dp = new int[n][n];

        return dfsMemo(1, n-2, arr, dp);
    }

    private static int dfsMemo(int i, int j, int[] arr, int[][] dp){

        if(i > j) return 0;

        if(dp[i][j] != 0) return dp[i][j];

        int max = 0;

        for(int k = i; k <= j; k++){

            int coins = arr[i-1] * arr[k] * arr[j+1]
                      + dfsMemo(i, k-1, arr, dp)
                      + dfsMemo(k+1, j, arr, dp);

            max = Math.max(max, coins);
        }

        return dp[i][j] = max;
    }

    // ===================== 3. TABULATION =====================
    /*
     Interval DP

     dp[i][j] = max coins for subarray i..j

     Time: O(n^3)
     Space: O(n^2)
    */
    public static int tabulation(int[] nums){

        int[] arr = prepare(nums);
        int n = arr.length;

        int[][] dp = new int[n][n];

        // length of interval
        for(int len = 1; len <= n-2; len++){

            for(int i = 1; i + len - 1 <= n-2; i++){

                int j = i + len - 1;

                for(int k = i; k <= j; k++){

                    int coins = arr[i-1] * arr[k] * arr[j+1]
                              + dp[i][k-1]
                              + dp[k+1][j];

                    dp[i][j] = Math.max(dp[i][j], coins);
                }
            }
        }

        return dp[1][n-2];
    }

    // ===================== DRY RUN =====================
    /*
     nums = [3,1,5,8]

     padded = [1,3,1,5,8,1]

     Best order:
     Burst 1 → 5 → 3 → 8

     Max coins = 167
    */
    public static void dryRun(){
        System.out.println("\n--- Dry Run ---");
        System.out.println("nums = [3,1,5,8]");
        System.out.println("Output = 167");
    }
}
