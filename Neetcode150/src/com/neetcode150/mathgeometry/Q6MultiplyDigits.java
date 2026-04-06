package com.neetcode150.mathgeometry;

public class Q6MultiplyDigits {

    public static void main(String[] args) {

        String[][] testCases = {
            {"123", "456"},   // 123*456 = 56088
            {"2", "3"},       // 2*3 = 6
            {"0", "12345"},   // 0*12345 = 0
            {"999", "999"}    // 999*999 = 998001
        };

        for (String[] pair : testCases) {
            String num1 = pair[0];
            String num2 = pair[1];

            System.out.println(num1 + " * " + num2 + " = " + multiply(num1, num2));
        }
    }

    /**
     * Method: multiply
     * ----------------
     * Intuition:
     * 1. Think of multiplication like we do by hand.
     *    - Multiply each digit of num1 by each digit of num2.
     * 2. Store results in an array of size (m + n) to hold all possible carries.
     * 3. Sum contributions to correct positions:
     *    - If num1[i] * num2[j] = x, add to positions i+j and i+j+1
     * 4. Handle carries in the array.
     * 5. Build the final string, skipping leading zeros.
     */
    public static String multiply(String num1, String num2) {

        int m = num1.length();
        int n = num2.length();

        // Result array, max length m+n
        int[] res = new int[m + n];

        // Multiply each digit of num1 and num2
        for (int i = m - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                int product = digit1 * digit2;

                int p1 = i + j;     // carry position
                int p2 = i + j + 1; // current digit position

                int sum = product + res[p2]; // add existing value (may include carry)

                res[p2] = sum % 10;          // current digit
                res[p1] += sum / 10;         // carry to next left position
            }
        }

        // Build result string, skipping leading zeros
        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}