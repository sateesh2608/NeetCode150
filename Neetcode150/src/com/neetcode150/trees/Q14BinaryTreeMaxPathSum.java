package com.neetcode150.trees;

public class Q14BinaryTreeMaxPathSum {

	static int maxi = 0;
	public static void main(String[] args) {

		TreeNode root = new TreeNode(-10);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.right = new TreeNode(7);
		root.right.left = new TreeNode(15);
				
		maxPathSum(root);
		System.out.println(maxi);
	}

	public static int maxPathSum(TreeNode root) {
		
		if(root == null) return 0;
		
		int leftHeight = Math.max(0,maxPathSum(root.left));
		int rightHeight = Math.max(0,maxPathSum(root.right));
		
		maxi = Math.max(maxi, rightHeight+leftHeight+root.val);

		return root.val+Math.max(leftHeight, rightHeight);
	}
}
