package com.neetcode150.advanced_graphs;

import java.util.*;

public class Q5AlienDictionary {

    public static void main(String[] args) {

        /*
         * Example Input:
         * Words are already sorted according to alien dictionary.
         * We must find the character ordering.
         */
        String[] words = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };

        String order = alienOrder(words);

        System.out.println("Alien Dictionary Order: " + order);
    }

    public static String alienOrder(String[] words) {

        /*
         * STEP 1: Create graph structure
         *
         * adjList  -> adjacency list representation of graph
         * indegree -> number of incoming edges for each character
         *
         * Why indegree?
         * Because we will use Kahn's Algorithm (BFS Topological Sort).
         */

        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        /*
         * STEP 2: Initialize graph nodes
         *
         * Every unique character must be added to graph,
         * even if it has no edges.
         */
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                adjList.putIfAbsent(ch, new ArrayList<>());
                indegree.putIfAbsent(ch, 0);
            }
        }

        /*
         * STEP 3: Build the graph using adjacent word comparison
         *
         * Only compare word[i] with word[i+1]
         * Because dictionary is already sorted.
         */
        for (int i = 0; i < words.length - 1; i++) {

            String first = words[i];
            String second = words[i + 1];

            /*
             * EDGE CASE:
             * ["abc", "ab"] is invalid
             * Because longer word appears before prefix.
             */
            if (first.length() > second.length() && first.startsWith(second))
                return "";

            /*
             * Find first differing character
             */
            for (int j = 0; j < Math.min(first.length(), second.length()); j++) {

                if (first.charAt(j) != second.charAt(j)) {

                    char from = first.charAt(j);
                    char to = second.charAt(j);

                    /*
                     * Add edge only if not already present
                     * Avoid duplicate edges
                     */
                    if (!adjList.get(from).contains(to)) {
                        adjList.get(from).add(to);
                        indegree.put(to, indegree.get(to) + 1);
                    }

                    /*
                     * VERY IMPORTANT:
                     * Only first difference matters.
                     */
                    break;
                }
            }
        }

        /*
         * STEP 4: Topological Sort using Kahn's Algorithm (BFS)
         *
         * Add all nodes with indegree 0 to queue.
         */
        Queue<Character> queue = new LinkedList<>();

        for (char ch : indegree.keySet()) {
            if (indegree.get(ch) == 0)
                queue.offer(ch);
        }

        StringBuilder result = new StringBuilder();

        /*
         * Process nodes in BFS order
         */
        while (!queue.isEmpty()) {

            char curr = queue.poll();
            result.append(curr);

            /*
             * Reduce indegree of neighbors
             */
            for (char neighbor : adjList.get(curr)) {

                indegree.put(neighbor, indegree.get(neighbor) - 1);

                /*
                 * If indegree becomes 0,
                 * it means all dependencies satisfied.
                 */
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        /*
         * STEP 5: Cycle Detection
         *
         * If result size != total unique characters,
         * cycle exists → invalid ordering.
         */
        if (result.length() != indegree.size())
            return "";

        return result.toString();
    }
}	