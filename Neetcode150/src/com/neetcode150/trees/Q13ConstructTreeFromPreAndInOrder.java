package com.neetcode150.trees;

import java.util.HashMap;

public class Q13ConstructTreeFromPreAndInOrder {

	public static void main(String[] args) {
		
		int[] preorder = {3,9,20,15,7};
		int[] inorder = {9,3,15,20,7};
		
		TreeNode.printTree(buildTree(preorder, inorder));
	}
	
	public static TreeNode buildTree(int[] preorder, int[] inorder) {

		// preorder array - used to find root
		// inorder array - helps to divide left subtree and right subtree
		
		// step 1: get first root from inorder which is the first element. then from preorder that root 
		// bifurcates into left and right and left subtree starts with root as preorder (left+1) and right subtree
		// root starts with inorder[root index + (mid-left) + 1] mid-left is no of right sub tree elements.
		
		HashMap<Integer,Integer> inorderMap = new HashMap<>();
		
		for (int i = 0; i < inorder.length; i++) {
			inorderMap.put(inorder[i], i);
		}
		
		return splitTree(inorderMap, preorder, 0, 0, preorder.length-1);
		
		
		
	}

	private static TreeNode splitTree(HashMap<Integer, Integer> inorderMap, int[] preorder, int rootIndex, int left, int right) {

		TreeNode resultTree = new TreeNode(preorder[rootIndex]);
		int mid = inorderMap.get(preorder[rootIndex]);
		
		if(mid > left) resultTree.left = splitTree(inorderMap, preorder, rootIndex+1, left, mid-1);
		if(mid< right) resultTree.right = splitTree(inorderMap, preorder, rootIndex+(mid-left)+1, mid+1, right);
		
		return resultTree;
		
	}
}
