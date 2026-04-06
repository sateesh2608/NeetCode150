package com.neetcode150.linked_list;

import java.util.HashSet;
import java.util.Set;

public class ListNode {

	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
	
	public static void printList(ListNode head) {
		// this set is for printing cycle list and this is useless in case non cycle
		Set<ListNode> visited = new HashSet<>();
	    ListNode curr = head;
	    while (curr != null) {
	    	if (visited.contains(curr)) {
	            System.out.print(curr.val + " -> (cycle starts here)");
	            break;
	        }
	        visited.add(curr);
	        System.out.print(curr.val);
	        if (curr.next != null) {
	            System.out.print(" -> ");
	        }
	        curr = curr.next;
	    }
	    System.out.println();
	}
}
