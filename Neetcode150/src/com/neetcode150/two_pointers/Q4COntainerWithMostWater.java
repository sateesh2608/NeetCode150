package com.neetcode150.two_pointers;

public class Q4COntainerWithMostWater {

	public static void main(String[] args) {

		int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
		
		System.out.println(maxArea(height));
	}

	  public static int maxArea(int[] height) {
		  
		  int maxArea =0;
		  int left = 0;
		  int right= height.length-1;
		  
		  while(left<right) {
			  
			  int width = right-left;
			  int length = Math.min(height[left], height[right]);
			  
			  maxArea = Math.max(maxArea, length*width);
			  
			  if(height[left]<height[right])
				  left++;
			  else
				  right--;
		  }
		  
		  
		  return maxArea;
	  }
}
