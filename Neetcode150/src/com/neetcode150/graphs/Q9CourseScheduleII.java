package com.neetcode150.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Q9CourseScheduleII {

    public static void main(String[] args) {

        // ----------- Test Case 1 (Valid DAG) -----------
        int numCourses1 = 4;
        int[][] prerequisites1 = {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };

        // Possible valid outputs:
        // [0,1,2,3] or [0,2,1,3]

        // ----------- Test Case 2 (Cycle Exists) -----------
        int numCourses2 = 2;
        int[][] prerequisites2 = {
                {1, 0},
                {0, 1}
        };

        // ----------- Test Case 3 (Disconnected Graph) -----------
        int numCourses3 = 5;
        int[][] prerequisites3 = {
                {1, 0}
        };

        System.out.println("Test 1 (Valid Order): "
                + Arrays.toString(findOrder(numCourses1, prerequisites1)));

        System.out.println("Test 2 (Cycle - Empty): "
                + Arrays.toString(findOrder(numCourses2, prerequisites2)));

        System.out.println("Test 3 (Disconnected Graph): "
                + Arrays.toString(findOrder(numCourses3, prerequisites3)));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        // WHAT:
        // Graph representation using adjacency list
        // graph[u] contains all courses that depend on u
        List<List<Integer>> graph = new ArrayList<>();

        // WHY:
        // We must initialize graph for ALL courses
        // Even isolated ones
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // HOW:
        // Build directed edges: prerequisite -> course
        for (int[] edge : prerequisites) {
            int course = edge[0];
            int prereq = edge[1];
            graph.get(prereq).add(course);
        }

        // visited[i] = true → fully processed
        boolean[] visited = new boolean[numCourses];

        // recStack[i] = true → currently in recursion path
        // Used to detect cycle (back edge)
        boolean[] recStack = new boolean[numCourses];

        // Stack stores nodes in reverse topological order
        Stack<Integer> stack = new Stack<>();

        // Important:
        // Graph may be disconnected.
        // So we must try DFS from every node.
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (dfs(i, graph, visited, recStack, stack)) {
                    // If cycle detected → no valid order
                    return new int[0];
                }
            }
        }

        // Pop stack to build final order
        int[] result = new int[numCourses];
        int index = 0;

        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }

        return result;
    }

    private static boolean dfs(int node,
                               List<List<Integer>> graph,
                               boolean[] visited,
                               boolean[] recStack,
                               Stack<Integer> stack) {

        // WHAT:
        // Mark node as visited and part of current DFS path
        visited[node] = true;
        recStack[node] = true;

        // Traverse all dependent courses
        for (int neighbor : graph.get(node)) {

            // If neighbor not visited → DFS deeper
            if (!visited[neighbor]) {
                if (dfs(neighbor, graph, visited, recStack, stack)) {
                    return true; // cycle found below
                }
            }
            // If neighbor is already in recursion stack → cycle
            else if (recStack[neighbor]) {
                return true;
            }
        }

        // HOW:
        // Remove from recursion stack after processing
        recStack[node] = false;

        // WHY:
        // Post-order insertion ensures prerequisites
        // are pushed before dependent course
        stack.push(node);

        return false;
    }
}
