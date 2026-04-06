package com.neetcode150.dynamic_programming_1d;

public class Q3HouseRobber {

	    public static void main(String[] args) {

	        int[] nums = {2, 7, 9, 3, 1};

	        Q3HouseRobber solution = new Q3HouseRobber();

	        System.out.println("Recursion: " + solution.robRecursive(nums));
	        System.out.println("Memoization: " + solution.robMemo(nums));
	        System.out.println("Tabulation: " + solution.robTabulation(nums));
	        System.out.println("Space Optimized: " + solution.robOptimized(nums));
	    }


	    // ============================================================
	    // 1️⃣ RECURSION (Exponential - For Understanding)
	    // ============================================================
	    /*
	     * INTUITION:
	     *
	     * Houses are in a straight line.
	     *
	     * At each index i:
	     *   You have 2 choices:
	     *
	     *   1️⃣ Rob this house
	     *        → nums[i] + solve(i-2)
	     *
	     *   2️⃣ Skip this house
	     *        → solve(i-1)
	     *
	     * Recurrence:
	     *
	     * dp[i] = max(
	     *      nums[i] + dp[i-2],
	     *      dp[i-1]
	     * )
	     *
	     * Base cases:
	     *   i < 0 → 0
	     *
	     * Time: O(2^n)
	     * Space: O(n) recursion stack
	     */
	    public int robRecursive(int[] nums) {
	        return helperRecursive(nums, nums.length - 1);
	    }

	    private int helperRecursive(int[] nums, int i) {

	        if (i < 0) return 0;

	        return Math.max(
	                nums[i] + helperRecursive(nums, i - 2),
	                helperRecursive(nums, i - 1)
	        );
	    }


	    // ============================================================
	    // 2️⃣ MEMOIZATION (Top-Down DP)
	    // ============================================================
	    /*
	     * INTUITION:
	     *
	     * Same recurrence as recursion.
	     *
	     * Store results in memo array to avoid recomputation.
	     *
	     * If memo[i] already computed → reuse it.
	     *
	     * Time: O(n)
	     * Space: O(n)
	     */
	    public int robMemo(int[] nums) {

	        Integer[] memo = new Integer[nums.length];
	        return memoHelper(nums, nums.length - 1, memo);
	    }

	    private int memoHelper(int[] nums, int i, Integer[] memo) {

	        if (i < 0) return 0;

	        if (memo[i] != null) return memo[i];

	        memo[i] = Math.max(
	                nums[i] + memoHelper(nums, i - 2, memo),
	                memoHelper(nums, i - 1, memo)
	        );

	        return memo[i];
	    }


	    // ============================================================
	    // 3️⃣ TABULATION (Bottom-Up DP)
	    // ============================================================
	    /*
	     * INTUITION:
	     *
	     * Build solution from left to right.
	     *
	     * dp[0] = nums[0]
	     * dp[1] = max(nums[0], nums[1])
	     *
	     * For i >= 2:
	     *
	     * dp[i] = max(
	     *      dp[i-1],            // skip
	     *      nums[i] + dp[i-2]   // rob
	     * )
	     *
	     * Final answer = dp[n-1]
	     *
	     * Time: O(n)
	     * Space: O(n)
	     */
	    public int robTabulation(int[] nums) {

	        int n = nums.length;

	        if (n == 1) return nums[0];

	        int[] dp = new int[n];

	        dp[0] = nums[0];
	        dp[1] = Math.max(nums[0], nums[1]);

	        for (int i = 2; i < n; i++) {

	            dp[i] = Math.max(
	                    dp[i - 1],
	                    dp[i - 2] + nums[i]
	            );
	        }

	        return dp[n - 1];
	    }


	    // ============================================================
	    // 4️⃣ SPACE OPTIMIZED (Best Solution)
	    // ============================================================
	    /*
	     * INTUITION:
	     *
	     * dp[i] only depends on:
	     *   dp[i-1]
	     *   dp[i-2]
	     *
	     * So we don't need entire dp array.
	     *
	     * Maintain:
	     *   prev1 = dp[i-1]
	     *   prev2 = dp[i-2]
	     *
	     * current = max(prev1, prev2 + nums[i])
	     *
	     * Shift values forward.
	     *
	     * Time: O(n)
	     * Space: O(1)
	     */
	    public int robOptimized(int[] nums) {

	        int prev2 = 0;
	        int prev1 = 0;

	        for (int num : nums) {

	            int current = Math.max(prev1, prev2 + num);

	            prev2 = prev1;
	            prev1 = current;
	        }

	        return prev1;
	    }
	}