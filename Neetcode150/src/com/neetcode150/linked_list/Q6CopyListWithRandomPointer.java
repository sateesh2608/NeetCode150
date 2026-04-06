package com.neetcode150.linked_list;

public class Q6CopyListWithRandomPointer {

	public static void main(String[] args) {

		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);

		node1.next = node2;
		node2.next = node3;

		node1.random = node3;
		node2.random = node1;
		node3.random = node3;

		System.out.println("Before Copy original LL is: ");
		Node.printRandomList(node1);
		System.out.println();

		Node copiedList = copyRandomList(node1);

		System.out.println("Before Copy original LL is: ");
		Node.printRandomList(copiedList);

	}

	public static Node copyRandomList(Node head) {

		// edge case
		if(head == null) return null;
		
		// 3 step process

		// step 1: create New Node exact copy of each node and insert in the list
		Node curr = head;
		while (curr != null) {
			Node newNode = new Node(curr.val);
			newNode.next = curr.next;
			curr.next = newNode;
			curr = newNode.next;
		}

		// step 2: copy random and add to newly created nodes
		curr = head;
		while (curr != null) {

			if (curr.random != null) {
				curr.next.random = curr.random.next;
			}
			curr = curr.next.next;
		}

		// step 3: Detach copied list from original list
		curr = head;
		Node newHead = head.next;
		Node newCurr = newHead;
		while (curr != null) {
			curr.next = newCurr.next;
			curr = curr.next;

			if (curr != null) {
				newCurr.next = curr.next;
				newCurr = newCurr.next;
			}

		}
		return newHead;
	}
}
