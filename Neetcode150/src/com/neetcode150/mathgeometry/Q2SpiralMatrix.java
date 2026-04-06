package com.neetcode150.mathgeometry;

public class Q2SpiralMatrix {

	 // Main method
    public static void main(String[] args) {
        int n = 4; // Change this for different sizes
        int[][] spiralMatrix = generateSpiralMatrix(n);

        // Print the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(spiralMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static int[][] generateSpiralMatrix(int n) {

        // Create result matrix
        int[][] matrix = new int[n][n];

        // Define boundaries of the current layer
        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = n - 1;

        // Start filling numbers from 1
        int num = 1;

        // Keep looping until boundaries cross
        while (rowBegin <= rowEnd && colBegin <= colEnd) {

            // 1️) Traverse LEFT → RIGHT across the top row
            // Fill the topmost remaining row
            for (int i = colBegin; i <= colEnd; i++) {
                matrix[rowBegin][i] = num++;
            }
            // Move top boundary down (we finished this row)
            rowBegin++;

            // 2️) Traverse TOP → BOTTOM along the right column
            for (int i = rowBegin; i <= rowEnd; i++) {
                matrix[i][colEnd] = num++;
            }
            // Move right boundary left
            colEnd--;

            // 3️) Traverse RIGHT → LEFT across the bottom row
            // Check needed to avoid double filling when only one row remains
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; i--) {
                    matrix[rowEnd][i] = num++;
                }
                // Move bottom boundary up
                rowEnd--;
            }

            // 4) Traverse BOTTOM → TOP along the left column
            // Check needed to avoid double filling when only one column remains
            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    matrix[i][colBegin] = num++;
                }
                // Move left boundary right
                colBegin++;
            }
        }

        return matrix;
    }
}
