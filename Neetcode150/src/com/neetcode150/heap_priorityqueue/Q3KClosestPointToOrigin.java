package com.neetcode150.heap_priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q3KClosestPointToOrigin {

	public static void main(String[] args) {

		int[][] points = {{3,3},{5,-1},{-2,4}}; 
		int k = 2;
		
		int[][] result = kClosest(points, k);
		for (int[] is : result) {
			System.out.println(Arrays.toString(is));
		}
	}

	public static int[][] kClosest(int[][] points, int k) {

		// create max heap based on distance from origin to points 
		PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((a,b)-> Integer.compare(b[0]*b[0]+b[1]*b[1], a[0]*a[0]+a[1]*a[1]));
		
		//add to queue of size K
		for (int[] is : points) {	
			maxHeap.offer(is);
			
			if(maxHeap.size() > k) {
				maxHeap.poll();
			}
		}
		
		// retrieve k points
		int[][] result = new int[k][2];
		
		for (int i= 0; i<k; i++) {
			result[i] = maxHeap.poll();
		}
		
		return result;
		
		
	}
}
