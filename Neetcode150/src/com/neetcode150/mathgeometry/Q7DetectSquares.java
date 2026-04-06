package com.neetcode150.mathgeometry;

import java.util.HashMap;
import java.util.Map;

public class Q7DetectSquares {

    static class DetectSquares {

        // Map to store points
        // Key = x-coordinate
        // Value = Map of y-coordinate → count of that point
        private Map<Integer, Map<Integer, Integer>> map;

        // Intuition: Initialize storage
        public DetectSquares() {
            map = new HashMap<>();
        }

        // Intuition: Add a point to the data structure
        public void add(int[] point) {
            int x = point[0];
            int y = point[1];

            // If this x-coordinate doesn't exist yet, create inner map
            map.putIfAbsent(x, new HashMap<>());

            // Increment count for this point (handles duplicates)
            Map<Integer, Integer> yMap = map.get(x);
            yMap.put(y, yMap.getOrDefault(y, 0) + 1);
        }

        // Intuition: Count number of squares with (x, y) as one corner
        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            int result = 0;

            // If no points share this x-coordinate, no squares possible
            if (!map.containsKey(x)) return 0;

            Map<Integer, Integer> yMap = map.get(x);

            // Iterate over all points vertically aligned with (x, y)
            for (int ny : yMap.keySet()) {
                // Skip same point (side length must be > 0)
                if (ny == y) continue;

                // Side length of the potential square
                int side = Math.abs(ny - y);

                // Check square to the LEFT of query point
                int x2 = x - side;
                if (map.containsKey(x2)) {
                    Map<Integer, Integer> yMap2 = map.get(x2);
                    result += yMap.get(ny) * yMap2.getOrDefault(y, 0) * yMap2.getOrDefault(ny, 0);
                }

                // Check square to the RIGHT of query point
                x2 = x + side;
                if (map.containsKey(x2)) {
                    Map<Integer, Integer> yMap2 = map.get(x2);
                    result += yMap.get(ny) * yMap2.getOrDefault(y, 0) * yMap2.getOrDefault(ny, 0);
                }
            }

            return result;
        }
    }

    // Main method for testing
    public static void main(String[] args) {

        DetectSquares ds = new DetectSquares();

        // Add points
        ds.add(new int[]{3, 10});
        ds.add(new int[]{3, 2});
        ds.add(new int[]{11, 2});
        ds.add(new int[]{11, 2}); // duplicate point

        // Query 1
        int result1 = ds.count(new int[]{11, 10});
        System.out.println("Count 1: " + result1);  // Expected: 2

        // Add more points
        ds.add(new int[]{11, 10}); // now duplicate for query corner
        int result2 = ds.count(new int[]{11, 10});
        System.out.println("Count 2: " + result2);  // Expected: 4
    }
}