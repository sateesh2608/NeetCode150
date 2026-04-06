package com.neetcode150.bitwisemanipulation;

public class Q6SumOfTwoIntegers {

    public static void main(String[] args) {
        int a = 5, b = 3;
        System.out.println("Sum of 5 and 3: " + getSum(a, b)); // 8

        a = -7; b = 12;
        System.out.println("Sum of -7 and 12: " + getSum(a, b)); // 5

        a = -4; b = -9;
        System.out.println("Sum of -4 and -9: " + getSum(a, b)); // -13
    }

    // Bitwise sum without '+' operator
    public static int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1; // compute carry
            a = a ^ b;                // sum without carry
            b = carry;                // propagate carry
        }
        return a;
    }
}