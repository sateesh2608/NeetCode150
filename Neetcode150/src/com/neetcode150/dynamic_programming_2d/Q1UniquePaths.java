package com.neetcode150.dynamic_programming_2d;

public class Q1UniquePaths {

    // Function to calculate the number of unique paths in an m x n grid
    public int uniquePaths(int m, int n) {
        // memo[i][j] will store the number of unique paths to reach cell (i, j)
        int[][] memo = new int[m][n];

        // Iterate through each cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Base case: first row or first column
                // There is only one way to reach any cell in the first row or first column:
                // either move all right (first row) or all down (first column)
                if (i == 0 || j == 0) {
                    memo[i][j] = 1;
                } else {
                    // The number of ways to reach cell (i, j) is the sum of:
                    // 1. paths from the cell above (i-1, j)
                    // 2. paths from the cell to the left (i, j-1)
                    memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
                }
            }
        }

        // The bottom-right cell contains the total number of unique paths
        return memo[m - 1][n - 1];
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Q1UniquePaths sol = new Q1UniquePaths();

        int m = 3, n = 7; // Example: 3 rows and 7 columns
        int result = sol.uniquePaths(m, n);

        System.out.println("Number of unique paths in a " + m + "x" + n + " grid: " + result);
    }
}

/*
DRY RUN EXAMPLE (m = 3, n = 3):

Initial grid (memo) with 0's:
[0, 0, 0]
[0, 0, 0]
[0, 0, 0]

Step 1: Fill first row and first column with 1's (only 1 way to reach these cells):
[1, 1, 1]
[1, 0, 0]
[1, 0, 0]

Step 2: Fill remaining cells using memo[i][j] = memo[i-1][j] + memo[i][j-1]:
- memo[1][1] = 1 + 1 = 2
- memo[1][2] = 1 + 2 = 3
- memo[2][1] = 2 + 1 = 3
- memo[2][2] = 3 + 3 = 6

Final memo grid:
[1, 1, 1]
[1, 2, 3]
[1, 3, 6]

Result: memo[2][2] = 6 unique paths.
Intuition: Each cell accumulates the total paths from top and left cells.
*/