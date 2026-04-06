package com.neetcode150.two_pointers;

public class Q5TrappingRainWater {

	public static void main(String[] args) {
		
		int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

		System.out.println(trap(heights));
	}
	
	public static int trap(int[] height) {
	
		 int left = 0;
		 int right = height.length-1;
		 
		 int total = 0;
		 int lmax = 0,rmax = 0;
		 
		 while(left<right) {
			 
			 if(height[left] <= height[right]) {
				 
				 if(lmax>height[left]) {
					 total+= lmax-height[left];
				 }else {
					 lmax=height[left];
				 }
				 
				 left++;
				 
			 }
			 if(height[left] > height[right]) {
				 
				 if(rmax-height[right]>0) {
					 total+= rmax-height[right];
				 }else {
					 rmax=height[right];
				 }
				 
				 right--;
				 
			 }
		 }
				 
		 return total;
	 }

}
