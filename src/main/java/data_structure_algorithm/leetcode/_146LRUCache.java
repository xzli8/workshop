package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _146LRUCache {


    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static class Solution1 {

        /**
         双链表 + 哈希表
         */
        class LRUCache {

            class ListNode {
                int key;
                int val;
                ListNode prev;
                ListNode next;

                ListNode() {}
                ListNode(int key, int val) {this.key = key; this.val = val;}
            }

            private int size;
            private int capacity;
            private ListNode head, tail;
            private Map<Integer, ListNode> cache = new HashMap<>();

            public LRUCache(int capacity) {
                this.size = 0;
                this.capacity = capacity;
                head = new ListNode();
                tail = new ListNode();
                head.next = tail;
                tail.prev = head;
            }

            public int get(int key) {
                ListNode node = cache.get(key);
                if (null == node) {
                    return -1;
                }
                moveToHead(node);
                return node.val;
            }

            public void put(int key, int value) {
                ListNode node = cache.get(key);
                if (null == node) {
                    node = new ListNode(key, value);
                    cache.put(key, node);
                    addToHead(node);
                    if (++size > capacity) {
                        ListNode eldest = removeNode(tail.prev);
                        cache.remove(eldest.key);
                        size--;
                    }
                } else {
                    node.val = value;
                    moveToHead(node);
                }
            }

            private ListNode removeNode(ListNode node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                return node;
            }

            private void addToHead(ListNode node) {
                head.next.prev = node;
                node.next = head.next;
                head.next = node;
                node.prev = head;
            }

            private void moveToHead(ListNode node) {
                removeNode(node);
                addToHead(node);
            }
        }

    }

}
