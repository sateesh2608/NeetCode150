package com.neetcode150.trie;

import java.util.Arrays;

class Node {

	Node[] children;
	boolean endOftheWord;

	public Node() {
		children = new Node[26];
		Arrays.fill(children, null);
		endOftheWord = false;
	}

}
