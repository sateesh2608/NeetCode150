package com.neetcode150.sliding_window;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q1438LongestSubarrayWithLimit {

    // Method to find the longest continuous subarray
    public static int longestSubarray(int[] nums, int limit) {
        // Deques to maintain max and min in the current window
        Deque<Integer> maxDeque = new ArrayDeque<>(); // decreasing deque
        Deque<Integer> minDeque = new ArrayDeque<>(); // increasing deque

        int left = 0;          // Left pointer of sliding window
        int maxLength = 0;     // To store the maximum length

        // Iterate through the array
        for (int right = 0; right < nums.length; right++) {

            // 1️⃣ Maintain maxDeque (decreasing)
            while (!maxDeque.isEmpty() && nums[right] > nums[maxDeque.peekLast()]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(right);

            // 2️⃣ Maintain minDeque (increasing)
            while (!minDeque.isEmpty() && nums[right] < nums[minDeque.peekLast()]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(right);

            // 3️⃣ Shrink window if invalid
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                // Remove indices that are leaving the window
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                left++; // Move the left pointer to shrink window
            }

            // 4️⃣ Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        // Example test cases
        int[] nums1 = {8, 2, 4, 7};
        int limit1 = 4;
        System.out.println("Test 1 Output: " + longestSubarray(nums1, limit1));
        // Expected Output: 2 → subarray [2,4] or [4,7]

        int[] nums2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;
        System.out.println("Test 2 Output: " + longestSubarray(nums2, limit2));
        // Expected Output: 4 → subarray [2,4,7,2]

        int[] nums3 = {4, 2, 2, 2, 4, 4, 2, 2};
        int limit3 = 0;
        System.out.println("Test 3 Output: " + longestSubarray(nums3, limit3));
        // Expected Output: 3 → longest subarray of equal elements [2,2,2]
    }
}
