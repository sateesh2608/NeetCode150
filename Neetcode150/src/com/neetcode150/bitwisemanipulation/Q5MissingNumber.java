package com.neetcode150.bitwisemanipulation;

import java.util.Arrays;

public class Q5MissingNumber {

    public static void main(String[] args) {
        int[] nums1 = {3, 0, 1};
        int[] nums2 = {0, 1};
        int[] nums3 = {9,6,4,2,3,5,7,0,1};

        System.out.println("Missing number (nums1): " + missingNumberBruteForce(nums1));
        System.out.println("Missing number (nums2): " + missingNumberOptimal(nums2));
        System.out.println("Missing number (nums3): " + missingNumberOptimal(nums3));
    }

    // =========================================================
    // 1️⃣ Brute-force Approach (Sorting / Checking)
    // =========================================================
    /*
     * Intuition:
     * - Sort the array.
     * - Check which number in the range 0..n is missing.
     *
     * Time Complexity: O(n log n) due to sorting
     * Space Complexity: O(1) if in-place sorting
     */
    public static int missingNumberBruteForce(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) return i;
        }
        return n; // if no mismatch, missing number is n
    }

    // =========================================================
    // 2️⃣ Optimal Approach (Using XOR)
    // =========================================================
    /*
     * Intuition:
     * - XOR all numbers from 0..n and all numbers in the array.
     * - Duplicate numbers cancel out: a ^ a = 0
     * - The remaining number is the missing one.
     *
     * Example:
     * nums = [3,0,1], n = 3
     * XOR 0^1^2^3 = 0^1^2^3
     * XOR array elements: 3^0^1
     * Result: (0^1^2^3) ^ (3^0^1) = 2 -> missing number
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int missingNumberOptimal(int[] nums) {
        int n = nums.length;
        int xor = 0;

        // XOR all indices 0..n
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }

        // XOR all numbers in array
        for (int num : nums) {
            xor ^= num;
        }

        return xor; // remaining number is missing
    }

    // =========================================================
    // 3️⃣ Alternative Optimal Approach (Sum Formula)
    // =========================================================
    /*
     * Intuition:
     * - Sum of 0..n = n*(n+1)/2
     * - Missing number = sum(0..n) - sum(array)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int missingNumberSum(int[] nums) {
        int n = nums.length;
        int totalSum = n * (n + 1) / 2;
        int arraySum = 0;
        for (int num : nums) arraySum += num;
        return totalSum - arraySum;
    }
}