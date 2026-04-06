package com.neetcode150.trie;

import java.util.Arrays;

public class Q1implementTrie {

	static class Node {

		Node[] children;
		boolean endOftheWord;

		public Node() {
			children = new Node[26];
			Arrays.fill(children, null);
			endOftheWord = false;
		}

	}

	static class Trie {

		Node root;

		public Trie() {
			root = new Node();
		}

		public void insert(String word) {
			Node currRoot = root;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				
				if (currRoot.children[index] == null) {
					currRoot.children[index] = new Node();
				}

				currRoot = currRoot.children[index];
			}
			currRoot.endOftheWord = true;
		}

		public boolean search(String word) {

			Node currRoot = root;

			for (int i = 0; i < word.length(); i++) {

				int index = word.charAt(i) - 'a';
				if (currRoot.children[index] == null) {
					return false;
				}
				
				currRoot = currRoot.children[index];
			}

			return currRoot.endOftheWord;
		}

		public boolean startsWith(String prefix) {

			Node curr = root;
			
			for (int i = 0; i < prefix.length(); i++) {
				if(curr.children[prefix.charAt(i)-'a'] == null) return false;
				curr = curr.children[prefix.charAt(i)-'a'];
			} 
			
			return true;
		}
		
		// this is word break problem not implementation, addded in this to avoid new class not part of neetcode 150 its additional from youtube
		public boolean wordBreak(String key) {

			if(key.length() == 0) return true; 
			for (int i = 1; i <= key.length(); i++) {
				
				String firstpart = key.substring(0,i);
				String secondpart = key.substring(i);
				
				if(search(firstpart) && wordBreak(secondpart))
					return true;
			}
			
			return false;
		}
		
	}

	public static void main(String[] args) {

		String word = "apple";

		Trie obj = new Trie();
		obj.insert(word);
		boolean param_2 = obj.search("app");
		boolean param_3 = obj.startsWith("app");
		
		System.out.println(param_2);
		System.out.println(param_3);

		String[] words = {"i","like","samsung","wish","sung"};
		
		for (int i = 0; i < words.length; i++) {
			obj.insert(words[i]);
		}
		String key ="ilikesamsung";
		System.out.println(obj.wordBreak(key));
		
	}
}
