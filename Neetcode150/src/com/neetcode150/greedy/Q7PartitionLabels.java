package com.neetcode150.greedy;

import java.util.*;

public class Q7PartitionLabels {

    /**
     * Greedy Approach
     *
     * INTUITION:
     * Each character must appear in only ONE partition.
     * So for every character, we must include its LAST occurrence.
     *
     * → While scanning, we keep expanding the current partition's end
     *   to ensure all characters inside are fully covered.
     *
     * → When current index == end:
     *   we know all characters in this segment end here → safe to cut
     *
     * WHY THIS WORKS:
     * This is similar to merging intervals:
     * each character forms an interval [first, last]
     * and we merge overlapping intervals on the fly.
     *
     * Time: O(n)
     * Space: O(1) (since only 26 lowercase letters)
     */
    public static List<Integer> partitionLabels(String s) {

        // Step 1: Store last occurrence of each character
        int[] last = new int[26];

        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();

        int start = 0; // start of current partition
        int end = 0;   // end of current partition

        // Step 2: Traverse and form partitions
        for (int i = 0; i < s.length(); i++) {

            /*
             * Expand the partition:
             * Include the last occurrence of current character
             */
            end = Math.max(end, last[s.charAt(i) - 'a']);

            /*
             * If current index reaches partition end:
             * → all characters in this segment are fully included
             */
            if (i == end) {

                /*
                 * Partition size = end - start + 1
                 */
                result.add(end - start + 1);

                /*
                 * Start next partition
                 */
                start = i + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        String s = "ababcbacadefegdehijhklij";

        System.out.println("Input: " + s);
        System.out.println("Partition sizes: " + partitionLabels(s));
    }
}
