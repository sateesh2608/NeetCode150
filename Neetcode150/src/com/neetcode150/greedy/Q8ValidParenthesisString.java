package com.neetcode150.greedy;

import java.util.Arrays;

public class Q8ValidParenthesisString {

    /**
     * Greedy Range Approach
     *
     * INTUITION:
     * We don't know what '*' represents:
     * it can be '(', ')' or empty.
     *
     * So instead of fixing it, we track a RANGE:
     *
     * min → minimum possible open brackets
     * max → maximum possible open brackets
     *
     * For each character:
     * - '(' → increases both min and max
     * - ')' → decreases both min and max
     * - '*' → can act as '(' or ')'
     *         so min-- (treat as ')')
     *         and max++ (treat as '(')
     *
     * WHY THIS WORKS:
     * We maintain all possible valid states.
     * If at any point max < 0 → too many ')'
     * If at end min == 0 → a valid configuration exists
     *
     * Time: O(n)
     * Space: O(1)
     */
    public static boolean checkValidString(String s) {

        int min = 0; // minimum open brackets possible
        int max = 0; // maximum open brackets possible

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '(') {
                min++;
                max++;
            }
            else if (c == ')') {
                min--;
                max--;
            }
            else { // '*'
                /*
                 * '*' can be:
                 * - '(' → increases open
                 * - ')' → decreases open
                 * - empty → no effect
                 *
                 * So we update range:
                 */
                min--;  // treat as ')'
                max++;  // treat as '('
            }

            /*
             * If min goes negative:
             * we reset it to 0 because we can't have
             * negative open brackets
             */
            if (min < 0) min = 0;

            /*
             * If max goes negative:
             * even in best case we have more ')'
             * → invalid string
             */
            if (max < 0) return false;
        }

        /*
         * If min == 0:
         * we can balance all brackets
         */
        return min == 0;
    }
    
    /**
     * =========================================================
     * APPROACH 2: Brute Force (Recursion)
     * =========================================================
     * INTUITION:
     * At each '*', we have 3 choices:
     * 1. Treat it as '('
     * 2. Treat it as ')'
     * 3. Treat it as empty
     *
     * We try all possibilities recursively.
     *
     * WHY THIS IS EXPENSIVE:
     * Each '*' creates 3 branches → O(3^n)
     *
     * We track:
     * - index → current position
     * - balance → number of open brackets
     *
     * RULES:
     * - balance should never go negative
     * - at end, balance must be 0
     */
    public static boolean checkValidStringBrute(String s) {
        return helper(s, 0, 0);
    }

    private static boolean helper(String s, int index, int balance) {

        /*
         * If balance < 0:
         * too many ')' → invalid
         */
        if (balance < 0) return false;

        /*
         * If we reached end:
         * valid only if all brackets closed
         */
        if (index == s.length()) {
            return balance == 0;
        }

        char c = s.charAt(index);

        if (c == '(') {
            return helper(s, index + 1, balance + 1);
        }
        else if (c == ')') {
            return helper(s, index + 1, balance - 1);
        }
        else { // '*'

            /*
             * 3 choices:
             * 1. treat as '('
             * 2. treat as ')'
             * 3. treat as empty
             */
            return helper(s, index + 1, balance + 1) ||  // '('
                   helper(s, index + 1, balance - 1) ||  // ')'
                   helper(s, index + 1, balance);        // empty
        }
    }
    

    /**
     * =========================================================
     * APPROACH 3: DP (Memoization) → O(n^2)
     * =========================================================
     *
     * INTUITION:
     * Same as brute force, but we cache results.
     *
     * State:
     * dp[index][balance] → can we form valid string?
     *
     * WHY O(n^2):
     * index ∈ [0..n]
     * balance ∈ [0..n]
     *
     * Total states = n * n
     */
    public static boolean checkValidStringDP(String s) {

        int n = s.length();

        // dp[index][balance] = -1 (unknown), 0 (false), 1 (true)
        int[][] dp = new int[n][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return dfs(s, 0, 0, dp);
    }

    private static boolean dfs(String s, int index, int balance, int[][] dp) {

        if (balance < 0) return false;

        if (index == s.length()) {
            return balance == 0;
        }

        if (dp[index][balance] != -1) {
            return dp[index][balance] == 1;
        }

        char c = s.charAt(index);
        boolean result;

        if (c == '(') {
            result = dfs(s, index + 1, balance + 1, dp);
        }
        else if (c == ')') {
            result = dfs(s, index + 1, balance - 1, dp);
        }
        else {
            result = dfs(s, index + 1, balance + 1, dp) ||  // '('
                     dfs(s, index + 1, balance - 1, dp) ||  // ')'
                     dfs(s, index + 1, balance, dp);        // empty
        }

        dp[index][balance] = result ? 1 : 0;
        return result;
    }

    public static void main(String[] args) {

        String s1 = "()";
        String s2 = "(*)";
        String s3 = "(*))";
        String s4 = ")(*";

        // TC - O(3^n)
        System.out.println("===== BRUTE FORCE =====");
        System.out.println(s1 + " → " + checkValidStringBrute(s1));
        System.out.println(s2 + " → " + checkValidStringBrute(s2));
        System.out.println(s3 + " → " + checkValidStringBrute(s3));
        System.out.println(s4 + " → " + checkValidStringBrute(s4));
        
        // TC - O(n^2)
        System.out.println("\n===== DP =====");
        System.out.println(s1 + " → " + checkValidStringDP(s1));
        System.out.println(s2 + " → " + checkValidStringDP(s2));
        System.out.println(s3 + " → " + checkValidStringDP(s3));
        System.out.println(s4 + " → " + checkValidStringDP(s4));
        
        // TC - O(n)
        System.out.println("\n==== GREEDY and Best approach=====");
        System.out.println(s1 + " → " + checkValidString(s1));
        System.out.println(s2 + " → " + checkValidString(s2));
        System.out.println(s3 + " → " + checkValidString(s3));
        System.out.println(s4 + " → " + checkValidString(s4));
        
    }
}
