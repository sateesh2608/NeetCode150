package com.neetcode150.binary_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Q6TimeBasedKeyValueStore {

	public static void main(String[] args) {

		TimeMap timeMap = new TimeMap();
		timeMap.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1.
		timeMap.get("foo", 1); // return "bar"
		System.out.println(timeMap.get("foo", 3)); // return "bar", since there is no value corresponding to foo at timestamp 3 and
								// timestamp 2, then the only value is at timestamp 1 is "bar".
		timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
		System.out.println(timeMap.get("foo", 4)); // return "bar2"
		System.out.println(timeMap.get("foo", 5)); // return "bar2"
	}

	static class TimeMap {

		Map<String, List<TimeBasedValue>> resultMap;

		public TimeMap() {
			resultMap = new HashMap<String, List<TimeBasedValue>>();
		}

		public void set(String key, String value, int timestamp) {

			/*
			 * if(!resultMap.containsKey(key)) { resultMap.put(key, new
			 * ArrayList<TimeBasedValue>()); }
			 * 
			 * resultMap.get(key).add(new TimeBasedValue(value, timestamp));
			 */
			// Replaced above lines in single line
			resultMap.computeIfAbsent(key, k -> new ArrayList<>()).add(new TimeBasedValue(value, timestamp));

		}

		public String get(String key, int timestamp) {

			if (!resultMap.containsKey(key))
				return "";
			Optional<TimeBasedValue> result = searchByTimestampWithBinarySearch(resultMap.get(key), timestamp);

			return result.get().value;

		}
	}

	static class TimeBasedValue {

		String value;
		int timeString;

		public TimeBasedValue(String value, int timeString) {
			super();
			this.value = value;
			this.timeString = timeString;
		}

	}

	public static Optional<TimeBasedValue> searchByTimestampWithBinarySearch(List<TimeBasedValue> list, int timestamp) {

		int left = 0;
		int right = list.size() - 1;
		int matchIndex = -1;

		while (left <= right) {

			int mid = left + (right-left) / 2;

			if (list.get(mid).timeString <= timestamp) {
				matchIndex = mid;
				left = mid + 1;
			} else {
				right = right - 1;
			}

		}

		if (matchIndex == -1)
			return Optional.empty();

		return Optional.of(list.get(matchIndex));
	}

}
