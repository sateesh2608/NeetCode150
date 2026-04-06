package com.neetcode150.linked_list;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
    
    public static void printRandomList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print("Node val: " + curr.val);
            if (curr.random != null) {
                System.out.print(", Random points to: " + curr.random.val);
            } else {
                System.out.print(", Random points to: null");
            }
            System.out.println();
            curr = curr.next;
        }
    }
}
