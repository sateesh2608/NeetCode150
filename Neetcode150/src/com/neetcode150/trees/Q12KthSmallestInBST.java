package com.neetcode150.trees;

import java.util.ArrayList;
import java.util.List;

public class Q12KthSmallestInBST {

	// hint property of BST, in order traversal return list in increasing order
	public static void main(String[] args) {

		TreeNode root = new TreeNode(5);

		root.left = new TreeNode(3);
		root.right = new TreeNode(6);

		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);

		root.left.left.left = new TreeNode(1);
		
		System.out.println(kthSmallest(root, 3));
	}

	public static int kthSmallest(TreeNode root, int k) {
		
		List<Integer> inOrderList = new ArrayList<>();  
		return inOrderTraversal(root, inOrderList).get(k-1);
		
	}

	private static List<Integer> inOrderTraversal(TreeNode root, List<Integer> inOrderList ) {
		// TODO Auto-generated method stub
		
		if(root == null) return inOrderList;
		
		inOrderTraversal(root.left, inOrderList);
		inOrderList.add(root.val);
		inOrderTraversal(root.right, inOrderList);
		
		return inOrderList;
		
	}

}
