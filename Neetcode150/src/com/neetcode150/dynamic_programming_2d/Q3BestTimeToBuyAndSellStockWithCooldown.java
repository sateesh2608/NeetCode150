package com.neetcode150.dynamic_programming_2d;

import java.util.Arrays;

public class Q3BestTimeToBuyAndSellStockWithCooldown {

	public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};

        System.out.println("=== 1. Recursion (Brute Force) ===");
        System.out.println("Max Profit: " + maxProfitRecursion(prices));

        System.out.println("\n=== 2. Memoization (Top-Down DP) ===");
        System.out.println("Max Profit: " + maxProfitMemo(prices));

        System.out.println("\n=== 3. Tabulation (Bottom-Up DP) ===");
        System.out.println("Max Profit: " + maxProfitTabulation(prices));

        System.out.println("\n=== 4. Space Optimized DP ===");
        System.out.println("Max Profit: " + maxProfitSpaceOptimized(prices));
    }

    // =========================
    // 1. Recursion (Brute Force)
    // Time: O(2^n), Space: O(n)
    // =========================
    static int maxProfitRecursion(int[] prices) {
        return dfsRec(0, 1, prices);
    }

    static int dfsRec(int day, int canBuy, int[] prices) {
        if (day >= prices.length) return 0;

        int profit;
        if (canBuy == 1) {
            profit = Math.max(
                    -prices[day] + dfsRec(day + 1, 0, prices), // buy
                    dfsRec(day + 1, 1, prices)                  // skip
            );
        } else {
            profit = Math.max(
                    prices[day] + dfsRec(day + 2, 1, prices), // sell + cooldown
                    dfsRec(day + 1, 0, prices)                // skip
            );
        }
        return profit;
    }

    // =========================
    // 2. Memoization (Top-Down DP)
    // Time: O(n*2), Space: O(n*2)
    // =========================
    static int maxProfitMemo(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) Arrays.fill(row, -1); // -1 = not computed
        return dfsMemo(0, 1, prices, dp);
    }

    static int dfsMemo(int day, int canBuy, int[] prices, int[][] dp) {
        if (day >= prices.length) return 0;

        if (dp[day][canBuy] != -1) return dp[day][canBuy];

        int profit;
        if (canBuy == 1) {
            profit = Math.max(
                    -prices[day] + dfsMemo(day + 1, 0, prices, dp),
                    dfsMemo(day + 1, 1, prices, dp)
            );
        } else {
            profit = Math.max(
                    prices[day] + dfsMemo(day + 2, 1, prices, dp),
                    dfsMemo(day + 1, 0, prices, dp)
            );
        }

        dp[day][canBuy] = profit;
        return profit;
    }

    // =========================
    // 3. Tabulation (Bottom-Up DP)
    // Time: O(n), Space: O(n)
    // =========================
    static int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2]; // +2 for safe i+2 access

        for (int day = n - 1; day >= 0; day--) {
            dp[day][1] = Math.max(-prices[day] + dp[day + 1][0], dp[day + 1][1]);
            dp[day][0] = Math.max(prices[day] + dp[day + 2][1], dp[day + 1][0]);
        }

        return dp[0][1];
    }

    // =========================
    // 4. Space Optimized DP
    // Time: O(n), Space: O(1)
    // =========================
    static int maxProfitSpaceOptimized(int[] prices) {
        int n = prices.length;

        int[] ahead1 = new int[2]; // dp[i+1][*]
        int[] ahead2 = new int[2]; // dp[i+2][*]
        int[] curr = new int[2];   // dp[i][*]

        Arrays.fill(ahead1, 0);
        Arrays.fill(ahead2, 0);

        for (int day = n - 1; day >= 0; day--) {
            // Can buy today
            curr[1] = Math.max(-prices[day] + ahead1[0], ahead1[1]);

            // Cannot buy (holding stock)
            curr[0] = Math.max(prices[day] + ahead2[1], ahead1[0]);

            // Shift windows
            ahead2 = ahead1;
            ahead1 = curr.clone();
        }

        return ahead1[1];
    }
}

