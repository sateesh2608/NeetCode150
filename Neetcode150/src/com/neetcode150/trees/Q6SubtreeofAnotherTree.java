package com.neetcode150.trees;

public class Q6SubtreeofAnotherTree {

	public static void main(String[] args) {
		
		TreeNode s = new TreeNode(3);
		s.left = new TreeNode(4);
		s.right = new TreeNode(5);
		s.left.left = new TreeNode(1);
		s.left.right = new TreeNode(2);

		TreeNode t = new TreeNode(4);
		t.left = new TreeNode(1);
		t.right = new TreeNode(2);
		
		System.out.println("Original trees are: \n");
		TreeNode.printTree(s);
		System.out.println();
		TreeNode.printTree(t);
		System.out.println(isSubtree(s, t));
	}

	public static boolean isSubtree(TreeNode root, TreeNode subRoot) {

		String preOrderResult = preOrderTraversal(root);
		String subTreePreOrderResult = preOrderTraversal(subRoot);

		//below 2 sysout is not needed
		System.out.println("\n"+preOrderResult);
		System.out.println(subTreePreOrderResult);
		
		return preOrderResult.contains(subTreePreOrderResult);
	}

	private static String preOrderTraversal(TreeNode root) {

		if(root == null) return "null";
		
		StringBuilder sb = new StringBuilder("");
		sb.append(root.val);
		sb.append(preOrderTraversal(root.left));
		sb.append(preOrderTraversal(root.right));
		
		return sb.toString();
	}
}
