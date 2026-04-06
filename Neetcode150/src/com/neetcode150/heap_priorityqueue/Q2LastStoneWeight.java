package com.neetcode150.heap_priorityqueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class Q2LastStoneWeight {

	public static void main(String[] args) {

		int[] stones = {2,7,4,1,8,1};
		System.out.println(lastStoneWeight(stones));
	}

	public static int lastStoneWeight(int[] stones) {

		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for (Integer integer : stones) {
			queue.offer(integer);
		}
		
		while(queue.size() > 1) {
			int firstStone = queue.poll();
			int secondStone = queue.poll();
			
			if(firstStone == secondStone) {
				continue;
			}else {
				queue.offer(firstStone-secondStone);
			}
		}
		
		return (queue.isEmpty())?0:queue.peek();
	}
}
