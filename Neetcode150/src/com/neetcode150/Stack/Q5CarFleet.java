package com.neetcode150.Stack;

import java.util.Arrays;

public class Q5CarFleet {

	public static void main(String[] args) {
		int target = 12;
		int[] position = { 10, 8, 0, 5, 3 };
		int[] speed = { 2, 4, 1, 1, 3 };
		
		System.out.println(carFleet(target, position, speed));
	}
	
	 public static int carFleet(int target, int[] position, int[] speed) {
		 
		 double[][] cars = new double[position.length][2];
		 
		 	//store position and time to reach destination
			for (int i = 0; i < position.length; i++) {
				cars[i][0] = position[i];
				cars[i][1] = (target - position[i]) / speed[i];
			}
			
			//sort based on times.
			Arrays.sort(cars,(a,b)->Double.compare(b[0], a[0]));
			
			// sorting output
			//cars = {{10, 1},{ 8, 1},{ 5, 7},{ 3, 3},{ 0,12}};
			
			int fleets = 0;
			double maxTime = 0;
			
			
			for (int i = 0; i < cars.length; i++) {
				if(cars[i][1]>maxTime) {
					fleets++;
					maxTime = cars[i][1]; 
				}
			}
			
			
			return fleets;
			
		 
	 }
}
