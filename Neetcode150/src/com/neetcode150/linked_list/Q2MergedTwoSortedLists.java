package com.neetcode150.linked_list;

public class Q2MergedTwoSortedLists {

	public static void main(String[] args) {

		ListNode list1 = new ListNode(1);
		list1.next = new ListNode(2);
		list1.next.next = new ListNode(4);

		ListNode list2 = new ListNode(1);
		list2.next = new ListNode(3);
		list2. next.next = new ListNode(4);

		System.out.print("Before List 1 is: ");
		ListNode.printList(list1);
		System.out.print("Before List 2 is: ");
		ListNode.printList(list2);

		ListNode mergedList = mergeTwoLists(list1, list2);

	    System.out.print("After merged new list is:  ");
	    ListNode.printList(mergedList);
		
	}

	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

		if(list1 == null) return list2;
		if(list2 == null) return list1;
		
		ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
		ListNode temp = dummyNode;
		
		while(list1 != null && list2 != null) {
			
			if(list1.val <= list2.val) {
				temp.next = list1;
				temp = list1;
				list1 = list1.next;
			}else {
				temp.next = list2;
			 	temp = list2;
			 	list2 = list2.next;
			}
		}
		
		if(list1 != null) temp.next = list1; 
		if(list2 != null) temp.next = list2; 
		
		return dummyNode.next;
	}

}
