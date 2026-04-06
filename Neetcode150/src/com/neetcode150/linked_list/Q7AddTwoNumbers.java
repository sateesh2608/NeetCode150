package com.neetcode150.linked_list;

public class Q7AddTwoNumbers {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
	    l1.next = new ListNode(4);
	    l1.next.next = new ListNode(3);

	    ListNode l2 = new ListNode(5);
	    l2.next = new ListNode(6);
	    l2.next.next = new ListNode(4);

	    ListNode result = addTwoNumbers(l1, l2);
	    System.out.println("List 1 is: ");
	    ListNode.printList(l1); 
	    System.out.println("List 2 is: ");
	    ListNode.printList(l2); 
	    System.out.println("Sum of 2 lists is: ");
	    ListNode.printList(result); 
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		int sum =0;
		int carry = 0;
		
		ListNode result = new ListNode(-1);
		ListNode ptr = result;
		
		while(l1 != null || l2 != null ) {
			
			sum =+ carry;
			
			if(l1 != null) {
				sum=sum +l1.val;
				l1= l1.next;
			}

			if(l2 != null) {
				sum=sum +l2.val;
				l2= l2.next;
			}
			
			carry = sum/10;
			sum = sum%10;
			ListNode newNode = new ListNode(sum);
			ptr.next = newNode;
			ptr= ptr.next;
			
		}
		
		if(carry == 1) ptr.next=new ListNode(1);
		
		return result.next;
	}
}