/*
====================================
Intuition / Explanation:

1. State Definition:
   - day = current index in prices array
   - canBuy = 1 if we can buy today, 0 if we are holding stock (must sell before buying again)

2. Choices:
   a) canBuy == 1:
      - Buy today: profit = -prices[day] + dfs(day+1, 0)
      - Skip today: profit = dfs(day+1, 1)
   b) canBuy == 0:
      - Sell today: profit = prices[day] + dfs(day+2, 1)  // cooldown next day
      - Skip today: profit = dfs(day+1, 0)

3. Memoization:
   - Store results of (day, canBuy) to avoid recomputation

4. Tabulation:
   - Build dp[n+2][2] bottom-up, starting from the last day
   - Extra 2 rows handle dp[i+2] without index out-of-bounds
6. Space Optimized: only store next two days to reduce space to O(1)

Dry Run for prices = [1,2,3,0,2]:
------------------------------------
Day 4 (price 2):
 dp[4][1] = max(-2 + 0, 0) = 0
 dp[4][0] = max(2 + 0, 0) = 2

Day 3 (price 0):
 dp[3][1] = max(-0 + 2, 0) = 2
 dp[3][0] = max(0 + 0, 2) = 2

Day 2 (price 3):
 dp[2][1] = max(-3 + 2, 2) = 2
 dp[2][0] = max(3 + 0, 2) = 3

Day 1 (price 2):
 dp[1][1] = max(-2 + 3, 2) = 2
 dp[1][0] = max(2 + 2, 3) = 4

Day 0 (price 1):
 dp[0][1] = max(-1 + 4, 2) = 3
 dp[0][0] = max(1 + 2, 4) = 4

Answer: dp[0][1] = 3

------------------------------------
Day 4 (price 2):
 curr[1] = 0
 curr[0] = 2

Day 3 (price 0):
 curr[1] = 2
 curr[0] = 2

Day 2 (price 3):
 curr[1] = 2
 curr[0] = 3

Day 1 (price 2):
 curr[1] = 2
 curr[0] = 4

Day 0 (price 1):
 curr[1] = 3
 curr[0] = 4

Answer: 3
Sequence: Buy day0 → Sell day1 → cooldown → Buy day3 → Sell day4
====================================
*/


/* For more understanding
==========================
Visual DP Table (Tabulation)
==========================

prices = [1, 2, 3, 0, 2]
n = 5

dp[i][1] = max profit starting at day i, canBuy = 1
dp[i][0] = max profit starting at day i, canBuy = 0 (holding stock)

Extra 2 rows for i+2 access (cooldown):

Initialization (dp[n][*] = dp[n+1][*] = 0):

i    price    dp[i][1]  dp[i][0]
--------------------------------
5     —         0          0
4     2         0          2
3     0         2          2
2     3         2          3
1     2         2          4
0     1         3          4

===================================
Step-by-step computation:

Day 4 (price 2):
 dp[4][1] = max(-2 + dp[5][0]= -2, dp[5][1]=0) = 0
 dp[4][0] = max(2 + dp[6][1]=2, dp[5][0]=0) = 2

Day 3 (price 0):
 dp[3][1] = max(-0 + dp[4][0]=2, dp[4][1]=0) = 2
 dp[3][0] = max(0 + dp[5][1]=0, dp[4][0]=2) = 2

Day 2 (price 3):
 dp[2][1] = max(-3 + dp[3][0]=-1, dp[3][1]=2) = 2
 dp[2][0] = max(3 + dp[4][1]=3, dp[3][0]=2) = 3

Day 1 (price 2):
 dp[1][1] = max(-2 + dp[2][0]=1, dp[2][1]=2) = 2
 dp[1][0] = max(2 + dp[3][1]=4, dp[2][0]=3) = 4

Day 0 (price 1):
 dp[0][1] = max(-1 + dp[1][0]=3, dp[1][1]=2) = 3
 dp[0][0] = max(1 + dp[2][1]=3, dp[1][0]=4) = 4

===================================
Answer:
 dp[0][1] = 3

Sequence of transactions:
 Buy day 0 → Sell day 1 → Cooldown day 2 → Buy day 3 → Sell day 4
 Total Profit = 3
*/