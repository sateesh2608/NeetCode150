package com.neetcode150.bitwisemanipulation;

public class Q7ReverseIntegers {

	 public static void main(String[] args) {
	        int[] testCases = {123, -123, 120, 0, 1534236469};

	        for (int x : testCases) {
	            System.out.println("Original: " + x);
	            System.out.println("Brute Force: " + reverseBrute(x));
	            System.out.println("Optimal:     " + reverseOptimal(x));
	            System.out.println("----------------------");
	        }
	    }
	 
	 // Brute Force using String
	    public static int reverseBrute(int x) {
	        try {
	            StringBuilder sb = new StringBuilder();
	            sb.append(Math.abs(x));
	            sb.reverse();
	            int reversed = Integer.parseInt(sb.toString());
	            return x < 0 ? -reversed : reversed;
	        } catch (NumberFormatException e) {
	            return 0; // overflow
	        }
	    }

	    // Optimal using arithmetic
	    public static int reverseOptimal(int x) {
	        int rev = 0;
	        while (x != 0) {
	            int digit = x % 10;
	            x /= 10;

	            // Overflow check
	            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
	            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) return 0;

	            rev = rev * 10 + digit;
	        }
	        return rev;
	    }
}
