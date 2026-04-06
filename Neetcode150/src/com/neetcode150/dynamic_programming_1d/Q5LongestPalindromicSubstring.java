package com.neetcode150.dynamic_programming_1d;

public class Q5LongestPalindromicSubstring {

	public static String longestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        int maxLen = 0;
        int endIndex = 0; // end index in original string

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    // Make sure the substring indices match palindrome position
                    int revIndex = n - j; // index in original string
                    if (revIndex + dp[i][j] - 1 == i - 1) { 
                        if (dp[i][j] > maxLen) {
                            maxLen = dp[i][j];
                            endIndex = i;
                        }
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return s.substring(endIndex - maxLen, endIndex);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // bab or aba
        System.out.println(longestPalindrome("cbbd"));  // bb
        System.out.println(longestPalindrome("abba"));  // abba
    }
}