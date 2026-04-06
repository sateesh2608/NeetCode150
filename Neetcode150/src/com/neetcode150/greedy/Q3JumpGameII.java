package com.neetcode150.greedy;

import java.util.Arrays;

public class Q3JumpGameII {

    /**
     * BFS Level-wise + Greedy
     *
     * INTUITION:
     * - Treat array as levels (like BFS)
     * - Each level = all indices reachable with current number of jumps
     *
     * - l → start of current level
     * - r → end of current level
     *
     * - For all indices in [l, r], compute farthest reachable index
     * - That defines next level
     *
     * WHY GREEDY:
     * We always choose the farthest reachable index
     * to minimize number of levels (jumps)
     *
     * Time: O(n)
     * Space: O(1)
     */
    public static int jump(int[] nums) {

        // Edge case: already at destination
        if (nums.length <= 1) return 0;

        int jumps = 0;
        int l = 0;  // left boundary of level
        int r = 0;  // right boundary of level

        while (r < nums.length - 1) {

            int farthest = 0;

            /*
             * Process current level:
             * Try all indices in range [l, r]
             */
            for (int i = l; i <= r; i++) {

                /*
                 * From index i, we can reach i + nums[i]
                 * Track the maximum reachable index
                 */
                farthest = Math.max(farthest, i + nums[i]);
            }

            /*
             * Move to next level
             * Next level = indices from r+1 to farthest
             */
            l = r + 1;
            r = farthest;

            /*
             * We used one jump to reach next level
             */
            jumps++;
        }

        return jumps;
    }

    public static void main(String[] args) {

        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {2, 3, 0, 1, 4};

        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Min jumps = " + jump(nums1)); // 2

        System.out.println("\nArray: " + Arrays.toString(nums2));
        System.out.println("Min jumps = " + jump(nums2)); // 2
    }
}