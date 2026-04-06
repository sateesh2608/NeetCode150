package com.neetcode150.linked_list;

public class Q4ReorderList {

	public static void main(String[] args) {
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		System.out.print("Before: ");
		ListNode.printList(head);

	    reorderList(head);

	    System.out.print("After:  ");
	    ListNode.printList(head);
	}

	private static void reorderList(ListNode head) {

		//Edge Cases
		if(head == null || head.next == null) return;
		
		ListNode slow = head;
		ListNode fast = head;	
		
		//finally slow will be in middle exactly after traversal.
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;	
		}
		
		//divide list into 2 halves
		ListNode prevNode = null;
		ListNode curr = slow.next;
		slow.next = null;
		
		//Reverse second half
		while(curr!=null) {
			ListNode nextNode = curr.next;
			curr.next = prevNode;
			prevNode = curr;
			curr = nextNode;
		}
		
	
		ListNode first = head;
		ListNode second = prevNode;
		
		while(second != null) {
	
			//Reorder List
			ListNode temp1 = first.next;
			ListNode temp2 = second.next;
			
			first.next = second;
			second.next = temp1;
			
			first = temp1;
			second = temp2;
					
		}
		
		
	}
	
	
}
