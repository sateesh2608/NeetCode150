package com.neetcode150.Stack;

import java.util.Stack;

public class Q6LargestRectangleInHistogram {

	public static void main(String[] args) {

		int[] heights = {2, 1, 5, 6, 2, 3, 1};
		System.out.println(maxRectangleArea(heights));

	}

	private static int maxRectangleArea(int[] heights) {

		int maxArea = 0;
		
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < heights.length; i++) {
			
			int nse = -1;
			int pse = -1;
			
			while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
				
				int element = stack.pop();
				pse = stack.isEmpty()?-1:stack.peek();
				nse = i;
				maxArea = Math.max(maxArea, heights[element]*(nse-pse-1));
				
			}
			
			stack.push(i);
			
		}
		
		// This while is for elements which dont have either nse or pse i.e. last smallest and first smallest which are unvisited(means not calculated max area) in array.
		while(!stack.isEmpty()) {
			
			int element = stack.pop();
			
			int nse = heights.length;
			int pse = stack.isEmpty()?-1:stack.peek();
			maxArea = Math.max(maxArea, heights[element]*(nse-pse-1));
				
		}
		
		// This is alterante solution which can be done without any additional while loop
//		for (int i = 0; i <= heights.length; i++) {
//
//			int nse = -1;
//			int pse = -1;
//			int currentHeight = (i == heights.length) ? 0 : heights[i];
//			while (!stack.isEmpty() && heights[stack.peek()] >= currentHeight) {
//
//				int element = stack.pop();
//				pse = stack.isEmpty() ? -1 : stack.peek();
//				nse = i;
//				maxArea = Math.max(maxArea, heights[element] * (nse - pse - 1));
//
//			}
//
//			stack.push(i);
//
//		}
		
		return maxArea;
	}
	
}
