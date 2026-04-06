package com.neetcode150.graphs;

import java.util.*;

public class Q13WordLadder {

    /*
     * PROBLEM INTUITION:
     *
     * We are given:
     * - beginWord
     * - endWord
     * - dictionary (wordList)
     *
     * We must transform beginWord → endWord
     * Changing ONLY one character at a time.
     *
     * Each intermediate word must exist in dictionary.
     *
     * This is essentially:
     * -> Shortest path in an unweighted graph
     *
     * WHY BFS?
     * Because BFS guarantees shortest path in unweighted graph.
     */

    static class Pair {
        String word;
        int steps;

        public Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList =
                List.of("hot","dot","dog","lot","log","cog");

        System.out.println(ladderLength(beginWord, endWord, wordList));
        // Expected Output: 5
        // hit → hot → dot → dog → cog
    }

    public static int ladderLength(String beginWord,
                                   String endWord,
                                   List<String> wordList) {

        /*
         * WHY convert to Set?
         * We need O(1) lookup to check
         * if a transformed word exists.
         */
        Set<String> dictionary = new HashSet<>(wordList);

        /*
         * Edge Case:
         * If endWord is not present,
         * transformation is impossible.
         */
        if (!dictionary.contains(endWord)) {
            return 0;
        }

        /*
         * BFS Queue
         * Stores current word + steps taken so far.
         */
        Queue<Pair> queue = new LinkedList<>();

        /*
         * Start BFS from beginWord.
         * Steps = 1 because beginWord counts.
         */
        queue.offer(new Pair(beginWord, 1));

        /*
         * Remove beginWord from dictionary
         * so we don't revisit it.
         */
        dictionary.remove(beginWord);

        /*
         * Standard BFS loop
         */
        while (!queue.isEmpty()) {

            Pair current = queue.poll();
            String word = current.word;
            int steps = current.steps;

            /*
             * If we reached target → return steps.
             * Because BFS ensures this is shortest path.
             */
            if (word.equals(endWord)) {
                return steps;
            }

            /*
             * Try changing each character position
             * one by one.
             *
             * WHY?
             * Because we can only change one letter at a time.
             */
            for (int i = 0; i < word.length(); i++) {
                char[] wordArray = word.toCharArray();
                 /*
                 * Try replacing current position
                 * with all 26 lowercase letters.
                 */
                for (char ch = 'a'; ch <= 'z'; ch++) {

                    wordArray[i] = ch;
                    String transformed =
                            new String(wordArray);

                    /*
                     * If transformed word exists
                     * in dictionary → valid move.
                     */
                    if (dictionary.contains(transformed)) {

                        /*
                         * Remove immediately to avoid revisiting.
                         * WHY?
                         * Prevents cycles and repeated work.
                         */
                        dictionary.remove(transformed);

                        /*
                         * Add to queue with steps + 1
                         * Because we moved one level deeper.
                         */
                        queue.offer(
                            new Pair(transformed, steps + 1)
                        );
                    }
                }
            }
        }

        /*
         * If BFS completes and we never reached endWord,
         * then no valid transformation exists.
         */
        return 0;
    }
}