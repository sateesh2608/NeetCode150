package com.neetcode150.intervals;

import java.util.*;

public class Q5MeetingRoomsII {

    /**
     * Approach 1: Chronological Ordering (Two Sorted Arrays)
     *
     * Intuition:
     * - Separate start and end times.
     * - Sort both arrays.
     * - Use two pointers:
     *   i -> next meeting to start
     *   j -> next meeting to end
     *
     * Logic:
     * - If a meeting starts before the earliest ending meeting:
     *     -> need a new room
     * - Else:
     *     -> reuse a room (one meeting ended)
     *
     * Key Insight:
     * - Track MAX rooms used at any time (peak overlap)
     *
     * Time: O(n log n)
     * Space: O(n)
     */
    public static int minMeetingRoomsChronological(List<Interval> intervals) {
        int n = intervals.size();

        int[] startArr = new int[n];
        int[] endArr = new int[n];

        for (int i = 0; i < n; i++) {
            startArr[i] = intervals.get(i).start;
            endArr[i] = intervals.get(i).end;
        }

        Arrays.sort(startArr);
        Arrays.sort(endArr);

        int i = 0, j = 0;
        int currentRooms = 0;
        int maxRooms = 0;

        while (i < n) {
            // New meeting starts before previous one ends -> need new room
            if (startArr[i] < endArr[j]) {
                currentRooms++;
                maxRooms = Math.max(maxRooms, currentRooms);
                i++;
            } else {
                // A meeting ended -> free a room
                currentRooms--;
                j++;
            }
        }

        return maxRooms;
    }

    /**
     * Approach 2: Min Heap (Priority Queue)
     *
     * Intuition:
     * - Always keep track of the meeting that ends the earliest.
     * - Use a min-heap storing end times.
     *
     * Logic:
     * - Sort meetings by start time.
     * - For each meeting:
     *     - If earliest ending meeting is done before this starts:
     *         -> reuse that room (poll heap)
     *     - Add current meeting's end time to heap
     *
     * Key Insight:
     * - Heap size = number of rooms currently in use
     * - Max heap size during process = answer
     *
     * Time: O(n log n)
     * Space: O(n)
     */
    public static int minMeetingRoomsHeap(List<Interval> intervals) {
        if (intervals.size() == 0) return 0;

        // Sort by start time
        intervals.sort((a, b) -> a.start - b.start);

        // Min heap based on end time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add first meeting
        minHeap.add(intervals.get(0).end);

        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);

            // If earliest meeting ended, reuse room
            if (curr.start >= minHeap.peek()) {
                minHeap.poll();
            }

            // Allocate room (new or reused)
            minHeap.add(curr.end);
        }

        return minHeap.size();
    }

    /**
     * Main method to test both approaches
     */
    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();

        intervals.add(new Interval(0, 30));
        intervals.add(new Interval(5, 10));
        intervals.add(new Interval(15, 20));

        int result1 = minMeetingRoomsChronological(intervals);
        int result2 = minMeetingRoomsHeap(intervals);

        System.out.println("Chronological Approach: " + result1);
        System.out.println("Heap Approach: " + result2);
    }
}
