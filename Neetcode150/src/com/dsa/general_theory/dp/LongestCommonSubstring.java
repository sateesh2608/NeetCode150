package com.dsa.general_theory.dp;

public class LongestCommonSubstring {

    // Method to find the longest common substring
    public static String longestCommonSubstring(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int maxLength = 0;  // Stores length of LCS
        int endIndex = 0;   // Stores ending index of LCS in s1

        // dp[i][j] = length of LCS ending at s1[i-1] and s2[j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Build dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    // Update maxLength and ending index if longer substring found
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                } else {
                    dp[i][j] = 0; // Reset because substring must be continuous
                }
            }
        }

        // Extract the substring from s1 using endIndex and maxLength
        return s1.substring(endIndex - maxLength, endIndex);
    }

    // Main method to test
    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "xyzabcde";

        int length = lengthOfLongestCommonSubstring(s1, s2);
        String lcs = longestCommonSubstring(s1, s2);
        System.out.println("Length of Longest Common Substring is: "+length+"\nand Longest Common Substring: " + lcs);
    }

    // Method to find the length of the longest common substring
    public static int lengthOfLongestCommonSubstring(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int maxLength = 0;

        int[][] dp = new int[m + 1][n + 1]; // dp table

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0; // Reset if no match
                }
            }
        }

        return maxLength;
    }
}