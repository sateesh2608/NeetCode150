package com.neetcode150.dynamic_programming_2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q7LongestIncreasingPathInMatrix {

    static int[][] DIRS = {{-1,0},{0,1},{1,0},{0,-1}};

    // ===================== MAIN =====================
    public static void main(String[] args) {

        int[][] matrix = {
            {9,9,4},
            {6,6,8},
            {2,1,1}
        };

        System.out.println("Brute Force: " + bruteForce(matrix));
        System.out.println("Memoization: " + memoization(matrix));
        System.out.println("Tabulation (Sorting): " + tabulationSort(matrix));
        System.out.println("Topo BFS: " + topoBFS(matrix));

        dryRun();
    }

    // ===================== 1. BRUTE FORCE =====================
    /*
     Intuition:
     Try all paths from every cell using DFS

     Time Complexity: O((m*n) * 4^(m*n))  → exponential (TLE)
     Space: O(m*n) recursion stack
    */
    public static int bruteForce(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int max = 1;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                max = Math.max(max, dfsBrute(i,j,matrix));
            }
        }

        return max;
    }

    private static int dfsBrute(int i, int j, int[][] matrix){
        int m = matrix.length, n = matrix[0].length;

        int best = 1;

        for(int[] d : DIRS){
            int x = i + d[0], y = j + d[1];

            if(x<0 || y<0 || x>=m || y>=n) continue;
            if(matrix[x][y] <= matrix[i][j]) continue;

            best = Math.max(best, 1 + dfsBrute(x,y,matrix));
        }

        return best;
    }

    // ===================== 2. MEMOIZATION =====================
    /*
     Intuition:
     Cache results → avoid recomputation

     Time: O(m*n)
     Space: O(m*n)
    */
    public static int memoization(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[][] dp = new int[m][n];

        int max = 1;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                max = Math.max(max, dfsMemo(i,j,matrix,dp));
            }
        }

        return max;
    }

    private static int dfsMemo(int i, int j, int[][] matrix, int[][] dp){

        if(dp[i][j] != 0) return dp[i][j];

        int m = matrix.length, n = matrix[0].length;

        int best = 1;

        for(int[] d : DIRS){
            int x = i + d[0], y = j + d[1];

            if(x<0 || y<0 || x>=m || y>=n) continue;
            if(matrix[x][y] <= matrix[i][j]) continue;

            best = Math.max(best, 1 + dfsMemo(x,y,matrix,dp));
        }

        return dp[i][j] = best;
    }

    // ===================== 3. TABULATION (SORTING) =====================
    /*
     Intuition:
     Process cells from smallest → largest

     Time: O(m*n log(m*n))
     Space: O(m*n)
    */
    public static int tabulationSort(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;

        int[][] dp = new int[m][n];

        List<int[]> cells = new ArrayList<>();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                cells.add(new int[]{matrix[i][j], i, j});
            }
        }

        // sort by value
        Collections.sort(cells, (a,b) -> a[0] - b[0]);

        int max = 1;

        for(int[] cell : cells){
            int i = cell[1], j = cell[2];

            dp[i][j] = 1;

            for(int[] d : DIRS){
                int x = i + d[0], y = j + d[1];

                if(x<0 || y<0 || x>=m || y>=n) continue;

                if(matrix[x][y] < matrix[i][j]){
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[x][y]);
                }
            }

            max = Math.max(max, dp[i][j]);
        }

        return max;
    }

    // ===================== 4. TOPOLOGICAL BFS =====================
    /*
     Intuition:
     Treat as DAG and use Kahn's algorithm

     Time: O(m*n)
     Space: O(m*n)
    */
    public static int topoBFS(int[][] matrix){

        int m = matrix.length, n = matrix[0].length;

        int[][] indegree = new int[m][n];

        // compute indegree
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                for(int[] d : DIRS){
                    int x = i + d[0], y = j + d[1];

                    if(x<0 || y<0 || x>=m || y>=n) continue;

                    if(matrix[x][y] < matrix[i][j]){
                        indegree[i][j]++;
                    }
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(indegree[i][j] == 0){
                    q.offer(new int[]{i,j});
                }
            }
        }

        int length = 0;

        while(!q.isEmpty()){
            int size = q.size();
            length++;

            for(int s=0;s<size;s++){
                int[] cell = q.poll();
                int i = cell[0], j = cell[1];

                for(int[] d : DIRS){
                    int x = i + d[0], y = j + d[1];

                    if(x<0 || y<0 || x>=m || y>=n) continue;

                    if(matrix[x][y] > matrix[i][j]){
                        indegree[x][y]--;

                        if(indegree[x][y] == 0){
                            q.offer(new int[]{x,y});
                        }
                    }
                }
            }
        }

        return length;
    }

    // ===================== DRY RUN =====================
    /*
     Matrix:
     9  9  4
     6  6  8
     2  1  1

     Longest Path:
     1 → 2 → 6 → 9

     Length = 4

     Key Idea:
     Start from smallest values and grow paths
    */
    public static void dryRun(){
        System.out.println("\n--- Dry Run ---");
        System.out.println("Matrix:");
        System.out.println("9 9 4");
        System.out.println("6 6 8");
        System.out.println("2 1 1");
        System.out.println("Longest Increasing Path = 4");
    }
}