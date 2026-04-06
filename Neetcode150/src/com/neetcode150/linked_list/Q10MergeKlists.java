package com.neetcode150.linked_list;

import java.util.PriorityQueue;

public class Q10MergeKlists {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(5);

		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);

		ListNode l3 = new ListNode(2);
		l3.next = new ListNode(6);

		ListNode[] lists = new ListNode[] { l1, l2, l3 };

		Q10MergeKlists merger = new Q10MergeKlists();
		ListNode merged = merger.mergeKLists(lists);
		
		ListNode.printList(merged);
	}

	public ListNode mergeKLists(ListNode[] lists) {

		if (lists == null || lists.length == 0)
			return null;

		ListNode dummy = new ListNode(0);
	    ListNode tail = dummy;

		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

		// Step 1: push heads to priority queue
		for (ListNode listNode : lists) {
			if (listNode != null) {
				minHeap.offer(listNode);
			}
		}
		
		// Step 2: merge
		while (!minHeap.isEmpty()) {
	        ListNode node = minHeap.poll();

	        tail.next = node;
	        tail = tail.next;

	        if (node.next != null) {
	            minHeap.offer(node.next);
	        }
	    }
		
		
		return dummy.next;
	}
}
