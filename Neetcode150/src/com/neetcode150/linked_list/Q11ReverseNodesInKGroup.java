package com.neetcode150.linked_list;

public class Q11ReverseNodesInKGroup {
	public static void main(String[] args) {
		 ListNode head = new ListNode(1);
		    head.next = new ListNode(2);
		    head.next.next = new ListNode(3);
		    head.next.next.next = new ListNode(4);
		    head.next.next.next.next = new ListNode(5);

		    int k = 2;

		    System.out.print("Before: ");
		    ListNode.printList(head);

		    ListNode result = reverseKGroup(head, k);

		    System.out.print("After:  ");
		    ListNode.printList(result);
	}

	private static ListNode reverseKGroup(ListNode head, int k) {
	    if (head == null || k == 1) return head;

	    ListNode dummy = new ListNode(0);
	    dummy.next = head;

	    ListNode prevGroupEnd = dummy;
	    ListNode temp = head;

	    while (temp != null) {
	        ListNode kthNode = provideKthNode(temp, k);
	        if (kthNode == null) break;

	        ListNode nextNode = kthNode.next;
	        kthNode.next = null;

	        // reverse current k-group
	        ListNode reversedHead = Q1ReverseLinkedList.reverseListWithoutExtraSpace(temp);

	        // connect previous group
	        prevGroupEnd.next = reversedHead;

	        // temp is now the tail
	        temp.next = nextNode;

	        // move pointers
	        prevGroupEnd = temp;
	        temp = nextNode;
	    }

	    return dummy.next;
	}

	private static ListNode provideKthNode(ListNode temp, int k) {

		 while (temp != null && k > 1) {
		        temp = temp.next;
		        k--;
		    }
		    return temp; // may be null (correct)
		}
}
