package com.neetcode150.intervals;

import java.util.Arrays;

public class Q1InsertIntervals {

    /*
     * Intuition:
     * We divide the problem into 3 parts:
     * 
     * 1. Add all intervals that come BEFORE newInterval
     *    (i.e., no overlap)
     * 
     * 2. Merge all overlapping intervals with newInterval
     *    by expanding its start and end
     * 
     * 3. Add all remaining intervals AFTER merging
     * 
     * Since intervals are sorted, we only need ONE pass.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        // Result array (max possible size = n + 1)
        int[][] res = new int[n + 1][2];

        int i = 0;     // pointer for intervals
        int idx = 0;   // pointer for result array

        // -------------------------------
        // 1. Add all non-overlapping intervals BEFORE newInterval
        // -------------------------------
        while (i < n && intervals[i][1] < newInterval[0]) {
            res[idx++] = intervals[i++];
        }

        // -------------------------------
        // 2. Merge overlapping intervals
        // -------------------------------
        int start = newInterval[0];
        int end = newInterval[1];

        while (i < n && intervals[i][0] <= end) {
            // expand the merging interval
            if (intervals[i][0] < start) start = intervals[i][0];
            if (intervals[i][1] > end) end = intervals[i][1];
            i++;
        }

        // add merged interval
        res[idx++] = new int[]{start, end};

        // -------------------------------
        // 3. Add remaining intervals AFTER merge
        // -------------------------------
        while (i < n) {
            res[idx++] = intervals[i++];
        }

        // Trim array to actual size
        return Arrays.copyOf(res, idx);
    }

    // -------------------------------
    // MAIN METHOD (for testing)
    // -------------------------------
    public static void main(String[] args) {

        int[][] intervals = {
            {1, 3},
            {6, 9}
        };

        int[] newInterval = {2, 5};

        int[][] result = insert(intervals, newInterval);

        // Print result
        System.out.println("Merged Intervals:");
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }

        /*
         * Expected Output:
         * [1, 5]
         * [6, 9]
         */
    }
}
