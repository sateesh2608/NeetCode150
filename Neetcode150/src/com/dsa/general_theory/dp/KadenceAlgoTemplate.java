package com.dsa.general_theory.dp;

public class KadenceAlgoTemplate {
	 public static void main(String[] args) {

	        // Example 1: Maximum Subarray Sum (Kadane)
	        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
	        System.out.println("Max Subarray Sum: " + maxSubArraySum(nums1));

	        // Example 2: Maximum Product Subarray
	        int[] nums2 = {2, 3, -2, 4};
	        System.out.println("Max Product Subarray: " + maxProductSubarray(nums2));

	        // Example 3: Best Time to Buy/Sell Stock (Single Transaction)
	        int[] prices = {7, 1, 5, 3, 6, 4};
	        System.out.println("Max Profit: " + maxProfitStock(prices));

	        // Example 4: House Robber
	        int[] houses = {2, 7, 9, 3, 1};
	        System.out.println("Max Robbed Amount: " + maxRobbery(houses));
	    }


	    // =========================
	    // 1️⃣ Maximum Subarray Sum (Kadane)
	    // =========================
	    public static int maxSubArraySum(int[] nums) {
	        int currentSum = nums[0];
	        int maxSum = nums[0];

	        for (int i = 1; i < nums.length; i++) {
	            currentSum = Math.max(nums[i], currentSum + nums[i]);
	            maxSum = Math.max(maxSum, currentSum);
	        }

	        return maxSum;
	    }

	    // =========================
	    // 2️⃣ Maximum Product Subarray
	    // =========================
	    public static int maxProductSubarray(int[] nums) {
	        int maxEndingHere = nums[0];
	        int minEndingHere = nums[0];
	        int result = nums[0];

	        for (int i = 1; i < nums.length; i++) {
	            int curr = nums[i];

	            // Negative flips max/min
	            if (curr < 0) {
	                int temp = maxEndingHere;
	                maxEndingHere = minEndingHere;
	                minEndingHere = temp;
	            }

	            maxEndingHere = Math.max(curr, maxEndingHere * curr);
	            minEndingHere = Math.min(curr, minEndingHere * curr);

	            result = Math.max(result, maxEndingHere);
	        }

	        return result;
	    }

	    // =========================
	    // 3️⃣ Best Time to Buy/Sell Stock (Single Transaction)
	    // =========================
	    public static int maxProfitStock(int[] prices) {
	        int minPrice = prices[0];
	        int maxProfit = 0;

	        for (int i = 1; i < prices.length; i++) {
	            minPrice = Math.min(minPrice, prices[i]);
	            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
	        }

	        return maxProfit;
	    }

	    // =========================
	    // 4️⃣ House Robber (Take / Skip Pattern)
	    // =========================
	    public static int maxRobbery(int[] nums) {
	        int prev1 = 0; // dp[i-1]
	        int prev2 = 0; // dp[i-2]

	        for (int num : nums) {
	            int temp = prev1;
	            prev1 = Math.max(prev1, prev2 + num); // rob or skip
	            prev2 = temp;
	        }

	        return prev1;
	    }
	}