package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _622MyCircularQueue {

    public static class Solution1 {


        /**
         链表
         */
        class MyCircularQueue {

            private int capacity;
            private int size;
            private ListNode head;
            private ListNode tail;

            public MyCircularQueue(int k) {
                capacity = k;
                size = 0;
            }

            public boolean enQueue(int value) {
                if (isFull()) return false;
                ListNode node = new ListNode(value);
                if (null == head) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }
                size++;
                return true;
            }

            public boolean deQueue() {
                if (isEmpty()) return false;
                head = head.next;
                size--;
                return true;
            }

            public int Front() {
                return isEmpty() ? -1 : head.val;
            }

            public int Rear() {
                return isEmpty() ? -1 : tail.val;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean isFull() {
                return size == capacity;
            }
        }

    }



    public static class Solution2 {

        /**
         数组1：用size记录队列大小
         */
        class MyCircularQueue {

            private int capacity;
            private int[] elements;
            private int head;
            private int tail;
            private int size;

            public MyCircularQueue(int k) {
                capacity = k;
                elements = new int[k];
                head = 0;
                tail = 0;
                size = 0;
            }

            public boolean enQueue(int value) {
                if (isFull()) return false;
                elements[tail++] = value;
                if (tail == capacity) tail -= capacity;
                size++;
                return true;
            }

            public boolean deQueue() {
                if (isEmpty()) return false;
                head++;
                if (head == capacity) head -= capacity;
                size--;
                return true;
            }

            public int Front() {
                if (isEmpty()) return -1;
                return elements[head];
            }

            public int Rear() {
                if (isEmpty()) return -1;
                return elements[(head+size-1)%capacity];
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean isFull() {
                return size == capacity;
            }
        }

    }



    public static class Solution3 {

        /**
         数组2：不用size记录队列大小
         */
        class MyCircularQueue {

            private int capacity;
            private int[] items;
            private int head;   // 头节点
            private int tail;   // 尾节点的下一个节点

            public MyCircularQueue(int k) {
                capacity = k;
                items = new int[capacity];
                head = 0;
                tail = 0;
            }

            public boolean enQueue(int value) {
                if (isFull()) return false;
                items[tail%capacity] = value;
                tail++;
                return true;
            }

            public boolean deQueue() {
                if (isEmpty()) return false;
                head++;
                return true;
            }

            public int Front() {
                return isEmpty() ? -1 : items[head%capacity];
            }

            public int Rear() {
                return isEmpty() ? -1 : items[(tail-1)%capacity];
            }

            public boolean isEmpty() {
                return head == tail;
            }

            public boolean isFull() {
                return tail - head == capacity;
            }
        }

    }


}
