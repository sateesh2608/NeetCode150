package com.neetcode150.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q9BinaryTreeRightSideView {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(6);

		TreeNode.printTree(root);
		List<Integer> result = rightSideView(root);
		System.out.println(result);
	}

	public static List<Integer> rightSideView(TreeNode root) {

		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		queue.add(root);
		List<Integer> result = new ArrayList<Integer>();
		if (root == null)
			return result;

		while (!queue.isEmpty()) {
			int levelSize = queue.size();

			for (int i = 0; i < levelSize; i++) {
				TreeNode node = queue.poll();
			
				if (i == levelSize - 1) {
	                result.add(node.val);
	            }

				if (node.left != null) {
					queue.offer(node.left);
				}
				
				if (node.right != null) {
					queue.offer(node.right);
				}

			}

		}

		return result;
	}
}
