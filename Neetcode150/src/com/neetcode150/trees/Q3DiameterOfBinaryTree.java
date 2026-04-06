package com.neetcode150.trees;

public class Q3DiameterOfBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		TreeNode.printTree(root);
		System.out.println(diameterOfBinaryTree(root));
	}

	public static int diameterOfBinaryTree(TreeNode root) {

		if(root == null) return 0;
		
		int maxHeight = 0;
		
		int rightHt = diameterOfBinaryTree(root.right);
		int leftHt = diameterOfBinaryTree(root.left);
		
		maxHeight = Math.max(maxHeight, leftHt+rightHt);
		
		return 1+ Math.max(leftHt, rightHt);
	}
}
