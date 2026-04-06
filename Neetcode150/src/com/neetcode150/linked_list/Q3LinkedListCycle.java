package com.neetcode150.linked_list;

public class Q3LinkedListCycle {

	public static void main(String[] args) {

		ListNode list1 = new ListNode(1);
		list1.next = new ListNode(2);
		list1.next.next = new ListNode(3);
		list1.next.next.next = list1.next;
		
		System.out.println(hasCycle(list1));
		System.out.println(startOfCycle(list1));
		ListNode.printList(list1);
		
	}

	private static int startOfCycle(ListNode head) {

		ListNode slow = head;
		ListNode fast = head;
		
		while(fast != null && fast.next != null) {
			
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow == fast) {
				while(head != slow) {
					head = head.next;
					slow = slow.next;
				}
				
				return slow.val;
			}
		}
		return -1;
	}

	public static boolean hasCycle(ListNode head) {

		ListNode slow = head;
		ListNode fast = head;
		
		while(fast != null && fast.next != null) {
			
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow == fast) return true;
		}
		
		return false;

	}

}
