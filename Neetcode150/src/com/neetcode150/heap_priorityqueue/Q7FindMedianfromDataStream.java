package com.neetcode150.heap_priorityqueue;

import java.util.PriorityQueue;

public class Q7FindMedianfromDataStream {

	public static void main(String[] args) {
		
		 MedianFinder medianFinder = new MedianFinder();

	        medianFinder.addNum(1);    // arr = [1]
	        System.out.println(medianFinder.findMedian()); 
	        // Expected: 1.0

	        medianFinder.addNum(2);    // arr = [1, 2]
	        System.out.println(medianFinder.findMedian()); 
	        // Expected: 1.5

	        medianFinder.addNum(3);    // arr = [1, 2, 3]
	        System.out.println(medianFinder.findMedian()); 
	        // Expected: 2.0
	}
	
	static class MedianFinder {

		PriorityQueue<Integer> maxHeap;
		PriorityQueue<Integer> minHeap;
		
	    public MedianFinder() {
	    	maxHeap = new PriorityQueue<Integer>((a,b)->b-a);
	    	minHeap = new PriorityQueue<Integer>((a,b)->Integer.compare(a, b));
	    }
	    
	    public void addNum(int num) {
	        
	    	if(maxHeap.isEmpty() || maxHeap.peek() >= num ) {
	    		maxHeap.add(num);
	    	}else {
	    		minHeap.add(num);
	    	}
	    	
	    	if(minHeap.size()+1<maxHeap.size()) {
	    		minHeap.add(maxHeap.poll());
	    	}else if (minHeap.size()>maxHeap.size())
	    		maxHeap.add(minHeap.poll());
	    	
	    }
	    
	    public double findMedian() {
	        
	    	if((minHeap.size() == maxHeap.size())) {
	    		return (minHeap.peek()+maxHeap.peek())/2.0;
	    	}else
	    		return maxHeap.peek();
	    		
	    }
	}
}
