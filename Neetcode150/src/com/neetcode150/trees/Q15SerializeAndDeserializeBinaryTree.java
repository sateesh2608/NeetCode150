package com.neetcode150.trees;

import java.util.LinkedList;
import java.util.Queue;

public class Q15SerializeAndDeserializeBinaryTree {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		String result = serialize(root);
		System.out.println("After serializing: "+result);
		System.out.println("After deserializing: ");
		TreeNode.printTree(deserialize(result));
	}

	// Encodes a tree to a single string.
	public static String serialize(TreeNode root) {

		if (root == null) return "";
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		StringBuilder result = new StringBuilder("");

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				if(node != null)result.append(node.val);
				else {
					result.append("#");
					continue;
				}
				queue.offer(node.left);
				queue.offer(node.right);
			}

		}

		return result.toString();

	}

	// Decodes your encoded data to tree.
	
	  public static TreeNode deserialize(String data) {
		 
		  if (data.charAt(0) == '#') return null;
		  
		  Queue<TreeNode> queue = new LinkedList<TreeNode>();
		  TreeNode root = new TreeNode(data.charAt(0)-'0'); 
		  
		  queue.add(root);
		  
		  int i = 1;
		  while(!queue.isEmpty() && i< data.length()) {
			  
			  TreeNode node = queue.poll();
			  
			  if(data.charAt(i) != '#') {
				  TreeNode left = new TreeNode(data.charAt(i)-'0');
				  node.left = left;
				  queue.offer(left);
			  }
			  i++;
			  if(data.charAt(i) != '#') {
				  TreeNode right = new TreeNode(data.charAt(i)-'0');
				  node.right = right;
				  queue.offer(right);
			  }
			  
			  i++;
		  }
	  
		  return root;
		  
	  }
	 
}
