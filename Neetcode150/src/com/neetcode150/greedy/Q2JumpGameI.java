package com.neetcode150.greedy;

import java.util.Arrays;

public class Q2JumpGameI {

    /**
     * Greedy approach:
     * 1) Track the farthest index reachable at any step
     * 2) Iterate through the array
     * 3) If current index > farthest reachable → cannot proceed
     * 4) If farthest reachable >= last index → success
     *
     * Time: O(n)
     * Space: O(1)
     */
    public static boolean canJump(int[] nums) {
        int farthest = 0;  // farthest index reachable

        for (int i = 0; i < nums.length; i++) {

            // EXPLANATION:
            // If current index is beyond what we can reach, we fail
            if (i > farthest) {
                return false;
            }

            // Update farthest index reachable
            farthest = Math.max(farthest, i + nums[i]);

            // Optional early exit if we can already reach the end
            if (farthest >= nums.length - 1) {
                return true;
            }
        }

        // If loop completes, check if we reached last index
        return farthest >= nums.length - 1;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4};
        int[] nums2 = {3,2,1,0,4};

        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Can jump to end? " + canJump(nums1)); // true

        System.out.println("\nArray: " + Arrays.toString(nums2));
        System.out.println("Can jump to end? " + canJump(nums2)); // false
    }
}