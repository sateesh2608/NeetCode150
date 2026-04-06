package com.neetcode150.heap_priorityqueue;

import java.util.PriorityQueue;

public class Q4KthLargestElementInArray {

	public static void main(String[] args) {

		int[] nums = {3,2,3,1,2,4,5,5,6};
		int k = 4;

		System.out.println(findKthLargest(nums, k));
	}

	public static int findKthLargest(int[] nums, int k) {

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for (Integer integer : nums) {
			queue.offer(integer);
			
			if(queue.size() > k) {
				queue.poll();
			}
		}
		
		return queue.peek();
	}
}
