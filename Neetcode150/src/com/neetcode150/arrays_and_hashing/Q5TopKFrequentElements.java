package com.neetcode150.arrays_and_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q5TopKFrequentElements {

	public static void main(String[] args) {

		int[] nums = {1,3,4,3,4,2,3,4,2,5,4,5,5};
		int k = 3;
		
		System.out.println(Arrays.toString(topKFrequent(nums, k)));
				
	}

	private static int[] topKFrequent(int[] nums, int k) {

		HashMap<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		
		if(nums.length == k)
			return nums;

		for (int i : nums) {
			
//			if(resultMap.containsKey(i))
//				resultMap.put(i,resultMap.get(i)+1);
//			else
//				resultMap.put(i, 0);
			
			//getOrdefault introduced in 1.8
			resultMap.put(i, resultMap.getOrDefault(i,0)+1);
			
		}
		
		Queue<Integer> heap = new PriorityQueue<Integer>(
				(a,b)-> resultMap.get(a)-resultMap.get(b)
		);
		
		for (int j : resultMap.keySet()) {
			heap.add(j);
			if(heap.size()>k)
				heap.poll();
		}
		
		int[] result = new int[k];
		
		for (int i = 0; i < k; i++) {
			result[i]=heap.poll();
		}
		
		return result;
	}

}
