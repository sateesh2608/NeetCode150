package com.neetcode150.dynamic_programming_1d;

public class Q8CoinChange {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println(coinChange(coins, amount)); // Expected output: 3
    }

    /*
     * Intuition:
     * ----------
     * We use Dynamic Programming to solve this problem.
     *
     * dp[i] represents the minimum number of coins needed to make amount i.
     *
     * For each amount i (from 1 to amount), we try every coin.
     * If the coin value is less than or equal to i, we check:
     *
     *      dp[i] = min(dp[i], 1 + dp[i - coin])
     *
     * Explanation:
     * -------------
     * If we take a coin of value 'coin', the remaining amount becomes (i - coin).
     * If we already know the minimum coins needed for (i - coin),
     * then the total coins needed for i would be:
     *
     *      1 (current coin) + dp[i - coin]
     *
     * We take the minimum among all possible coins.
     *
     * Example:
     * coins = [1,2,5], amount = 11
     *
     * dp[0] = 0 (0 coins needed for amount 0)
     *
     * Build dp array gradually:
     *
     * i=1 → dp[1] = 1 (1)
     * i=2 → dp[2] = 1 (2)
     * i=3 → dp[3] = 2 (1+2)
     * i=4 → dp[4] = 2 (2+2)
     * i=5 → dp[5] = 1 (5)
     * ...
     * i=11 → dp[11] = 3 (5+5+1)
     *
     * If dp[amount] remains Integer.MAX_VALUE,
     * it means the amount cannot be formed using given coins.
     */

    public static int coinChange(int[] coins, int amount) {

        // Edge case: if amount is 0, no coins are needed
        if (amount < 1) return 0;

        // dp[i] = minimum coins needed to make amount i
        int[] dp = new int[amount + 1];

        // Build the dp table from amount 1 to target amount
        for (int i = 1; i <= amount; i++) {

            // Initialize with large value (represents impossible initially)
            dp[i] = Integer.MAX_VALUE;

            // Try every coin
            for (int coin : coins) {

                /*
                 * Condition 1: coin must not exceed the current amount i
                 * Condition 2: dp[i - coin] must already be solvable
                 */
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {

                    // Choose the minimum coins among possible options
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        // If still MAX_VALUE, it means the amount cannot be formed
        if (dp[amount] == Integer.MAX_VALUE) return -1;

        return dp[amount];
    }
}