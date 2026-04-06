package com.neetcode150.trees;

public class Q11IsValidBinaryTree {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);

		System.out.println(isValidBST(root));

	}

	public static boolean isValidBST(TreeNode root) {
		return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public static boolean helper(TreeNode root, long minValue, long maxValue) {
	
		if(root == null) return true;
		
		if(root.val <= minValue || root.val >= maxValue) return false;
		
		return helper(root.left, minValue, root.val) && helper(root.right, root.val, maxValue);
	}
}
