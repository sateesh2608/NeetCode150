package com.neetcode150.mathgeometry;

public class Q3SetMatrixZeroes {

    public static void main(String[] args) {
        // Create object to call method
        Q3SetMatrixZeroes obj = new Q3SetMatrixZeroes();

        // Test matrix (original zeros at (1,1), (2,3), (3,0))
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 0, 7, 8},
            {9, 10, 11, 0},
            {0, 14, 15, 16}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        // Call setZeroes to modify matrix in-place
        obj.setZeroes(matrix);

        System.out.println("\nAfter Set Zeroes:");
        printMatrix(matrix);
    }

    /**
     * Method: setZeroes
     * -----------------
     * Intuition:
     * If any cell is zero, we need to set its entire row and column to zero.
     * We want to do this in-place with O(1) extra space.
     * 
     * Approach:
     * 1. Use the first row and first column as markers to indicate which rows/columns should be zeroed.
     * 2. Use two flags firstRow and firstCol to remember if first row/column originally had a zero.
     * 3. First, mark zeros in the inner matrix (excluding first row/col).
     * 4. Then, apply the markers to set inner cells to zero.
     * 5. Finally, update the first row and first column if needed.
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRow = false;  // Flag to track if first row has any zero
        boolean firstCol = false;  // Flag to track if first column has any zero

        // Step 1: Check if first row contains zero
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                firstRow = true;
                break;
            }
        }

        // Step 2: Check if first column contains zero
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }

        // Step 3: Use first row and column as markers for inner matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;  // Mark row
                    matrix[0][j] = 0;  // Mark column
                }
            }
        }

        // Step 4: Apply markers to inner matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 5: Zero out the first row if needed
        if (firstRow) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 6: Zero out the first column if needed
        if (firstCol) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * Helper method to print the matrix nicely
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}