package com.dsa.general_theory.greedy;

import java.util.ArrayList;
import java.util.List;

public class MergeTripletsSolutions {

    /**
     * =========================================================
     * Variant 1: Unlimited Merges (Original LeetCode 1899)
     * =========================================================
     *
     * INTUITION:
     * - We can merge ANY number of triplets.
     * - Each index of the target can come from a different triplet.
     * - So we only need to check if each target value can be obtained from a valid triplet.
     * - Skip triplets exceeding the target, since they cannot be used.
     *
     * TC: O(n)  → iterate through triplets once
     * SC: O(1)
     */
    public static boolean mergeUnlimited(int[][] triplets, int[] target) {

        boolean match0 = false; // track if we can achieve target[0]
        boolean match1 = false; // track if we can achieve target[1]
        boolean match2 = false; // track if we can achieve target[2]

        for (int[] t : triplets) {
            // Ignore triplets that exceed target values
            if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) continue;

            // Check which indices match the target
            if (t[0] == target[0]) match0 = true;
            if (t[1] == target[1]) match1 = true;
            if (t[2] == target[2]) match2 = true;
        }

        // If all target values are achievable → success
        return match0 && match1 && match2;
    }

    /**
     * =========================================================
     * Variant 2: Only 2 Merges Allowed
     * =========================================================
     *
     * INTUITION:
     * - Since only 2 merges allowed, we must carefully pick pairs.
     * - Merge = element-wise max of two triplets.
     * - Filter invalid triplets exceeding target.
     * - Check pairs that could combine to form the target.
     *
     * TC: O(n^2) worst case → all pairs
     * SC: O(n) → filtered valid triplets list
     */
    public static boolean mergeTwoOnly(int[][] triplets, int[] target) {

        List<int[]> valid = new ArrayList<>();

        // Step 1: Filter triplets that exceed target
        for (int[] t : triplets) {
            if (t[0] <= target[0] && t[1] <= target[1] && t[2] <= target[2]) {
                valid.add(t);
            }
        }

        // Step 2: Check all pairs
        for (int i = 0; i < valid.size(); i++) {
            for (int j = i; j < valid.size(); j++) {
                int[] t1 = valid.get(i);
                int[] t2 = valid.get(j);

                int a = Math.max(t1[0], t2[0]);
                int b = Math.max(t1[1], t2[1]);
                int c = Math.max(t1[2], t2[2]);

                if (a == target[0] && b == target[1] && c == target[2]) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int[][] triplets = {
            {2, 5, 3},
            {1, 8, 4},
            {1, 7, 5}
        };

        int[] target1 = {2, 8, 5}; // achievable
        int[] target2 = {2, 7, 5}; // achievable
        int[] target3 = {2, 9, 5}; // not achievable

        System.out.println("=== Unlimited Merges ===");
        System.out.println("Target1: " + mergeUnlimited(triplets, target1)); // true
        System.out.println("Target2: " + mergeUnlimited(triplets, target2)); // true
        System.out.println("Target3: " + mergeUnlimited(triplets, target3)); // false

        System.out.println("\n=== Only 2 Merges ===");
        System.out.println("Target1: " + mergeTwoOnly(triplets, target1)); // true
        System.out.println("Target2: " + mergeTwoOnly(triplets, target2)); // false (needs 3)
        System.out.println("Target3: " + mergeTwoOnly(triplets, target3)); // false
    }
}