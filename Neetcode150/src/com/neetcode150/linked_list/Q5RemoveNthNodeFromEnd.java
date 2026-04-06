package com.neetcode150.linked_list;

public class Q5RemoveNthNodeFromEnd {

	public static void main(String[] args) {

		// 1,2,3,4,5
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		System.out.print("Before: ");
		ListNode.printList(head);

		ListNode resultList = removeNthFromEnd(head, 2);

		System.out.print("After:  ");
		ListNode.printList(resultList);
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {

		ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
		dummyNode.next = head;
		ListNode firstPtr = dummyNode;
		ListNode secondPtr = dummyNode;
		
		for (int i = 0; i < n; i++) {
			secondPtr = secondPtr.next;
		}
		
		while(secondPtr.next != null) {
			firstPtr = firstPtr.next;
			secondPtr = secondPtr.next;
		}
		
		firstPtr.next = firstPtr.next.next;

		return dummyNode.next;
	}
}
