package com.neetcode150.dynamic_programming_1d;

public class Q6PalindromicSubstrings {

	  /* 
     * Method 1: Expand Around Center
     * Intuition:
     * - A palindrome expands symmetrically around a center.
     * - Each character can be a center for odd-length palindrome.
     * - Each gap between characters can be a center for even-length palindrome.
     * - Expand left and right while characters match and count all palindromes.
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int countSubstringsExpandCenter(String s) {
        int n = s.length();
        int total = 0;

        for (int i = 0; i < n; i++) {
            // Odd-length palindromes (center at i)
            total += expand(s, i, i);

            // Even-length palindromes (center between i and i+1)
            total += expand(s, i, i + 1);
        }

        return total;
    }

    // Helper method to expand around given left and right indices
    private static int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;      // Found a palindrome
            left--;       // Expand left
            right++;      // Expand right
        }
        return count;
    }

    /*
     * Method 2: DP (1D optimized)
     * Intuition:
     * - dp[j] = true if substring s[i..j] is palindrome for current start index i.
     * - We iterate start index i from end to start to reuse previous row values.
     * - If s[i] == s[j] and (length <= 2 or inner substring is palindrome), mark dp[j] = true
     * - Count each palindrome found.
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public static int countSubstringsDP(String s) {
        int n = s.length();
        boolean[] dp = new boolean[n]; // dp[j] stores current row
        int count = 0;

        // Iterate start index i from end to start
        for (int i = n - 1; i >= 0; i--) {
            boolean[] newDp = new boolean[n]; // new row for current start index

            for (int j = i; j < n; j++) { // end index
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1 || dp[j - 1]) {
                        newDp[j] = true; // current substring is palindrome
                        count++;
                    }
                }
            }

            dp = newDp; // update dp for next iteration
        }

        return count;
    }

    // Main method for testing both methods
    public static void main(String[] args) {
        String[] testCases = {"abc", "aaa", "ababa"};

        for (String s : testCases) {
            System.out.println("Input: \"" + s + "\"");
            System.out.println("Expand Around Center Count: " + countSubstringsExpandCenter(s));
            System.out.println("DP (1D Optimized) Count: " + countSubstringsDP(s));
            System.out.println("-----------------------------------");
        }
    }
}