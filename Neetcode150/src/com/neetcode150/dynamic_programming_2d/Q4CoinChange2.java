package com.neetcode150.dynamic_programming_2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q4CoinChange2 {

    // ================================
    // 1️⃣ BRUTE FORCE RECURSION
    // ================================
    public static int countWaysBruteForce(int[] coins, int index, int amount) {
        // Base cases
        if (amount == 0) return 1;   // found valid combination
        if (amount < 0 || index == coins.length) return 0; // invalid

        // Two choices: take current coin or skip it
        int take = countWaysBruteForce(coins, index, amount - coins[index]);
        int skip = countWaysBruteForce(coins, index + 1, amount);

        return take + skip;
    }

    // ================================
    // 2️⃣ MEMOIZATION / TOP-DOWN DP
    // ================================
    public static int countWaysMemo(int[] coins, int index, int amount, Map<String, Integer> memo) {
        String key = index + "," + amount;
        if (memo.containsKey(key)) return memo.get(key);

        if (amount == 0) return 1;
        if (amount < 0 || index == coins.length) return 0;

        int take = countWaysMemo(coins, index, amount - coins[index], memo);
        int skip = countWaysMemo(coins, index + 1, amount, memo);

        memo.put(key, take + skip);
        return take + skip;
    }

    // ================================
    // 3️⃣ BOTTOM-UP TABULATION / OPTIMIZED DP
    // Returns total number of ways
    // ================================
    public static int countWaysTabulation(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case: 1 way to make 0

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin]; // add ways by including current coin
            }
        }
        return dp[amount];
    }

    // ================================
    // 4️⃣ ALL WAYS (Bottom-Up with List of Combinations)
    // ================================
    public static List<List<Integer>> allWays(int[] coins, int amount) {
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 0; i <= amount; i++) {
            dp.add(new ArrayList<>());
        }

        dp.get(0).add(new ArrayList<>()); // base case: one empty way to make 0

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                for (List<Integer> prev : dp.get(j - coin)) {
                    List<Integer> newWay = new ArrayList<>(prev);
                    newWay.add(coin);
                    dp.get(j).add(newWay);
                }
            }
        }

        return dp.get(amount);
    }

    // ================================
    // MAIN METHOD
    // ================================
    public static void main(String[] args) {
        int[] coins = {2, 3};
        int amount = 7;

        System.out.println("=== Brute Force Recursion ===");
        int brute = countWaysBruteForce(coins, 0, amount);
        System.out.println("Total ways: " + brute);

        System.out.println("\n=== Memoization / Top-Down DP ===");
        int memo = countWaysMemo(coins, 0, amount, new HashMap<>());
        System.out.println("Total ways: " + memo);

        System.out.println("\n=== Bottom-Up Tabulation / Optimized DP ===");
        int tab = countWaysTabulation(coins, amount);
        System.out.println("Total ways: " + tab);

        System.out.println("\n=== All Ways Combinations ===");
        List<List<Integer>> allWays = allWays(coins, amount);
        for (List<Integer> way : allWays) {
            System.out.println(way);
        }
    }
}

/*
INTUITION AND PROGRESSION:

1️⃣ BRUTE FORCE:
- Recursively try "take" or "skip" for each coin
- Returns count only
- Time complexity: O(2^n) worst case

2️⃣ MEMOIZATION:
- Same recursion but store results in HashMap
- Avoids recomputation of same (index, remainingAmount)
- Time complexity: O(n * amount)

3️⃣ BOTTOM-UP TABULATION:
- Use 1D DP array (dp[j] = ways to make amount j)
- Iteratively add ways for each coin
- Space optimized
- Time complexity: O(n * amount), space O(amount)

4️⃣ ALL WAYS:
- Instead of counts, store actual combinations
- dp[j] stores **list of lists** representing all ways to make amount j
- Can be combined with tabulation to get both counts and sequences

DRY RUN (coins=[2,3], amount=7):
- dp[0] = [[]]
- Add coin 2: dp[2]=[[2]], dp[4]=[[2,2]], dp[6]=[[2,2,2]]
- Add coin 3: dp[3]=[[3]], dp[5]=[[2,3]], dp[6]=[[2,2,2],[3,3]], dp[7]=[[2,2,3]]
*/