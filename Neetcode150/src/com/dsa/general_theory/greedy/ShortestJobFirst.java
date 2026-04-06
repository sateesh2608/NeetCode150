package com.dsa.general_theory.greedy;

import java.util.Arrays;

/** this is  hacker rank problem not leetcode problem */
public class ShortestJobFirst {

    /*
     * =========================================================
     * APPROACH 1: Contribution Technique
     * =========================================================
     * INTUITION:
     * Instead of calculating waiting time for each job,
     * think in reverse:
     *
     * → Each job contributes to the waiting time of all jobs AFTER it
     *
     * If there are (n - 1 - i) jobs after current job,
     * then its total contribution = jobs[i] * (n - 1 - i)
     *
     * WHY THIS WORKS:
     * Every job delays all future jobs by its burst time.
     * So we multiply its time by how many jobs it delays.
     */
    public static double avgWaitTimeContribution(int[] jobs) {

        Arrays.sort(jobs); // SJF → shortest jobs first
        int n = jobs.length;
        int totalWaitingTime = 0;

        for (int i = 0; i < n; i++) {

            // Number of jobs that come after current job
            int jobsAfter = n - 1 - i;

            /*
             * EXPLANATION:
             * Current job runs for jobs[i] time.
             * It delays all future jobs by this much.
             *
             * So total contribution = jobs[i] * jobsAfter
             */
            totalWaitingTime += jobs[i] * jobsAfter;
        }

        return (double) totalWaitingTime / n;
    }

    /*
     * =========================================================
     * APPROACH 2: Prefix Sum Technique
     * =========================================================
     * INTUITION:
     * Simulate execution:
     *
     * → Keep track of total time spent so far
     * → That "total so far" is the waiting time for next job
     *
     * WHY THIS WORKS:
     * Every job waits for all previously executed jobs.
     */
    public static double avgWaitTimePrefixSum(int[] jobs) {

        Arrays.sort(jobs); // SJF ordering
        int totalWaitingTime = 0;
        int elapsedTime = 0; // time spent executing previous jobs

        for (int i = 0; i < jobs.length; i++) {

            /*
             * EXPLANATION:
             * elapsedTime = sum of all previous jobs
             * → this is exactly how long current job waited
             */
            totalWaitingTime += elapsedTime;

            /*
             * Now execute current job
             * → add its burst time to elapsed time
             */
            elapsedTime += jobs[i];
        }

        return (double) totalWaitingTime / jobs.length;
    }

    public static void main(String[] args) {

        int[] jobs1 = {4, 3, 7, 1, 2};
        int[] jobs2 = jobs1.clone(); // clone to reuse same input

        System.out.println("Original Jobs: " + Arrays.toString(jobs1));

        double ans1 = avgWaitTimeContribution(jobs1);
        double ans2 = avgWaitTimePrefixSum(jobs2);

        System.out.println("\nUsing Contribution Technique:");
        System.out.println("Average Waiting Time = " + ans1);

        System.out.println("\nUsing Prefix Sum Technique:");
        System.out.println("Average Waiting Time = " + ans2);
    }
}