package com.neetcode150.two_pointers;

public class Q1ValidPalindrome {

	public static void main(String[] args) {
		
		String s = "racecar";
		System.out.println(validPalindrome(s));
		
		//not sure what it is
		System.out.println(validPalindromeNew(s));
		
	}
	
	
	static boolean validPalindrome(String s) {
		
		int start = 0;
		int end = s.length()-1;
		
		while(start<end) {
			if(s.charAt(start) != s.charAt(end))
				return false;
			
			start++;
			end--;
		}
		
		return true;
	}

static boolean validPalindromeNew(String s) {
		
	int left = 0;
    int right = s.length() - 1;
    
    while(left<right){
        while(left<right && !Character.isLetterOrDigit(s.charAt(left))){
            left++;
        }
        while(left<right && !Character.isLetterOrDigit(s.charAt(right))){
            right--;
        }
        if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
            return false;
        }
        left++;
        right--;
    }
    return true;
	}

}
