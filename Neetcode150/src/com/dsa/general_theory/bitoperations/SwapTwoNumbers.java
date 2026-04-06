package com.dsa.general_theory.bitoperations;

public class SwapTwoNumbers {

	public static void main(String[] args) {

		int n1 = 8;
		int n2 = 4;

		System.out.println(" Value of n1 and n2 before swapping: " + n1 + ", " + n2);

		// Theory is XOR of same element is 0
		n1 = n1 ^ n2;
		n2 = n1 ^ n2; // n1
		n1 = n1 ^ n2; // n2

		System.out.println(" Value of n1 and n2 before swapping: " + n1 + ", " + n2);

		// if we want to divide by 2 that means we need to do right shift
		n1 = n1 >> 1;
		n2 = n2 >> 1;
		System.out.println(" Retrieving half of both numbers: " + n1 + ", " + n2);

		double num1 = n1>>1;
		double num2 = n2>>1;
		System.out.println("Further right shift to retrieving another half: "+num1+", "+num2);
		
		// if we want to multiply by 2 that means we need to do right shift
		n1 = n1 << 1;
		n2 = n2 << 1;
		System.out.println(" Retrieving double of both numbers: " + n1 + ", " + n2);

		num1 = n1<<1;
		num2 = n2<<1;
		System.out.println("Further left shift to retrieving another double: "+num1+", "+num2);

		//check if number can be expressed in power of 2
		int a = 66;
		int b = 64;
		
		if((a & (a-1)) == 0) {
			System.out.println("a is power of 2");			
		}else
			System.out.println("a is not power of 2");
		
		if((b & (b-1)) == 0) {
			System.out.println("b is power of 2");			
		}else
			System.out.println("b is not power of 2");
			

		//
	
	}

}
