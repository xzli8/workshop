package data_structure_algorithm.leetcode;

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
         链表
         */
        class MyCircularQueue {

           /**
            * Definition for singly-linked list.
            */
            public class ListNode {
                int val;
                ListNode next;
                ListNode(int val) { this.val = val; }
            }


            private int capacity, size;
            private ListNode head, tail;    // head -> 队头元素，tail -> 队尾元素

            public MyCircularQueue(int k) {
                capacity = k;
                size = 0;
            }

            public boolean enQueue(int value) {
                if (isFull()) return false;
                ListNode node = new ListNode(value);
                if (isEmpty()) {
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



    public static class Solution1 {

        /**
         数组 + 双指针：牺牲一个存储空间 -> 插入或删除时不用多一次维护计数器值的计算
         Note: 如果不多一个空位置，那么isFull和isEmpty的条件都是head == tail，为了避免冲突选择浪费一个存储位置
         Ref: https://leetcode.cn/problems/design-circular-queue/solutions/56619/shu-zu-shi-xian-de-xun-huan-dui-lie-by-liweiwei141/
         */
        class MyCircularQueue {

            private int[] nums;
            private int capacity, head, tail;   // head -> 队头元素，tail -> 队尾元素的下一个位置

            public MyCircularQueue(int k) {
                head = tail = 0;
                capacity = k + 1;
                nums = new int[capacity];
            }

            public boolean enQueue(int value) {
                if (isFull()) return false;
                nums[tail] = value;
                if (++tail == capacity) tail -= capacity;
                return true;
            }

            public boolean deQueue() {
                if (isEmpty()) return false;
                if (++head == capacity) head -= capacity;
                return true;
            }

            public int Front() {
                if (isEmpty()) return -1;
                return nums[head];
            }

            public int Rear() {
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



    public static class Solution2 {

        /**
         数组 + 双指针：用size记录队列大小
         */
        class MyCircularQueue {

            private int[] elements;
            private int capacity, size, head, tail; // head -> 头元素，tail -> 尾元素的下一个

            public MyCircularQueue(int k) {
                capacity = k;
                elements = new int[k];
                head = tail = size = 0;
            }

            public boolean enQueue(int value) {
                if (isFull()) return false;
                elements[tail] = value;
                if (++tail == capacity) tail -= capacity;
                size++;
                return true;
            }

            public boolean deQueue() {
                if (isEmpty()) return false;
                if (++head == capacity) head -= capacity;
                size--;
                return true;
            }

            public int Front() {
                if (isEmpty()) return -1;
                return elements[head];
            }

            public int Rear() {
                if (isEmpty()) return -1;
                // return elements[(head + size - 1) % capacity];

                // 另一种写法(+capacity再%capacity是因为tail-1可能为负数，这里要进行修正)
                return elements[(tail - 1 + capacity) % capacity];
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
         数组 + 双指针：不用size记录队列大小(这样写不太好，head和tail一直增加可能会溢出)
         */
        class MyCircularQueue {

            private int capacity;
            private int[] items;
            private int head;   // 头节点
            private int tail;   // 尾节点的下一个节点

            public MyCircularQueue(int k) {
                capacity = k;
                items = new int[capacity];
                head = tail = 0;
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
