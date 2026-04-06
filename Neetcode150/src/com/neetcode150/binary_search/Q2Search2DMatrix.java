package com.neetcode150.binary_search;

public class Q2Search2DMatrix {

	public static void main(String[] args) {
		
		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		int target = 60;
		
		System.out.println(searchMatrix(matrix, target));
	}

	public static boolean searchMatrix(int[][] matrix, int target) {

		int cols = matrix[0].length;
		int left = 0;
		int right = matrix.length*cols-1;
		
		while(left<=right) {

			int mid = left+(right-left)/2;
			int row = mid/cols;
			int col = mid%cols;
			int midVal = matrix[row][col];
			
			if(midVal < target) {
				left=mid+1;
			}else if(midVal > target) {
				right = mid-1;
			}else {
				return true;
			}
			
			
		}
			
		
		return false;
	}
}
