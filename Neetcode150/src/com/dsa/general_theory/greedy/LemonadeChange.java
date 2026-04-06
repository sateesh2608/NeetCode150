package com.dsa.general_theory.greedy;

public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        // Counters for $5 and $10 bills
        int five = 0;
        int ten = 0;

        for (int bill : bills) {
            if (bill == 5) {
                // Customer pays with $5, no change needed
                five++;
            } else if (bill == 10) {
                // Customer pays with $10, need to give $5 as change
                if (five == 0) return false;
                five--;  // give one $5 bill
                ten++;   // collect the $10 bill
            } else if (bill == 20) {
                // Customer pays with $20, need to give $15 change
                if (ten > 0 && five > 0) {
                    // Prefer giving one $10 and one $5
                    ten--;
                    five--;
                } else if (five >= 3) {
                    // Otherwise give three $5 bills
                    five -= 3;
                } else {
                    // Cannot give correct change
                    return false;
                }
            }
        }

        // Successfully gave change to all customers
        return true;
    }

    // Main method to test
    public static void main(String[] args) {
        LemonadeChange solution = new LemonadeChange();

        int[] bills1 = {5,5,5,10,20};
        System.out.println(solution.lemonadeChange(bills1)); // Expected: true

        int[] bills2 = {5,5,10,10,20};
        System.out.println(solution.lemonadeChange(bills2)); // Expected: false

        int[] bills3 = {5,5,5,5,10,5,10,10,10,20};
        System.out.println(solution.lemonadeChange(bills3)); // Expected: false
    }
}