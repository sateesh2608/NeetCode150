package com.neetcode150.Stack;

public class Q2MinStack {

	Node head;
	
	public Q2MinStack(){
		
	}
	
	public void push(int val) {
		if(head == null) {
			head = new Node(val, val, null);
		}else {
			head = new Node(val,Math.min(val, head.min),head);
		}
		
	}
	
	public void pop() {
		head = head.next;
	}
	
	public int top() {
		return head.value;
	}
	
	public int getMin() {
		return head.min;
	}
	
	
	
	private class Node{
		
		int value;
		int min;
		Node next;
		
		Node(int value, int min, Node next){
			this.value = value;
			this.min = min;
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
		
		Q2MinStack minStack = new Q2MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin()); // return -3
		minStack.pop();
		System.out.println(minStack.top());    // return 0
		System.out.println(minStack.getMin()); // return -2
	}
	
}
