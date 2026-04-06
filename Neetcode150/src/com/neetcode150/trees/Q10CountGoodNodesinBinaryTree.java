package com.neetcode150.trees;

public class Q10CountGoodNodesinBinaryTree {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        
        System.out.println(goodNodes(root));
	}

	public static int goodNodes(TreeNode root) {

		return getCurrentGoodNodes(root, Integer.MIN_VALUE);
	}
	
	public static int getCurrentGoodNodes(TreeNode root, int currMax) {
		
		if(root == null) return 0;
		
		int rootAnswer = 0;
		if(root.val >= currMax) {
			rootAnswer =1;
			currMax = root.val;
		}
		
		int lGoodNodes = getCurrentGoodNodes(root.left, currMax);
		int rGoodNodes = getCurrentGoodNodes(root.right, currMax);
		
		return lGoodNodes+rGoodNodes+rootAnswer;
		
	}
}
