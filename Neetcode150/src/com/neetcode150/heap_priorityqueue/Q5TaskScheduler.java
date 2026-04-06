package com.neetcode150.heap_priorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Q5TaskScheduler {

	public static void main(String[] args) {

		char[] tasks = {'A','A','A','B','B'};
		int n = 2;
		
		System.out.println(leastInterval(tasks, n));
	}

	public static int leastInterval(char[] tasks, int n) {

		// create freq map 
		HashMap<Character,Integer> freqMap = new HashMap<>();
	    for (char c : tasks) {
	        freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
	    }

	    PriorityQueue<Integer> freqQueue =
	            new PriorityQueue<>((a, b) -> b - a);
	    freqQueue.addAll(freqMap.values());

	    int result = 0;

	    while (!freqQueue.isEmpty()) {

	        List<Integer> temp = new ArrayList<>();
	        int cycle = n + 1;

	        while (cycle-- > 0 && !freqQueue.isEmpty()) {
	            temp.add(freqQueue.poll());
	            result++; // time spent executing task
	        }

	        for (int freq : temp) {
	            if (--freq > 0) {
	                freqQueue.offer(freq);
	            }
	        }

	        // if tasks remain, fill idle time
	        if (!freqQueue.isEmpty()) {
	            result += cycle + 1;
	        }
	    }

	    return result;
	}
}
