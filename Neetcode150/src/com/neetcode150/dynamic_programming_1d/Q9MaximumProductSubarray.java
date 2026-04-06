package com.neetcode150.dynamic_programming_1d;

public class Q9MaximumProductSubarray {

    public static void main(String[] args) {

        int[] nums = {2, 3, -2, 4};

        System.out.println("DP Solution: " + maxProductDP(nums));
        System.out.println("Prefix/Suffix Solution: " + maxProductPrefix(nums));
    }


    /*
     * ==========================================================
     * 1️⃣ Dynamic Programming Solution
     * ==========================================================
     *
     * Intuition:
     *
     * At every index we track two values:
     *
     * maxEndingHere → maximum product subarray ending at index i
     * minEndingHere → minimum product subarray ending at index i
     *
     * Why track minimum?
     *
     * Because if we multiply a negative number with a negative
     * minimum product, it may become the new maximum product.
     *
     * Example:
     *
     * nums = [-2, 3, -4]
     *
     * i=0 → max=-2 , min=-2
     *
     * i=1 → 3
     * max = max(3, -2*3) = 3
     * min = min(3, -2*3) = -6
     *
     * i=2 → -4
     *
     * multiply with previous:
     *
     * max = max(-4, 3*-4, -6*-4)
     *     = max(-4, -12, 24)
     *     = 24
     *
     * So the minimum (-6) becomes the maximum.
     *
     * This is why we track both values.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     */

    public static int maxProductDP(int[] nums) {

        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int current = nums[i];

            /*
             * If the current number is negative,
             * max and min swap roles because
             * multiplication flips the sign.
             */
            if (current < 0) {
                int temp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }

            /*
             * Either start a new subarray from current element
             * OR extend the previous product
             */
            maxEndingHere = Math.max(current, maxEndingHere * current);
            minEndingHere = Math.min(current, minEndingHere * current);

            /*
             * Update global maximum
             */
            result = Math.max(result, maxEndingHere);
        }

        return result;
    }



    /*
     * ==========================================================
     * 2️⃣ Prefix + Suffix Product Trick
     * ==========================================================
     *
     * Intuition:
     *
     * If an array has an odd number of negatives,
     * the maximum product will be obtained by removing:
     *
     * prefix until first negative
     * OR
     * suffix after last negative
     *
     * So we calculate:
     *
     * prefix product (left → right)
     * suffix product (right → left)
     *
     * and take the maximum.
     *
     * Example:
     *
     * nums = [2, -5, -2, -4, 3]
     *
     * prefix scan
     * suffix scan
     *
     * One of them will skip the problematic negative.
     *
     * Also if product becomes 0 we reset to 1
     * because zero breaks the chain.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(1)
     */

    public static int maxProductPrefix(int[] nums) {

        int result = Integer.MIN_VALUE;

        int leftProduct = 1;
        int rightProduct = 1;

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            /*
             * If product becomes 0,
             * restart multiplication
             */
            if (leftProduct == 0) leftProduct = 1;
            if (rightProduct == 0) rightProduct = 1;

            /*
             * Prefix multiplication
             */
            leftProduct *= nums[i];

            /*
             * Suffix multiplication
             */
            rightProduct *= nums[n - 1 - i];

            /*
             * Track maximum
             */
            result = Math.max(result, Math.max(leftProduct, rightProduct));
        }

        return result;
    }
}