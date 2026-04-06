package com.neetcode150.sliding_window;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Q6SlidingWindowMaximum {

	public static void main(String[] args) {

		int[] inputArray = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		
		System.out.println(Arrays.toString(maxSlidingWindow(inputArray,k)));
		
	}

	private static int[] maxSlidingWindow(int[] inputArray, int k) {

		int n = inputArray.length;
		
		if(inputArray == null || n == 0 || k<=0) {
			return new int[0];
		}
		
		int[] result = new int[n-k+1];
		Deque<Integer> deque = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			
			//Remove out of window 
			while(!deque.isEmpty() && deque.peek()<i-k+1) {
				deque.poll();
			}

			//Remove smallest values from deque
			while(!deque.isEmpty() && inputArray[deque.peekLast()] < inputArray[i]) {
				deque.pollLast();
			}
			
			//Add element
			deque.offer(i);
			
			// Get Max out of window
			if(i >= k-1) {
				result[i-k+1] = inputArray[deque.peekFirst()]; 
			}
			
		}
		return result;
	}

}
