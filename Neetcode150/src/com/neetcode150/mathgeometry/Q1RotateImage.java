package com.neetcode150.mathgeometry;

public class Q1RotateImage {

    public static void main(String[] args) {

        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix1);

        // Approach 1: Transpose + Reverse
        rotateTransposeReverse(matrix1);
        System.out.println("\nRotated Matrix (Transpose + Reverse):");
        printMatrix(matrix1);

        // Approach 2: Layer-by-layer swap
        rotateLayerSwap(matrix2);
        System.out.println("\nRotated Matrix (Layer Swap):");
        printMatrix(matrix2);
    }

    // ----------------------------
    // Approach 1: Transpose + Reverse
    // ----------------------------
    public static void rotateTransposeReverse(int[][] matrix) {
        int n = matrix.length;

        // STEP 1: Transpose the matrix (swap across main diagonal)
        // Only iterate upper triangle to avoid double swapping
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // STEP 2: Reverse each row to complete 90° rotation
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    // ----------------------------
    // Approach 2: Layer-by-layer swap
    // ----------------------------
    public static void rotateLayerSwap(int[][] matrix) {
        int n = matrix.length;

        // Process each layer
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;               // starting index of the layer
            int last = n - 1 - layer;        // ending index of the layer

            for (int i = first; i < last; i++) {
                int offset = i - first;

                // save top
                int top = matrix[first][i];

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }
    }

    // ----------------------------
    // Utility: Print matrix
    // ----------------------------
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}