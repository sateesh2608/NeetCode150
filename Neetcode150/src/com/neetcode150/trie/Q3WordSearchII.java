package com.neetcode150.trie;

import java.util.*;

public class Q3WordSearchII {

    // Step 1: Trie Node
    static class TrieNode {
        TrieNode[] children;
        String word; // store full word at the end

        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }

    // Step 2: Build Trie from words list
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.word = word; // mark the end of word
        }
        return root;
    }

    // Step 3: Main function to find all words on the board
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);

        int rows = board.length;
        int cols = board[0].length;

        // DFS starting from every cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    // Step 4: DFS + backtracking
    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        // Boundary check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;

        char ch = board[r][c];
        // Already visited or not in Trie
        if (ch == '#' || node.children[ch - 'a'] == null) return;

        node = node.children[ch - 'a'];

        // Word found
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // avoid duplicates
        }

        // Mark current cell as visited
        board[r][c] = '#';

        // Explore 4 directions
        dfs(board, r + 1, c, node, result);
        dfs(board, r - 1, c, node, result);
        dfs(board, r, c + 1, node, result);
        dfs(board, r, c - 1, node, result);

        // Restore the cell
        board[r][c] = ch;
    }

    // Step 5: Test example
    public static void main(String[] args) {
        Q3WordSearchII solver = new Q3WordSearchII();

        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };

        String[] words = {"oath","pea","eat","rain"};

        List<String> result = solver.findWords(board, words);

        System.out.println(result); // Expected: ["oath","eat"]
    }
}
