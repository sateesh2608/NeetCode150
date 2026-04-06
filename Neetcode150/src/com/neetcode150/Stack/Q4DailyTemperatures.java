package com.neetcode150.Stack;

import java.util.Arrays;
import java.util.Stack;

public class Q4DailyTemperatures {

	public static void main(String[] args) {
		
		int[] temperatures = {73,74,75,71,69,72,76,73};
		
		System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
	}

	public static int[] dailyTemperatures(int[] temperatures) {

		Stack<Integer> stack = new Stack<>();
		int[] result = new int[temperatures.length];
		
		for (int i = temperatures.length-1; i > 0 ; i--) {
			
			//ALways remove if stack has lower value than temperatures
			while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
				stack.pop();
			}

			// once lower value removed, we found higer value so result will store no of days by subtracting
			// top element of stack and current index
			if(!stack.isEmpty()) {
				result[i] = stack.peek()-i;
			}
			
			// if nothing happened you found new greater value in input array so store in stack
			stack.push(i);
		}
		
		return result;
	}
}
