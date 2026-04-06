package com.neetcode150.dynamic_programming_1d;

public class Q7DecodeWays {

    public static void main(String[] args) {

        String s1 = "12";
        String s2 = "226";
        String s3 = "06";
        String s4 = "111";
        String s5 = "11106";

        System.out.println("Tabulation Solution:");
        System.out.println(numDecodingsTabulation(s1));
        System.out.println(numDecodingsTabulation(s2));
        System.out.println(numDecodingsTabulation(s3));
        System.out.println(numDecodingsTabulation(s4));
        System.out.println(numDecodingsTabulation(s5));

        System.out.println("\nOptimal O(1) Space Solution:");
        System.out.println(numDecodingsOptimal(s1));
        System.out.println(numDecodingsOptimal(s2));
        System.out.println(numDecodingsOptimal(s3));
        System.out.println(numDecodingsOptimal(s4));
        System.out.println(numDecodingsOptimal(s5));
    }


    /*
     -----------------------------------------------------
     METHOD 1 : TABULATION (BOTTOM-UP DP)
     -----------------------------------------------------

     Intuition:

     dp[i] = number of ways to decode first i characters

     Example: "226"

     Index representation:

     String:  2   2   6
     Index :  1   2   3

     dp[0] = 1  (empty string)

     dp[1] = 1  ("2")

     Now calculate remaining:

     dp[2] -> check "2" and "22"
     dp[3] -> check "6" and "26"

     Transition:

     dp[i] += dp[i-1]  (single digit)
     dp[i] += dp[i-2]  (two digits)

     */

    public static int numDecodingsTabulation(String s) {

        int n = s.length();

        // dp[i] = number of ways to decode first i characters
        int[] dp = new int[n + 1];

        // Base case: empty string
        dp[0] = 1;

        // First character
        dp[1] = (s.charAt(0) == '0') ? 0 : 1;

        for (int i = 2; i <= n; i++) {

            // Get single digit
        	// Similar to Integer.parseInt(s.substring(i-2,i)), retriveing last 1 digit and converting to int
            int oneDigit = s.charAt(i - 1) - '0';

            // Get two digit number
            // Similar to Integer.parseInt(s.substring(i-2,i)), retriveing last 2 digits and converting to int
            int twoDigit =
                    (s.charAt(i - 2) - '0') * 10 +
                    (s.charAt(i - 1) - '0');

            // If single digit valid
            if (oneDigit != 0) {
                dp[i] += dp[i - 1];
            }

            // If two digit valid
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }



    /*
     -----------------------------------------------------
     METHOD 2 : OPTIMAL SOLUTION (SPACE OPTIMIZED DP)
     -----------------------------------------------------

     Observation:

     dp[i] only depends on:
     dp[i-1]
     dp[i-2]

     So we don't need the entire dp array.

     We store only two variables.

     prev1 = dp[i-1]
     prev2 = dp[i-2]

     */

    public static int numDecodingsOptimal(String s) {

        if (s.charAt(0) == '0') return 0;

        int prev2 = 1; // dp[i-2]
        int prev1 = 1; // dp[i-1]

        for (int i = 1; i < s.length(); i++) {

            int current = 0;

            int oneDigit = s.charAt(i) - '0';

            int twoDigit =
                    (s.charAt(i - 1) - '0') * 10 + oneDigit;

            // Single digit decode
            if (oneDigit != 0) {
                current += prev1;
            }

            // Two digit decode
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += prev2;
            }

            // Move forward
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}