package com.neetcode150.dynamic_programming_1d;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q10WordBreak {

    public static void main(String[] args) {

        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");

        System.out.println("Recursion: " + wordBreakRec(s, wordDict));
        System.out.println("Memoization: " + wordBreakMemo(s, wordDict));
        System.out.println("Tabulation: " + wordBreakTab(s, wordDict));
    }

    // =========================================================
    // 1. RECURSION (PURE BACKTRACKING)
    // =========================================================
    /*
    INTUITION:
    Try every possible split at every index.

    f(i) = can we break s[i..end]?

    At each i:
        try all j >= i:
            if s[i..j] is valid AND f(j+1) is true → return true
    */

    /*
    DRY RUN:
    s = "catsand"

    f(0)
     ├── "cat" → f(3)
     │        ├── "sand" → f(7) → TRUE
     │
     ├── "cats" → f(4)
              ├── "and" → f(7) → TRUE
    */

    public static boolean wordBreakRec(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return rec(0, s, set);
    }

    private static boolean rec(int i, String s, Set<String> set) {

        // BASE CASE: whole string processed
        if (i == s.length()) return true;

        for (int j = i; j < s.length(); j++) {

            String part = s.substring(i, j + 1);

            if (set.contains(part)) {

                if (rec(j + 1, s, set)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
    TIME COMPLEXITY:
    O(2^n)
    Reason:
    - every index has multiple split choices
    - recomputation of same states
    */

    // =========================================================
    // 2. MEMOIZATION (TOP-DOWN DP)
    // =========================================================
    /*
    INTUITION:
    Same recursion, but store results of each index i.

    dp[i] = can s[i..end] be segmented?
    */

    /*
    DRY RUN:
    f(0) → f(3) → f(7)
    f(4) → f(7)  (f(7) reused, not recomputed)
    */

    public static boolean wordBreakMemo(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Boolean[] dp = new Boolean[s.length()];
        return memo(0, s, set, dp);
    }

    private static boolean memo(int i, String s, Set<String> set, Boolean[] dp) {

        if (i == s.length()) return true;

        if (dp[i] != null) return dp[i];

        for (int j = i; j < s.length(); j++) {

            String part = s.substring(i, j + 1);

            if (set.contains(part)) {

                if (memo(j + 1, s, set, dp)) {
                    return dp[i] = true;
                }
            }
        }

        return dp[i] = false;
    }

    /*
    TIME COMPLEXITY:
    O(n^2)
    Reason:
    - each index computed once
    - substring checks O(n)
    */

    // =========================================================
    // 3. TABULATION (BOTTOM-UP DP)
    // =========================================================
    /*
    INTUITION:
    dp[i] = can we form s[0..i-1]

    For each i:
        check all j < i:
            if dp[j] is true AND s[j..i-1] is valid → dp[i] = true
    */

    /*
    DRY RUN:
    s = "catsanddog"

    dp[0] = true

    dp[3] = "cat"
    dp[4] = "cats"
    dp[7] = "cats + sand"
    dp[10] = "cats + sand + dog"
    */

    public static boolean wordBreakTab(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < i; j++) {

                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    /*
    TIME COMPLEXITY:
    O(n^2)
    Reason:
    - two nested loops
    - substring checks inside
    */
}
