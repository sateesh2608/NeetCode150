package com.neetcode150.intervals;

import java.util.Arrays;

/**
 * Q2MergeIntervals
 *
 * Problem: Given a collection of intervals, merge all overlapping intervals.
 *
 * Intuition:
 * - First, sort intervals by start time.
 * - Then iterate through intervals:
 *     1. Keep track of the last interval added to the result (prev).
 *     2. If the current interval overlaps with prev (curr[0] <= prev[1]):
 *        - Merge them by updating prev's end to max(prev[1], curr[1])
 *     3. If no overlap, just add the current interval to the result.
 * - Finally, trim the result array to the actual number of merged intervals.
 *
 * Key Points:
 * - Sorting is required to handle unsorted input.
 * - Always merge into the last added interval to maintain correct chaining.
 * - Handles all base cases:
 *     - Empty input
 *     - Single interval
 *     - Fully overlapping
 *     - Partially overlapping
 *     - Touching intervals
 */
public class Q2MergeIntervals {

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;

        // Base case: empty input
        if (n == 0) return new int[0][0];

        // 1. Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 2. Result array (max possible size = n)
        int[][] res = new int[n][2];
        int idx = 0;

        // 3. Add first interval
        res[idx++] = new int[]{intervals[0][0], intervals[0][1]};

        // 4. Iterate through remaining intervals
        for (int i = 1; i < n; i++) {
            int[] prev = res[idx - 1]; // last interval in result
            int[] curr = intervals[i]; // current interval

            if (curr[0] <= prev[1]) {
                // Overlapping: merge into prev
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                // No overlap: add current interval to result
                res[idx++] = new int[]{curr[0], curr[1]};
            }
        }
        
        
        
        
        

        // 5. Trim result array to actual size
        return Arrays.copyOf(res, idx);
    }

    // -------------------------------
    // MAIN METHOD for testing
    // -------------------------------
    public static void main(String[] args) {
        Q2MergeIntervals solution = new Q2MergeIntervals();

        // Test cases
        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals2 = {{1,4},{4,5}};
        int[][] intervals3 = {{1,4},{0,4}};
        int[][] intervals4 = {{1,4}};

        // Execute and print results
        printResult(solution.merge(intervals1)); // Expected: [[1,6],[8,10],[15,18]]
        printResult(solution.merge(intervals2)); // Expected: [[1,5]]
        printResult(solution.merge(intervals3)); // Expected: [[0,4]]
        printResult(solution.merge(intervals4)); // Expected: [[1,4]]
    }

    // Helper method to print 2D array
    private static void printResult(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print(Arrays.toString(intervals[i]));
            if (i != intervals.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}