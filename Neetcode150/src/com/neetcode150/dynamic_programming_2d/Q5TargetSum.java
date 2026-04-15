package com.neetcode150.dynamic_programming_2d;

import java.util.HashMap;

public class Q5TargetSum {

    // ============================================
    // 1️⃣ BRUTE FORCE (RECURSION)
    // ============================================
    /*
    INTUITION:
    For every number, we have 2 choices:
        +num OR -num

    So we explore all possible combinations (like a binary tree)

    TREE:
            0
         /     \
       +1       -1
      /  \     /  \
    +1   -1  +1   -1 ...

    TC: O(2^n)  → every element doubles choices
    SC: O(n)    → recursion stack depth
    */
    public static int bruteForce(int[] nums, int target) {
        return dfs(nums, 0, 0, target);
    }

    private static int dfs(int[] nums, int index, int sum, int target) {
        // Base case: processed all elements
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }

        // Choose + or -
        int add = dfs(nums, index + 1, sum + nums[index], target);
        int subtract = dfs(nums, index + 1, sum - nums[index], target);

        return add + subtract;
    }


    // ============================================
    // 2️⃣ MEMOIZATION (TOP-DOWN DP)
    // ============================================
    /*
    INTUITION:
    Same as brute force BUT we cache results of:
        (index, currentSum)

    WHY?
    Same state repeats many times → avoid recomputation

    TC: O(n * S)
    SC: O(n * S)

    where S = range of possible sums
    */
    public static int memoization(int[] nums, int target) {
        HashMap<String, Integer> memo = new HashMap<>();
        return dfsMemo(nums, 0, 0, target, memo);
    }

    private static int dfsMemo(int[] nums, int index, int sum, int target, HashMap<String, Integer> memo) {
        String key = index + "," + sum;

        if (memo.containsKey(key)) return memo.get(key);

        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }

        int add = dfsMemo(nums, index + 1, sum + nums[index], target, memo);
        int subtract = dfsMemo(nums, index + 1, sum - nums[index], target, memo);

        memo.put(key, add + subtract);
        return add + subtract;
    }


    // ============================================
    // 3️⃣ HASHMAP DP (BOTTOM-UP)
    // ============================================
    /*
    INTUITION:
    Instead of recursion, build results iteratively.

    dp[sum] = number of ways to reach this sum

    For each number:
        For every existing sum:
            create:
                sum + num
                sum - num

    TC: O(n * S)
    SC: O(S)

    where S = number of possible sums
    */
    public static int tabulation(int[] nums, int target) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1); // 1 way to make sum = 0

        for (int num : nums) {
            HashMap<Integer, Integer> next = new HashMap<>();

            for (int sum : dp.keySet()) {
                int count = dp.get(sum);

                // Add current number
                next.put(sum + num, next.getOrDefault(sum + num, 0) + count);

                // Subtract current number
                next.put(sum - num, next.getOrDefault(sum - num, 0) + count);
            }

            dp = next; // move to next state
        }

        return dp.getOrDefault(target, 0);
    }


    // ============================================
    // MAIN METHOD
    // ============================================
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println("Brute Force Result: " + bruteForce(nums, target));
        System.out.println("Memoization Result: " + memoization(nums, target));
        System.out.println("Tabulation Result: " + tabulation(nums, target));
    }
}


/*
============================================
🧪 DRY RUN
============================================

nums = [1,1,1,1,1], target = 3

We want expressions like:
+1 +1 +1 +1 -1 = 3
+1 +1 +1 -1 +1 = 3
...

--------------------------------------------
BRUTE FORCE TREE (partial)
--------------------------------------------

index=0, sum=0
    +1 → sum=1
        +1 → sum=2
            +1 → sum=3
                +1 → sum=4
                    -1 → sum=3 ✅
                -1 → sum=2
            -1 → sum=1
        -1 → sum=0
    -1 → sum=-1
        ...

Every path explores all combinations

--------------------------------------------
MEMOIZATION
--------------------------------------------
Repeated states like:
(index=3, sum=1) happen multiple times

Memo stores:
key = "3,1" → value = number of ways

Avoids recomputation → faster

--------------------------------------------
TABULATION (HASHMAP)
--------------------------------------------

Start:
dp = {0:1}

Step 1 (num=1):
dp = {1:1, -1:1}

Step 2:
dp = {2:1, 0:2, -2:1}

Step 3:
dp = {3:1, 1:3, -1:3, -3:1}

Step 4:
dp = {4:1, 2:4, 0:6, -2:4, -4:1}

Step 5:
dp = {5:1, 3:5, 1:10, -1:10, -3:5, -5:1}

Answer:
dp[3] = 5

--------------------------------------------
FINAL OUTPUT
--------------------------------------------
Brute Force Result: 5
Memoization Result: 5
Tabulation Result: 5

============================================
KEY TAKEAWAY
============================================

Brute Force → explores all paths  
Memoization → avoids recomputation  
Tabulation → builds answers iteratively  

All solve SAME problem, but efficiency improves step by step.
*/