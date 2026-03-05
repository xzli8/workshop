package data_structure_algorithm.leetcode;

public class _641MyCircularDeque {


    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */

    /**
     循环队列的两种实现方式
     链表：双向链表
     数组：牺牲一个存储空间；计数器(用size记录数组元素个数)；布尔标记(?)；不用牺牲一个存储空间也不用计数器(不断累加游标，操作次数多后会溢出)
     */

    public static class Solution1 {

        /**
         * 双链表
         */
        class MyCircularDeque {

            // Defination of doubly-linked list
            class ListNode {
                int val;
                ListNode prev, next;
                ListNode(int val) { this.val = val; }
            }

            private int capacity, size;
            private ListNode head, tail;

            public MyCircularDeque(int k) {
                capacity = k;
                size = 0;
            }

            public boolean insertFront(int value) {
                if (isFull()) return false;
                ListNode node = new ListNode(value);
                if (isEmpty()) {
                    head = tail = node;
                } else {
                    node.next = head;
                    head.prev = node;
                    head = node;
                }
                size++;
                return true;
            }

            public boolean insertLast(int value) {
                if (isFull()) return false;
                ListNode node = new ListNode(value);
                if (isEmpty()) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }
                size++;
                return true;
            }

            public boolean deleteFront() {
                if (isEmpty()) return false;
                head = head.next;
                if (head != null) head.prev = null;
                size--;
                return true;
            }

            public boolean deleteLast() {
                if (isEmpty()) return false;
                tail = tail.prev;
                if (tail != null) tail.next = null;
                size--;
                return true;
            }

            public int getFront() {
                return isEmpty() ? -1 : head.val;
            }

            public int getRear() {
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
         数组+双指针：牺牲一个存储空间 -> 插入或删除时不用多一次维护计数器值的计算
         */
        class MyCircularDeque {

            private int[] nums;
            private int capacity, head, tail;   // head -> 队头元素，tail -> 队尾元素的下一个位置

            public MyCircularDeque(int k) {
                head = tail = 0;
                capacity = k + 1;
                nums = new int[capacity];
            }

            public boolean insertFront(int value) {
                if (isFull()) return false;
                if (--head < 0) head += capacity;
                nums[head] = value;
                return true;
            }

            public boolean insertLast(int value) {
                if (isFull()) return false;
                nums[tail] = value;
                if (++tail == capacity) tail -= capacity;
                return true;
            }

            public boolean deleteFront() {
                if (isEmpty()) return false;
                if (++head == capacity) head -= capacity;
                return true;
            }

            public boolean deleteLast() {
                if (isEmpty()) return false;
                if (--tail < 0) tail += capacity;
                return true;
            }

            public int getFront() {
                if (isEmpty()) return -1;
                return nums[head];
            }

            public int getRear() {
                if (isEmpty()) return -1;
                return nums[(tail - 1 + capacity) % capacity];
            }

            public boolean isEmpty() {
                return head == tail;
            }

            public boolean isFull() {
                return (tail + 1) % capacity == head;
            }

        }
    }

}
