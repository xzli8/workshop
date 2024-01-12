package data_structure_algorithm.leetcode;

import org.junit.Test;

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
     链表：单链表/双链表
     数组：牺牲一个存储空间；计数器；布尔标记(?)；不用牺牲一个存储空间也不用计数器(不断累加游标，操作次数多后会溢出)
     */

    public static class Solution1 {

        /**
         数组(牺牲一个存储空间)：插入或删除时不用多一次维护计数器值的计算
         */
        class MyCircularDeque {

            private int front;  // 指向队头元素
            private int rear;   // 指向队尾元素的下一个位置
            private int capacity;
            private int[] nums;

            public MyCircularDeque(int k) {
                this.front = 0;
                this.rear = 0;
                this.capacity = k + 1;
                this.nums = new int[capacity];
            }

            public boolean insertFront(int value) {
                if (isFull()) return false;
                if (--front < 0) front += capacity;
                nums[front] = value;
                return true;
            }

            public boolean insertLast(int value) {
                if (isFull()) return false;
                nums[rear] = value;
                if (++rear >= capacity) rear -= capacity;
                return true;
            }

            public boolean deleteFront() {
                if (isEmpty()) return false;
                if (++front >= capacity) front -= capacity;
                return true;
            }

            public boolean deleteLast() {
                if (isEmpty()) return false;
                if (--rear < 0) rear += capacity;
                return true;
            }

            public int getFront() {
                if (isEmpty()) return -1;
                return nums[front];
            }

            public int getRear() {
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
            }

        }
    }

}
