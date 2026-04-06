package com.neetcode150.greedy;

import java.util.Arrays;

public class Q4GasStation {

    /**
     * =========================================================
     * APPROACH: Greedy
     * =========================================================
     *
     * INTUITION:
     * 1. First check if total gas >= total cost
     *    → If not, it's impossible to complete the circuit
     *
     * 2. Traverse stations and track currentGas
     *
     * 3. If currentGas becomes negative:
     *    → We cannot reach next station from current start
     *    → So, discard all stations before this point
     *    → Set new start = i + 1
     *
     * WHY THIS WORKS:
     * If we fail at index i, then any index between previous start
     * and i cannot be a valid starting point (they would fail earlier)
     *
     * Time: O(n)
     * Space: O(1)
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int totalGas = 0;
        int totalCost = 0;

        /*
         * Step 1: Check feasibility
         */
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        /*
         * If total gas is less than total cost:
         * impossible to complete circuit
         */
        if (totalGas < totalCost) return -1;

        int currentGas = 0;
        int start = 0;

        /*
         * Step 2: Find valid starting index
         */
        for (int i = 0; i < gas.length; i++) {

            /*
             * Gain/loss at current station
             */
            currentGas += gas[i] - cost[i];

            /*
             * If we cannot reach next station:
             */
            if (currentGas < 0) {

                /*
                 * Discard current segment
                 * New start must be after this point
                 */
                start = i + 1;

                /*
                 * Reset current gas
                 */
                currentGas = 0;
            }
        }

        return start;
    }

    public static void main(String[] args) {

        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        System.out.println("Gas:  " + Arrays.toString(gas));
        System.out.println("Cost: " + Arrays.toString(cost));

        System.out.println("Starting Index = " + canCompleteCircuit(gas, cost)); // 3
    }
}