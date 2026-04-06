package com.neetcode150.greedy;

import java.util.*;

public class Q5HandOfStraights {

    /**
     * =========================================================
     * APPROACH 1: Brute Force → O(n^2)
     * =========================================================
     *
     * INTUITION:
     * - Sort the array
     * - Try to form groups starting from each unused card
     * - For each group, search next consecutive elements manually
     *
     * WHY SLOW:
     * - For every element, we may scan rest of array
     */
    public static boolean bruteForce(int[] hand, int groupSize) {

        // If total cards cannot be divided into equal groups → impossible
        if (hand.length % groupSize != 0) return false;

        // Sort to make consecutive search easier
        Arrays.sort(hand);

        // Track which cards are already used in groups
        boolean[] used = new boolean[hand.length];

        // Try forming groups starting from each index
        for (int i = 0; i < hand.length; i++) {

            // Skip already used cards
            if (used[i]) continue;

            used[i] = true;           // Use current card
            int prev = hand[i];       // Last added card in group
            int count = 1;            // Current group size

            // Try to find next consecutive elements
            for (int j = i + 1; j < hand.length && count < groupSize; j++) {

                // If not used and consecutive
                if (!used[j] && hand[j] == prev + 1) {
                    used[j] = true;   // Mark as used
                    prev = hand[j];   // Update last element
                    count++;          // Increase group size
                }
            }

            // If we couldn't build full group → fail
            if (count != groupSize) return false;
        }

        return true;
    }


    /**
     * =========================================================
     * APPROACH 2: Better → HashMap + Sorting → O(n log n)
     * =========================================================
     *
     * INTUITION:
     * - Sort array so we process smallest first
     * - Use HashMap to track frequencies
     * - For each card, try forming group of size groupSize
     */
    public static boolean better(int[] hand, int groupSize) {

        if (hand.length % groupSize != 0) return false;

        // Sort ensures smallest card is processed first
        Arrays.sort(hand);

        // Store frequency of each card
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        // Traverse sorted cards
        for (int card : hand) {

            // If already used completely → skip
            if (map.get(card) == 0) continue;

            // Try to form group starting from this card
            for (int i = 0; i < groupSize; i++) {

                int current = card + i; // Next required card

                // If missing or exhausted → invalid
                if (!map.containsKey(current) || map.get(current) == 0) {
                    return false;
                }

                // Use one occurrence
                map.put(current, map.get(current) - 1);
            }
        }

        return true;
    }


    /**
     * =========================================================
     * APPROACH 3: Optimal → TreeMap Greedy → O(n log n)
     * =========================================================
     *
     * INTUITION:
     * - Always pick the smallest available card
     * - Build consecutive group from it
     *
     * WHY:
     * - If smallest card is not used now → it may never be used later
     */
    public static boolean optimal(int[] hand, int groupSize) {

        if (hand.length % groupSize != 0) return false;

        // TreeMap keeps keys sorted automatically
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // Count frequency of each card
        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        // Continue until all cards are used
        while (!map.isEmpty()) {

            // Get smallest available card
            int first = map.firstKey();

            // Try forming a group of size groupSize
            for (int i = 0; i < groupSize; i++) {

                int current = first + i; // Required next card

                // If card not present → fail
                if (!map.containsKey(current)) return false;

                // Decrease frequency (use card)
                map.put(current, map.get(current) - 1);

                // If frequency becomes zero → remove from map
                if (map.get(current) == 0) {
                    map.remove(current);
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {

        int[] hand = {1,2,3,6,2,3,4,7,8};
        int groupSize = 3;

        System.out.println("Brute Force  : " + bruteForce(hand, groupSize));
        System.out.println("Better       : " + better(hand, groupSize));
        System.out.println("Optimal      : " + optimal(hand, groupSize));
    }
}