package com.neetcode150.heap_priorityqueue;

import java.util.PriorityQueue;

public class Q1KthLargestElementInStream {

	int k;
	public static void main(String[] args) {
		int[] nums = {4, 5, 8, 2};
		KthLargest kthLargest = new KthLargest(3, nums);
		System.out.println(kthLargest.add(3)); // return 4
		System.out.println(kthLargest.add(5)); // return 5
		System.out.println(kthLargest.add(10)); // return 5
		System.out.println(kthLargest.add(9)); // return 8
		System.out.println(kthLargest.add(4)); // return 8
	}
	
	public static class KthLargest {

	    private PriorityQueue<Integer> minHeap;
	    private int k;

	    public KthLargest(int k, int[] nums) {
	        this.k = k;
	        minHeap = new PriorityQueue<>();

	        for (int num : nums) {
	            add(num);
	        }
	    }
	    
	    
	    public int add(int val) {
	    	minHeap.offer(val);
	    	
	    	if(minHeap.size() > k) {
	    		minHeap.poll();
	    	}
	    	
	    	return minHeap.peek();
	    	
	    }
	}

}
