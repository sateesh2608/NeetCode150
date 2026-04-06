package com.neetcode150.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Q8CourseSchedule {

    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};          // Simple chain: 0 → 1
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}}; // Cycle: 0 → 1 → 0

        System.out.println("===== DFS Approach =====");
        System.out.println("Test 1 (should be true): " + canFinishDFS(numCourses1, prerequisites1));
        System.out.println("Test 2 (should be false): " + canFinishDFS(numCourses2, prerequisites2));

        System.out.println("\n===== BFS (Kahn's Algorithm) Approach =====");
        System.out.println("Test 1 (should be true): " + canFinishBFS(numCourses1, prerequisites1));
        System.out.println("Test 2 (should be false): " + canFinishBFS(numCourses2, prerequisites2));
    }

    // ===================== DFS Approach =====================
    /**
     * Intuition:
     * - Treat courses as nodes and prerequisites as directed edges.
     * - DFS detects cycles in a directed graph.
     * - visited[] marks nodes we have ever visited.
     * - recursionStack[] marks nodes in the current DFS path.
     * - If we encounter a node in recursionStack → cycle exists.
     * - If no cycle in any path → all courses can be finished.
     */
    public static boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // Stream-based initialization of adjacency list
        List<ArrayList<Integer>> graph = IntStream.range(0, numCourses)
                .mapToObj(i -> new ArrayList<Integer>())
                .toList();

        for (int[] p : prerequisites) {
            int course = p[0];
            int prereq = p[1];
            graph.get(prereq).add(course); // prereq -> course
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recursionStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && dfsCycle(i, visited, recursionStack, graph)) {
                return false; // Cycle detected
            }
        }

        return true; // No cycles → all courses can be finished
    }

    private static boolean dfsCycle(int curr, boolean[] visited, boolean[] recursionStack, List<ArrayList<Integer>> graph) {
        visited[curr] = true;
        recursionStack[curr] = true;

        for (int neighbor : graph.get(curr)) {
            if (!visited[neighbor] && dfsCycle(neighbor, visited, recursionStack, graph)) {
                return true; // Cycle found in recursion
            } else if (recursionStack[neighbor]) {
                return true; // Back-edge found → cycle
            }
        }

        recursionStack[curr] = false; // remove from current DFS path
        return false; // No cycle from this node
    }

    // ===================== BFS / Kahn's Algorithm Approach =====================
    /**
     * Intuition:
     * - Count indegrees for all courses (number of prerequisites).
     * - Start with courses with indegree 0 → can be taken immediately.
     * - Process these courses and reduce indegrees of dependents.
     * - Enqueue dependent courses when indegree becomes 0.
     * - If all courses processed → no cycle → can finish all courses.
     * - If some courses remain with indegree > 0 → cycle exists.
     */
    public static boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        // Stream-based initialization of adjacency list
    	List<ArrayList<Integer>> graph = IntStream.range(0, numCourses)
                .mapToObj(i -> new ArrayList<Integer>())
                .toList();

        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            int course = p[0];
            int prereq = p[1];
            graph.get(prereq).add(course); // prereq -> course
            indegree[course]++;           // increment indegree
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int processed = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            processed++;

            for (int neighbor : graph.get(curr)) {
                indegree[neighbor]--;           // reduce indegree of dependent
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);      // now this course can be taken
                }
            }
        }

        return processed == numCourses; // true if all courses processed
    }
}
