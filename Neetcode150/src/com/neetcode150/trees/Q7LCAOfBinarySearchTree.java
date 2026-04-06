package com.neetcode150.trees;

public class Q7LCAOfBinarySearchTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(6);

		root.left = new TreeNode(2);
		root.right = new TreeNode(8);

		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);

		root.left.right.left = new TreeNode(3);
		root.left.right.right = new TreeNode(5);

		TreeNode.printTree(root);
		System.out.println();
		TreeNode.printTree(lowestCommonAncestor(root, root.left.left, root.left.right.right)); 
	}

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if(root == null) return null;
		
		int curr = root.val;
		if(curr > p.val && curr > q.val) return lowestCommonAncestor(root.left, p, q);
		if(curr < p.val && curr < q.val) return lowestCommonAncestor(root.right, p, q);
		
		return root;
	}
}
