package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _622MyCircularQueue {


    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */

    /**
     循环队列的两种实现方式
     链表：单链表；双链表
     数组：牺牲一个存储空间；计数器；布尔标记(?)；不用牺牲一个存储空间也不用计数器(不断累加游标，操作次数多后会溢出)

     ref:https://leetcode.cn/problems/design-circular-queue/solutions/56619/shu-zu-shi-xian-de-xun-huan-dui-lie-by-liweiwei141/
     */

    public static class Solution0 {

        /**
         数组(牺牲一个存储空间)：插入或删除时不用多一次维护计数器值的计算
         */
        class MyCircularQueue {

            private int front;  // 指向队头元素
            private int rear;   // 指向队尾元素的下一个位置
            private int capacity;
            private int[] nums;

            public MyCircularQueue(int k) {
                this.front = 0;
                this.rear = 0;
                this.capacity = k + 1;
                this.nums = new int[capacity];
            }

            public boolean enQueue(int value) {
                if (isFull()) return false;
                nums[rear++] = value;
                if (rear >= capacity) rear -= capacity;
                return true;
            }

            public boolean deQueue() {
                if (isEmpty()) return false;
                if (++front >= capacity) front -= capacity;
                return true;
            }

            public int Front() {
                if (isEmpty()) return -1;
                return nums[front];
            }

            public int Rear() {
                if (isEmpty()) return -1;
                int index = rear - 1;
                if (index < 0) index += capacity;
                return nums[index];
            }

            public boolean isEmpty() {
                return front == rear;
            }

            public boolean isFull() {
                int index = rear + 1;
                if (index >= capacity) index -= capacity;
                return index == front;
                // return (rear + 1) % capacity == front;
            }
        }


    }

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
