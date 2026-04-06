package com.neetcode150.arrays_and_hashing;

import java.util.HashMap;
import java.util.Map;

public class Q9LongestConsecutiveSequence {

	public static void main(String[] args) {
		
		int[] inputArray = {100,4,200,1,2,3};
		
		System.out.println(longestConsecutive(inputArray));	
		
	}
	
	 public static int longestConsecutive(int[] nums) {
		 
		 if(nums.length == 0)
			 return 0;
		 
		 int longestLength = 0;
		 Map<Integer, Boolean> numberTravelledMap = new HashMap<>();
		 for (int num : nums) {
		      numberTravelledMap.put(num, Boolean.FALSE);
		    }
		 
		 for (int i : nums) {
			int currentLength = 1;
			
			//Check in forward direction
			int nextNum = i+1;
			while(numberTravelledMap.containsKey(nextNum) &&
			          numberTravelledMap.get(nextNum) == false)  {
				
				currentLength++;
				numberTravelledMap.put(nextNum, Boolean.TRUE);
				nextNum++;
				
			}
			
			//Check in reverse direction
			int previousNum = i-1;
			while(numberTravelledMap.containsKey(previousNum) &&
			          numberTravelledMap.get(previousNum) == false)  {
				
				currentLength++;
				numberTravelledMap.put(previousNum, Boolean.TRUE);
				previousNum--;
				
			}
			
			longestLength = Math.max(longestLength, currentLength);
			
		}
		 
		 return longestLength;
	 }
}
