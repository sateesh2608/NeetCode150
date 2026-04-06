package com.neetcode150.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	public static void printTree(TreeNode root) {
	    if (root == null) return;

	    int height = height(root);
	    int maxWidth = (int) Math.pow(2, height) - 1;

	    Queue<TreeNode> queue = new LinkedList<>();
	    queue.offer(root);

	    for (int level = 0; level < height; level++) {
	        int levelSize = queue.size();
	        int spacesBetween = maxWidth / levelSize;
	        int leadingSpaces = spacesBetween / 2;

	        printSpaces(leadingSpaces);

	        for (int i = 0; i < levelSize; i++) {
	            TreeNode node = queue.poll();

	            if (node != null) {
	                System.out.print(node.val);
	                queue.offer(node.left);
	                queue.offer(node.right);
	            } else {
	                System.out.print(" ");
	                queue.offer(null);
	                queue.offer(null);
	            }

	            printSpaces(spacesBetween);
	        }
	        System.out.println();
	    }
	}

	private static void printSpaces(int count) {
	    for (int i = 0; i < count; i++) {
	        System.out.print(" ");
	    }
	}

	private static int height(TreeNode root) {
	    if (root == null) return 0;
	    return 1 + Math.max(height(root.left), height(root.right));
	}
}
