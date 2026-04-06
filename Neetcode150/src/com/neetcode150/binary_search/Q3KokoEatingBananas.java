package com.neetcode150.binary_search;

public class Q3KokoEatingBananas {

	public static void main(String[] args) {

		int[] piles = {3,6,7,11};
		int h = 8;
		
		System.out.println(minEatingSpeed(piles, h));
	}

	public static int minEatingSpeed(int[] piles, int h) {
			
		int minSpeed = 1;
		int maxSpeed = 0;
		
		//to retrieve max element from the piles array as we need left(smallest in array i.e. minSpeed) and right pointer(smallest in array i.e. maxSpeed) 
		for (int pile : piles) {
			maxSpeed = Math.max(maxSpeed, pile);
		}

		while (minSpeed < maxSpeed) {
			
			int mid = minSpeed + (maxSpeed-minSpeed)/2;
			if(canEatInPiles(piles,h,mid)) {
				maxSpeed = mid; 
			}else {
				minSpeed = mid+1;
			}
			
		}

		return minSpeed;
	}

	private static boolean canEatInPiles(int[] piles, int h, int mid) {

		int time = 1;
		for (int i = 0; i < piles.length; i++) {
			time += piles[i]/mid; 
		}
		
		return time<=h;
	}

}
