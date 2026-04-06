package com.dsa.general_theory.bitoperations;

import java.util.*;

public class BitManipulationCheatSheet {

    public static void main(String[] args) {

        // 1️⃣ Check if number is power of 2
        int n1 = 16;
        System.out.println(n1 + " is power of 2? " + isPowerOfTwo(n1));

        // 2️⃣ Count set bits in a number
        int n2 = 29;
        System.out.println("Number of 1 bits in " + n2 + " = " + countSetBits(n2));

        // 3️⃣ Single number (all appear twice except one)
        int[] nums1 = {2, 2, 1};
        System.out.println("Single number = " + singleNumber(nums1));

        // 4️⃣ Single number III (all appear twice except two)
        int[] nums2 = {1, 2, 1, 3, 2, 5};
        System.out.println("Two single numbers = " + Arrays.toString(singleNumberIII(nums2)));

        // 5️⃣ Swap two numbers without temp using XOR
        int a = 5, b = 9;
        System.out.println("Before swap: a=" + a + " b=" + b);
        int[] swapped = swapXOR(a, b);
        System.out.println("After swap: a=" + swapped[0] + " b=" + swapped[1]);

        // 6️⃣ Isolate rightmost set bit
        int n3 = 12; // 1100
        System.out.println("Rightmost set bit of " + n3 + " = " + Integer.toBinaryString(rightmostSetBit(n3)));

        // 7️⃣ Remove rightmost set bit
        System.out.println("Remove rightmost set bit of " + n3 + " = " + Integer.toBinaryString(removeRightmostSetBit(n3)));
    }

    // ========================= 1️⃣ Power of 2 =============================
    public static boolean isPowerOfTwo(int n) {
        // n > 0, and only 1 bit is set
        return n > 0 && (n & (n - 1)) == 0;
    }

    // ========================= 2️⃣ Count set bits =========================
    public static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            // Remove lowest set bit and count
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    // ========================= 3️⃣ Single Number =========================
    public static int singleNumber(int[] nums) {
        int unique = 0;
        for (int num : nums) {
            // XOR cancels duplicates
            unique ^= num;
        }
        return unique;
    }

    // ========================= 4️⃣ Single Number III =====================
    public static int[] singleNumberIII(int[] nums) {
        // Step 1: XOR all numbers → xor = a ^ b
        int xor = 0;
        for (int num : nums) xor ^= num;

        // Step 2: Find rightmost set bit → mask
        int mask = xor & -xor;

        // Step 3: Divide numbers into two groups and XOR separately
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & mask) != 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    // ========================= 5️⃣ Swap two numbers ======================
    public static int[] swapXOR(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return new int[]{a, b};
    }

    // ========================= 6️⃣ Rightmost set bit =====================
    public static int rightmostSetBit(int n) {
        // Keeps only the lowest set bit
        return n & -n;
    }

    // ========================= 7️⃣ Remove rightmost set bit =============
    public static int removeRightmostSetBit(int n) {
        // Clears the lowest set bit
        return n & (n - 1);
    }

}