package com.neetcode150.binary_search;

public class Q7MedianOfTwoArrays {

	public static void main(String[] args) {
		
		int[] nums1 = {1,2};
		int[] nums2 = {3};
		
		System.out.println((findMedianSortedArrays(nums1, nums2)));
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
     
		if(nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
		
		int n1 = nums1.length;
		int n2 = nums2.length;
		int low = 0, high = n1;
		
		while(low<=high) {
			int mid1 = low+high/2;
			int mid2 = (n1+n2+1)/2-mid1;
			
			int l1 = mid1 == 0?Integer.MIN_VALUE:nums1[mid1-1] ;
			int l2=  mid2 == 0?Integer.MIN_VALUE:nums2[mid2-1] ;
			int r1= mid1 == n1?Integer.MAX_VALUE:nums1[mid1];
			int r2= mid2 == n2?Integer.MAX_VALUE:nums2[mid2];
			
			//if partition is valid
			if(l1<=r2 && l2<=r1) {
				// Even total elements => average of two middle elements
				if((n1+n2)%2 == 0)
					return (Math.max(l1, l2)+Math.min(r1, r2))/2;
				else
					// Odd total elements => max of left parts
					return Math.max(l1, l2);
			}
			else if (l1>=r2) {
				high = mid1-1;
			}else {
				low= mid1+1;
			}
			
		}
		
		
		return 0.0;
		
    }
}
