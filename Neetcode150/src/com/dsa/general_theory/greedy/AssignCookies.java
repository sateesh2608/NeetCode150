package com.dsa.general_theory.greedy;

import java.util.Arrays;

public class AssignCookies {

    /**
     * Greedy approach:
     * 1) Sort both arrays
     * 2) Try to satisfy the least greedy kid first
     * 3) Use the smallest cookie big enough for that kid
     *
     * Time: O(n log n)
     * Space: O(1) extra
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);  // sort greed factors
        Arrays.sort(s);  // sort cookie sizes

        int kid = 0;     // index over kids
        int cookie = 0;  // index over cookies

        // try to assign
        while (kid < g.length && cookie < s.length) {

            // if current cookie can satisfy current kid
            if (s[cookie] >= g[kid]) {
                kid++;      // next kid
                cookie++;   // use cookie
            } else {
                // cookie too small → try next bigger cookie
                cookie++;
            }
        }

        // number of satisfied kids = kid
        return kid;
    }

    public static void main(String[] args) {
        int[] greed = {1, 2, 3};
        int[] cookies = {1, 1};

        System.out.println("Greed factors: " + Arrays.toString(greed));
        System.out.println("Cookie sizes: " + Arrays.toString(cookies));

        int result = findContentChildren(greed, cookies);
        System.out.println("Max content children = " + result);
    }
}
