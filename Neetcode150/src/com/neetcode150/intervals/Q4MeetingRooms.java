package com.neetcode150.intervals;

import java.util.*;

// Class representing an interval
class Interval {
    int start;
    int end;

    Interval() {}
    Interval(int s, int e) { start = s; end = e; }
}

public class Q4MeetingRooms {

    /**
     * Intuition:
     * - To attend all meetings, no two intervals should overlap.
     * - Sort intervals by start time.
     * - Compare each interval's start with previous interval's end.
     *   If overlap exists, return false.
     *
     * Time Complexity: O(n log n) for sorting + O(n) for checking overlaps → O(n log n)
     * Space Complexity: O(1) extra (sorting may use O(log n))
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals.size() <= 1) return true;

        // Sort intervals by start time
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        // Check for overlaps
        for(int i = 1; i < intervals.size(); i++) {
            if(intervals.get(i).start < intervals.get(i-1).end) {
                return false; // overlap detected
            }
        }

        return true; // no overlaps
    }

    // -------------------------------
    // MAIN METHOD
    // -------------------------------
    public static void main(String[] args) {
        Q4MeetingRooms solution = new Q4MeetingRooms();

        List<Interval> intervals = Arrays.asList(
            new Interval(0, 30),
            new Interval(5, 10),
            new Interval(15, 20)
        );

        boolean canAttend = solution.canAttendMeetings(intervals);
        System.out.println("Can attend all meetings? " + canAttend);
        // Output: false (overlaps exist)
    }
}