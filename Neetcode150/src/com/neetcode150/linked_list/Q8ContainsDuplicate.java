package com.neetcode150.linked_list;

public class Q8ContainsDuplicate {

	public static void main(String[] args) {

		int[] inputArray = { 1, 2, 2, 4 };

		System.out.println(getDuplicateNumber(inputArray));
	}

	private static int getDuplicateNumber(int[] inputArray) {

		int slow = 0;
		int fast = 0;
		
		do{
			slow = inputArray[slow];
			fast = inputArray[inputArray[fast]];
		}while(fast!=slow);
		
		slow =0;
		
		while(fast!=slow){
			slow = inputArray[slow];
			fast = inputArray[fast];
		}
		
		return slow;
	}
	
	

}