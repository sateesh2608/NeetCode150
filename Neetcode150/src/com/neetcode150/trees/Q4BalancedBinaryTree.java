package com.neetcode150.trees;

public class Q4BalancedBinaryTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);

		root.left = new TreeNode(9);
		root.right = new TreeNode(20);

		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);

		System.out.println("Tree is: \n");
		TreeNode.printTree(root);

		System.out.println("\n(True/false)this is balanced tree ? " + isBalanced(root));
	}

	public static boolean isBalanced(TreeNode root) {

		return !(heightOfTree(root) == -1);
	}

	public static int heightOfTree(TreeNode root) {
		
		if(root == null) return 0;
		
		int lh = heightOfTree(root.left);		
		if(lh == -1) return -1;
		
		int rh = heightOfTree(root.right);
		if(rh == -1) return -1;
		
		if(Math.abs(lh-rh) > 1) return -1;
		
		return 1+(Math.max(lh, rh));	
	}

}
