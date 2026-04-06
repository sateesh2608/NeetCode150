package com.neetcode150.linked_list;

import java.util.HashMap;
import java.util.Map;

public class Q9LRUCache {

	static class LRUCache {

		Map<Integer,DoubleLinkedList> map = new HashMap<>();
		int capacity =0;
		DoubleLinkedList head = new DoubleLinkedList(-1, -1);
		DoubleLinkedList tail = new DoubleLinkedList(-1, -1);
		
		public LRUCache(int capacity) {

			this.capacity =capacity;
			map.clear();
			head.next = tail;
			tail.prev = head;
		}

		public int get(int key) {

			if(map.containsKey(key)) {
				DoubleLinkedList node = map.get(key);
				map.remove(key);
				
				// delete node
				deleteNode(node);
				
				// addAfterHead
				addAfterHead(node);
				map.put(key, node);
				
				return node.val;
			}
			return -1;
		}

		private void addAfterHead(DoubleLinkedList node) {

			DoubleLinkedList currNext =head.next;
			head.next = node;
			node.prev= head;
			node.next = currNext;
			currNext.prev = node;
		}

		private void deleteNode(DoubleLinkedList node) {
			DoubleLinkedList prevNode = node.prev;
			DoubleLinkedList nextNode = node.next;
			
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			
		}

		public void put(int key, int value) {

			
			if(map.containsKey(key)) {

				DoubleLinkedList node = map.get(key);
				node.val = value;
				deleteNode(node);
				addAfterHead(node);
				
			}else {
				if(capacity == map.size()) {
					DoubleLinkedList LRUNode = tail.prev;
					map.remove(LRUNode.key);
					deleteNode(LRUNode);
				}
				DoubleLinkedList node =new DoubleLinkedList(key,value); 
				map.put(key, node);
				addAfterHead(node);
			}
		}

		/**
		 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
		 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
		 */
	}

	public static void main(String[] args) {

		LRUCache lRUCache = new LRUCache(2);
		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		System.out.println(lRUCache.get(1)); // return 1
		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		System.out.println(lRUCache.get(2)); // returns -1 (not found)
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		System.out.println(lRUCache.get(1)); // return -1 (not found)
		System.out.println(lRUCache.get(3)); // return 3
		System.out.println(lRUCache.get(4)); // return 4
	}
}
