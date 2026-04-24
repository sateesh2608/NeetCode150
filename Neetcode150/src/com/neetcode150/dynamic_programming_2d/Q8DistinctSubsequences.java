package com.neetcode150.dynamic_programming_2d;

public class Q8DistinctSubsequences {

    // ===================== MAIN =====================
    public static void main(String[] args) {

        String s = "babgbag";
        String t = "bag";

        System.out.println("Brute Force (End): " + bruteEnd(s, t));
        System.out.println("Memoization (End): " + memoEnd(s, t));
        System.out.println("Tabulation: " + tabulation(s, t));
        System.out.println("Space Optimized: " + spaceOptimized(s, t));

        dryRun();
    }

    // ===================== 1. BRUTE FORCE =====================
    /*
     Intuition:
     Try all subsequences from end

     Time: O(2^n)
     Space: O(n)
    */
    public static int bruteEnd(String s, String t) {
        return dfs(s.length()-1, t.length()-1, s, t);
    }

    private static int dfs(int i, int j, String s, String t){

        // matched entire t
        if(j < 0) return 1;

        // s exhausted
        if(i < 0) return 0;

        if(s.charAt(i) == t.charAt(j)){
            return dfs(i-1, j-1, s, t)   // take
                 + dfs(i-1, j, s, t);    // skip
        }

        return dfs(i-1, j, s, t); // skip
    }

    // ===================== 2. MEMOIZATION =====================
    /*
     dp[i][j] = ways to form t[0..j] from s[0..i]

     Time: O(n*m)
     Space: O(n*m)
    */
    public static int memoEnd(String s, String t) {

        int n = s.length(), m = t.length();

        Integer[][] dp = new Integer[n][m];

        return dfsMemo(n-1, m-1, s, t, dp);
    }

    private static int dfsMemo(int i, int j, String s, String t, Integer[][] dp){

        if(j < 0) return 1;
        if(i < 0) return 0;

        if(dp[i][j] != null) return dp[i][j];

        if(s.charAt(i) == t.charAt(j)){
            return dp[i][j] =
                dfsMemo(i-1, j-1, s, t, dp) +
                dfsMemo(i-1, j, s, t, dp);
        }

        return dp[i][j] = dfsMemo(i-1, j, s, t, dp);
    }

    // ===================== 3. TABULATION =====================
    /*
     Convert end-based memo → bottom-up

     dp[i][j] = ways to form t[0..j-1] from s[0..i-1]

     Time: O(n*m)
     Space: O(n*m)
    */
    public static int tabulation(String s, String t){

        int n = s.length(), m = t.length();

        int[][] dp = new int[n+1][m+1];

        // base: empty t
        for(int i=0;i<=n;i++){
            dp[i][0] = 1;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][m];
    }

    // ===================== 4. SPACE OPTIMIZED =====================
    /*
     Use 1D DP

     Time: O(n*m)
     Space: O(m)
    */
    public static int spaceOptimized(String s, String t){

        int n = s.length(), m = t.length();

        int[] dp = new int[m+1];
        dp[0] = 1;

        for(int i=1;i<=n;i++){

            for(int j=m;j>=1;j--){

                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[j] = dp[j-1] + dp[j];
                }
            }
        }

        return dp[m];
    }

    // ===================== DRY RUN =====================
    /*
     s = "babgbag"
     t = "bag"

     Working backwards:
     g matches g → split
     a matches a → split
     b matches b → split

     Total ways = 5
    */
    public static void dryRun(){
        System.out.println("\n--- Dry Run ---");
        System.out.println("s = babgbag");
        System.out.println("t = bag");
        System.out.println("Output = 5");
    }
}
