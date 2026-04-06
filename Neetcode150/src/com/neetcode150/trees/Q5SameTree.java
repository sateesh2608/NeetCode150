package com.neetcode150.trees;

public class Q5SameTree {
	public static void main(String[] args) {

		   TreeNode p = new TreeNode(1);
	        p.left = new TreeNode(2);
	        p.right = new TreeNode(3);

	        // Tree q: [1,2,3]
	        TreeNode q = new TreeNode(1);
	        q.left = new TreeNode(2);
	        q.right = new TreeNode(3);

	        // Expected output: true
	        System.out.println(isSameTree(p, q));
	}

	private static boolean isSameTree(TreeNode p, TreeNode q) {

		if(p==null || q==null) return (p==q);
				
		return ((p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
	}
	
}
