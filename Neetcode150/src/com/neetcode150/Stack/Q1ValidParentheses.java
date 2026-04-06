package com.neetcode150.Stack;

import java.util.HashMap;
import java.util.Stack;

public class Q1ValidParentheses {

	public static void main(String[] args) {

		String s = "(({()}))[]()";
		
		System.out.println(checkValidParentheses(s));
		
	}

	private static Boolean checkValidParentheses(String s) {

		HashMap<Character, Character> inputMap = new HashMap<Character, Character>();
		inputMap.put('{', '}');
		inputMap.put('(', ')');
		inputMap.put('[', ']');
		
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < s.length(); i++) {
			
			char c = s.charAt(i);
			
			if(inputMap.containsKey(c)) {
				stack.push(inputMap.get(c));
			}else {
				if(stack.empty())
					return false;
				
				char val = s.charAt(i);
				if(stack.pop() != val)
					return false;
			}
		}
		
		return true;
	}

}
