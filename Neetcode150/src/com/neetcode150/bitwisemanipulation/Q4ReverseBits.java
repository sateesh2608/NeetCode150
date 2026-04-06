package com.neetcode150.bitwisemanipulation;

public class Q4ReverseBits {

    public static void main(String[] args) {
        int n = 43261596; // Example input
        System.out.println("Brute-force approach result: " + reverseBitsBruteForce(n));
        System.out.println("Optimal approach result: " + reverseBitsOptimal(n));
    }

    // =========================================
    // 1️⃣ Brute-force Approach (Using Strings)
    // =========================================
    /*
     * Intuition:
     * - Convert the integer n to a 32-bit binary string.
     * - Reverse the string.
     * - Parse it back to an integer.
     * 
     * Time Complexity: O(32) ~ O(1)
     * Space Complexity: O(32) for string representation
     */
    public static int reverseBitsBruteForce(int n) {
        StringBuilder sb = new StringBuilder();

        // Convert n to binary string with 32 bits
        for (int i = 0; i < 32; i++) {
            sb.append((n & 1)); // take last bit
            n = n >> 1;          // shift right to get next bit
        }

        // Convert reversed binary string back to integer
        return (int) Long.parseLong(sb.toString(), 2);
    }

    // =========================================
    // 2️⃣ Optimal Bit Manipulation Approach
    // =========================================
    /*
     * Intuition:
     * - We build the reversed number bit by bit.
     * - For each of the 32 bits:
     *      - Shift result left by 1 to make room for new bit.
     *      - Add the last bit of n using bitwise OR.
     *      - Shift n right to process the next bit.
     * - No extra space needed.
     * 
     * Time Complexity: O(32) ~ O(1)
     * Space Complexity: O(1)
     */
    public static int reverseBitsOptimal(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1); // append last bit of n to result
            n = n >>> 1;                      // unsigned right shift to handle sign bit
        }

        return result;
    }
}