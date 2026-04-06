package com.neetcode150.linked_list;

import java.util.Stack;

public class Q1ReverseLinkedList {

	public static void main(String[] args) {

		ListNode head = new ListNode(4);
		head.next = new ListNode(8);
		head.next.next = new ListNode(15);
		head.next.next.next = new ListNode(16);

		
		
		System.out.print("Before: ");
		ListNode.printList(head);

		ListNode reversedList = reverseListWithStack(head);

	    System.out.print("After reversal with stack:  ");
	    ListNode.printList(reversedList);

		ListNode reversedListWithoutStack = reverseListWithoutExtraSpace(head);

	    System.out.print("After reversal without stack:  ");
	    ListNode.printList(reversedListWithoutStack);
	    
	}

	public static ListNode reverseListWithStack(ListNode head) {

		Stack<Integer> valueStack = new Stack<>();
	    while (head != null) {
	      valueStack.push(head.val);
	      head = head.next;
	    }

	    ListNode reversedList = new ListNode(Integer.MIN_VALUE);
	    ListNode ptr = reversedList;

	    while (!valueStack.isEmpty()) {
	      ptr.next = new ListNode(valueStack.pop());
	      ptr = ptr.next;
	    }

	    return reversedList.next;
	  }
	
	//with stack is good but taking extra space
	public static ListNode reverseListWithoutExtraSpace(ListNode head) {
		
		
		if(head == null)
			return null;
		
		if(head.next == null)
			return head;
		
		ListNode prevNode = null;
		ListNode currNode = head;
		
		while(currNode != null) {
			
			ListNode nextNode = currNode.next;
			currNode.next = prevNode;
			
			prevNode = currNode;	
			currNode = nextNode;
		}
		
		return prevNode;
	}
}
