package com.neetcode150.mathgeometry;

import java.util.HashSet;

public class Q4HappyNumber {

    public static void main(String[] args) {

        int[] testCases = {19, 2, 7, 20};

        for (int n : testCases) {
            System.out.println("Is " + n + " a Happy Number? -> " + isHappy(n));
        }
    }

    /**
     * Method: isHappy
     * ----------------
     * Intuition:
     * A number is "happy" if repeatedly summing the squares of its digits
     * eventually leads to 1.
     *
     * If it does NOT reach 1, it will fall into a cycle (loop).
     *
     * So the idea is:
     * 1. Keep transforming the number → sum of squares of digits
     * 2. Track previously seen numbers using a HashSet
     * 3. If we see the same number again → cycle → NOT happy
     * 4. If we reach 1 → happy number
     */
    public static boolean isHappy(int n) {

        // Set to store numbers we've already seen (to detect cycles)
        HashSet<Integer> seen = new HashSet<>();

        // Continue until we either reach 1 or detect a cycle
        while (n != 1) {

            // If number already seen → cycle detected → not happy
            if (seen.contains(n)) {
                return false;
            }

            // Add current number to set
            seen.add(n);

            int sum = 0;

            // Calculate sum of squares of digits
            while (n > 0) {
                int digit = n % 10;     // extract last digit
                sum += digit * digit;   // square and add
                n = n / 10;             // remove last digit
            }

            // Update n to the new computed sum
            n = sum;
        }

        // If we reach 1 → happy number
        return true;
    }
}