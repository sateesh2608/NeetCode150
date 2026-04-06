package com.neetcode150.trie;

public class Q2DesignAddAndSearchWordsDS {

	public static class Node{
		
		Node[] children;
		boolean endOfTheWord;
	
	    public Node() {
	    	children = new Node[26];
	    	endOfTheWord = false;
	    }
		
	}
	
	static class WordDictionary {

		Node root;
	    public WordDictionary() {
	        root = new Node();
	    }
	    
	    public void addWord(String word) {
	        
	    	Node curr = root;
	    	
	    	for (int i = 0; i < word.length(); i++) {
				
	    		int index = word.charAt(i)-'a';
	    		if(curr.children[index] == null) {
	    			curr.children[index] = new Node();
	    		}
	    		
	    		curr = curr.children[index];
			}
	    	
	    	curr.endOfTheWord = true;
	    	
	    }
	    
	    public boolean search(String word) {
	    	return dfs(word,0,root);
	    }

		private boolean dfs(String word, int i, Node root2) {

			//base condition
			if(i== word.length()) return root2.endOfTheWord;
			
			char c = word.charAt(i);
			
			if(c == '.') {
				for (Node child : root2.children) {
					if(child != null && dfs(word,i+1,child)) {
						return true;
					}
				}
				return false;
			}else {
				 if(root2.children[word.charAt(i)-'a'] == null)
					 return false;					 
				return dfs(word,i+1,root2.children[word.charAt(i)-'a']);
			}
			
		}
	}	

	public static void main(String[] args) {

        WordDictionary dict = new WordDictionary();

        // Add words
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");

        // Search tests
        System.out.println(dict.search("pad")); // false
        System.out.println(dict.search("bad")); // true
        System.out.println(dict.search(".ad")); // true
        System.out.println(dict.search("b..")); // true
        System.out.println(dict.search("..d")); // true
        System.out.println(dict.search("...")); // true
        System.out.println(dict.search("....")); // false
    }
}
