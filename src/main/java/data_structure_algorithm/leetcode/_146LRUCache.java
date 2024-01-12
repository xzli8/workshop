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

            // 双链表节点定义
            class Node {
                int key, val;
                Node prev, next;

                public Node() {}

                public Node(int key, int val) {
                    this.key = key;
                    this.val = val;
                }
            }

            private int capacity;
            private Node head, tail;
            private Map<Integer, Node> key2Node = new HashMap<>();

            public LRUCache(int capacity) {
                this.capacity = capacity;
                this.head = new Node();
                this.tail = new Node();
                head.next = tail;
                tail.prev = head;
            }

            public int get(int key) {
                Node node = key2Node.get(key);
                if (node == null) return -1;
                moveToHead(node);
                return node.val;
            }

            public void put(int key, int value) {
                Node node = key2Node.get(key);
                if (node != null) {
                    node.val = value;
                    moveToHead(node);
                } else {
                    node = new Node(key, value);
                    key2Node.put(key, node);
                    addToHead(node);
                    if (key2Node.size() > capacity) {
                        Node eldest = removeNode(tail.prev);
                        key2Node.remove(eldest.key);
                    }
                }
            }

            private void addToHead(Node node) {
                head.next.prev = node;
                node.next = head.next;
                head.next = node;
                node.prev = head;
            }

            private Node removeNode(Node node) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                return node;
            }

            private void moveToHead(Node node) {
                removeNode(node);
                addToHead(node);
            }
        }

    }

}
