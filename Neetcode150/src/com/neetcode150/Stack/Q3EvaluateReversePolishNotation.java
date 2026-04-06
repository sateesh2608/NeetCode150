package com.neetcode150.Stack;

import java.util.Stack;

public class Q3EvaluateReversePolishNotation {
	
	public static void main(String[] args) {
	 
		String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
		System.out.println(evalRPN(tokens));
	}
	
	 public static int evalRPN(String[] tokens) {
	        
		 Stack<Integer> stack = new Stack<>();
		 for (int i = 0; i < tokens.length; i++) {
			
			 String c = tokens[i];
			 if(c.equalsIgnoreCase("+") || c.equalsIgnoreCase("-") || c.equalsIgnoreCase("*") || c.equalsIgnoreCase("/")) {
				 int b = stack.pop(); // second operand
		            int a = stack.pop(); // first operand

		            stack.push(applyOperator(a, b, c));
			 }else
				 stack.push(Integer.parseInt(c));
			 
		}
		 
		 return stack.pop();
	 }
	 
	 public static int applyOperator(int n1, int n2,String operator) {
		 switch(operator) {
		 	case "+": return n1 + n2;
		 	case "*": return n1 * n2;
		 	case "/": return n1 / n2;
		 	case "-": return (n1 - n2);
		 	default: throw new IllegalArgumentException("Invalid Operator");
		 }
	 }
}
