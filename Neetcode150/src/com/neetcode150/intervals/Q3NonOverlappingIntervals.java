package com.neetcode150.intervals;

import java.util.*;


public class Q3NonOverlappingIntervals {

    /**
     * Intuition:
     * - To remove minimum intervals, always keep the interval that ends earliest.
     * - Sort intervals by end time (greedy approach).
     * - Iterate through intervals:
     *   - If current interval starts before previous end → overlap → remove it.
     *   - Otherwise → no overlap → update prevEnd.
     *
     * Time Complexity: O(n log n) for sorting + O(n) for iteration → O(n log n)
     * Space Complexity: O(1) extra (ignoring input list)
     */
    public int eraseOverlapIntervals(List<Interval> intervals) {
        if(intervals.size() <= 1) return 0;

        // 1. Sort intervals by end time
        intervals.sort((a, b) -> Integer.compare(a.end, b.end));

        int count = 0;           // Number of intervals to remove
        int prevEnd = intervals.get(0).end;  // End of last kept interval

        for(int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);

            if(curr.start < prevEnd) {
                // Overlap detected → remove current interval
                count++;
            } else {
                // No overlap → keep current interval
                prevEnd = curr.end;
            }
        }

        return count;
    }

    // -------------------------------
    // MAIN METHOD
    // -------------------------------
    public static void main(String[] args) {
        Q3NonOverlappingIntervals solution = new Q3NonOverlappingIntervals();

        List<Interval> intervals = Arrays.asList(
            new Interval(1,2),
            new Interval(2,3),
            new Interval(3,4),
            new Interval(1,3)
        );

        int minRemovals = solution.eraseOverlapIntervals(intervals);
        System.out.println("Minimum intervals to remove: " + minRemovals);
        // Output: 1
    }
}
