package com.neetcode150.trees;

public class Q1InvertTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		System.out.println("Before Inverting: \n");
		TreeNode.printTree(root);
		
		root = invertTree(root);
		
		System.out.println("\nAfter Inverting: \n");
		TreeNode.printTree(root);
	}

	public static TreeNode invertTree(TreeNode root) {

		if(root == null) return null;
		
		TreeNode rightNode = root.right;
		root.right =invertTree(root.left);
		root.left = invertTree(rightNode);
		
		return root;
	}
}
