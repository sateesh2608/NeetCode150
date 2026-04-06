package com.neetcode150.dynamic_programming_1d;

public class Q4houseRobberII {

	    public static void main(String[] args) {

	        int[] nums = {2, 3, 2, 5, 1};

	        Q4houseRobberII solution = new Q4houseRobberII();

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
	     * Circular houses → cannot rob first and last together.
	     *
	     * So split into two linear problems:
	     *
	     * Case 1: 0 → n-2
	     * Case 2: 1 → n-1
	     *
	     * For linear:
	     *
	     * At each index i:
	     *  - Rob → nums[i] + solve(i-2)
	     *  - Skip → solve(i-1)
	     *
	     * Take max.
	     *
	     * Time: O(2^n)
	     * Space: O(n) recursion stack
	     */
	    public int robRecursive(int[] nums) {

	        int n = nums.length;
	        if (n == 1) return nums[0];

	        return Math.max(
	                recursive(nums, 0, n - 2),
	                recursive(nums, 1, n - 1)
	        );
	    }

	    private int recursive(int[] nums, int start, int end) {
	        return helperRecursive(nums, end, start);
	    }

	    private int helperRecursive(int[] nums, int i, int start) {

	        if (i < start) return 0;

	        return Math.max(
	                nums[i] + helperRecursive(nums, i - 2, start),
	                helperRecursive(nums, i - 1, start)
	        );
	    }


	    // ============================================================
	    // 2️⃣ MEMOIZATION (Top-Down DP)
	    // ============================================================
	    /*
	     * INTUITION:
	     *
	     * Same as recursion but store results to avoid recomputation.
	     *
	     * dp[i] = max(
	     *      nums[i] + dp[i-2],
	     *      dp[i-1]
	     * )
	     *
	     * Store values in memo array.
	     *
	     * Time: O(n)
	     * Space: O(n)
	     */
	    public int robMemo(int[] nums) {

	        int n = nums.length;
	        if (n == 1) return nums[0];

	        return Math.max(
	                memoSolve(nums, 0, n - 2),
	                memoSolve(nums, 1, n - 1)
	        );
	    }

	    private int memoSolve(int[] nums, int start, int end) {

	        Integer[] memo = new Integer[nums.length];
	        return memoHelper(nums, end, start, memo);
	    }

	    private int memoHelper(int[] nums, int i, int start, Integer[] memo) {

	        if (i < start) return 0;

	        if (memo[i] != null) return memo[i];

	        memo[i] = Math.max(
	                nums[i] + memoHelper(nums, i - 2, start, memo),
	                memoHelper(nums, i - 1, start, memo)
	        );

	        return memo[i];
	    }


	    // ============================================================
	    // 3️⃣ TABULATION (Bottom-Up DP)
	    // ============================================================
	    /*
	     * INTUITION:
	     *
	     * Instead of recursion, build solution from left to right.
	     *
	     * dp[i] = max(
	     *      dp[i-1],              // skip
	     *      nums[i] + dp[i-2]     // rob
	     * )
	     *
	     * Since circular:
	     * compute twice and take max.
	     *
	     * Time: O(n)
	     * Space: O(n)
	     */
	    public int robTabulation(int[] nums) {

	        int n = nums.length;
	        if (n == 1) return nums[0];

	        return Math.max(
	                tabulation(nums, 0, n - 2),
	                tabulation(nums, 1, n - 1)
	        );
	    }

	    private int tabulation(int[] nums, int start, int end) {

	        int length = end - start + 1;
	        int[] dp = new int[length];

	        dp[0] = nums[start];

	        if (length == 1) return dp[0];

	        dp[1] = Math.max(nums[start], nums[start + 1]);

	        for (int i = 2; i < length; i++) {

	            dp[i] = Math.max(
	                    dp[i - 1],
	                    dp[i - 2] + nums[start + i]
	            );
	        }

	        return dp[length - 1];
	    }


	    // ============================================================
	    // 4️⃣ SPACE OPTIMIZED (Best Solution)
	    // ============================================================
	    /*
	     * INTUITION:
	     *
	     * dp[i] only depends on:
	     *  - dp[i-1]
	     *  - dp[i-2]
	     *
	     * So no need full array.
	     *
	     * Keep:
	     *  prev1 = dp[i-1]
	     *  prev2 = dp[i-2]
	     *
	     * current = max(prev1, prev2 + nums[i])
	     *
	     * Update and continue.
	     *
	     * Circular handled same way: solve twice.
	     *
	     * Time: O(n)
	     * Space: O(1)
	     */
	    public int robOptimized(int[] nums) {

	        int n = nums.length;
	        if (n == 1) return nums[0];

	        return Math.max(
	                optimized(nums, 0, n - 2),
	                optimized(nums, 1, n - 1)
	        );
	    }

	    private int optimized(int[] nums, int start, int end) {

	        int prev2 = 0;
	        int prev1 = 0;

	        for (int i = start; i <= end; i++) {

	            int current = Math.max(prev1, prev2 + nums[i]);

	            prev2 = prev1;
	            prev1 = current;
	        }

	        return prev1;
	    }
	}

