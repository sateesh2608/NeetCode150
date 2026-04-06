package com.neetcode150.intervals;

import java.util.*;

public class Q6MinimumIntervalToIncludeEachQuery {

	/**
	 * Intuition:
	 * 
	 * - Sort intervals by start - Process queries in sorted order - Use a min-heap
	 * to always pick the smallest valid interval
	 */
	public int[] minInterval(int[][] intervals, int[] queries) {

		int n = queries.length;
		int m = intervals.length;

		int[] result = new int[n];

		// Store original indices of queries
		Integer[] queryIndices = new Integer[n];
		for (int i = 0; i < n; i++) {
			queryIndices[i] = i;
		}

		// Sort intervals by start
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

		// Sort queries based on values
		Arrays.sort(queryIndices, (a, b) -> Integer.compare(queries[a], queries[b]));

		// Min heap based on interval length
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare((a[1] - a[0]), (b[1] - b[0])));

		int intervalIndex = 0;

		/**
		 * ---------------- DRY RUN ---------------- intervals =
		 * [[1,4],[2,4],[3,6],[4,4]] queries = [2,3,4,5]
		 * 
		 * After sorting: intervals = [[1,4],[2,4],[3,6],[4,4]] queries order (by value)
		 * = [2,3,4,5] ----------------------------------------
		 */

		for (int i = 0; i < n; i++) {

			int query = queries[queryIndices[i]];

			/**
			 * QUERY = 2 Add: [1,4], [2,4] Heap → [2,4] (len=3), [1,4] (len=4)
			 */

			/**
			 * QUERY = 3 Add: [3,6] Heap → [2,4], [1,4], [3,6]
			 */

			/**
			 * QUERY = 4 Add: [4,4] Heap → [4,4] (len=1), others...
			 */

			/**
			 * QUERY = 5 Remove invalid: remove [4,4], [2,4], [1,4] Remaining → [3,6]
			 */

			// Add intervals with start <= query
			while (intervalIndex < m && intervals[intervalIndex][0] <= query) {
				int left = intervals[intervalIndex][0];
				int right = intervals[intervalIndex][1];

				if (right >= query) {
					pq.offer(new int[] { left, right });
				}

				intervalIndex++;
			}

			// Remove invalid intervals
			while (!pq.isEmpty() && pq.peek()[1] < query) {
				pq.poll();
			}

			if (pq.isEmpty()) {
				result[queryIndices[i]] = -1;
			} else {
				int[] best = pq.peek();
				result[queryIndices[i]] = best[1] - best[0] + 1;
			}
		}

		return result;
	}

	public static void main(String[] args) {

		Q6MinimumIntervalToIncludeEachQuery sol = new Q6MinimumIntervalToIncludeEachQuery();

		// Example input
		int[][] intervals = { { 1, 4 }, { 2, 4 }, { 3, 6 }, { 4, 4 } };

		int[] queries = { 2, 3, 4, 5 };

		/**
		 * Expected Output: Query 2 → [2,4] → length = 3 Query 3 → [2,4] → length = 3
		 * Query 4 → [4,4] → length = 1 Query 5 → [3,6] → length = 4
		 * 
		 * Final result = [3, 3, 1, 4]
		 */

		int[] result = sol.minInterval(intervals, queries);

		// Print output
		System.out.println("Results:");
		for (int val : result) {
			System.out.print(val + " ");
		}
	}
}