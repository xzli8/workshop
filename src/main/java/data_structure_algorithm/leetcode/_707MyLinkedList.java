package data_structure_algorithm.leetcode;

public class _707MyLinkedList {

    public static class Solution1 {

        /**
         单链表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        class MyLinkedList {

            public class ListNode {
                public int val;
                public ListNode next;
                public ListNode() {}
                public ListNode(int val) { this.val = val; }
                public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
            }

            private int size;
            private ListNode dummy;

            public MyLinkedList() {
                size = 0;
                dummy = new ListNode();
            }

            public int get(int index) {
                if (index < 0 || index >= size) return -1;
                ListNode p = dummy.next;
                while (index > 0) {
                    p = p.next;
                    index--;
                }
                return p.val;
            }

            public void addAtHead(int val) {
                ListNode p = new ListNode(val, dummy.next);
                dummy.next = p;
                size++;
            }

            public void addAtTail(int val) {
                ListNode p = dummy;
                while (p.next != null) {
                    p = p.next;
                }
                ListNode newNode = new ListNode(val, p.next);
                p.next = newNode;
                size++;
            }

            public void addAtIndex(int index, int val) {
                if (index > size) return;
                ListNode p = dummy;
                while (index > 0) {
                    p = p.next;
                    index--;
                }
                ListNode newNode = new ListNode(val, p.next);
                p.next = newNode;
                size++;
            }

            public void deleteAtIndex(int index) {
                if (index < 0 || index >= size) return;
                ListNode p = dummy;
                while (index > 0) {
                    p = p.next;
                    index--;
                }
                p.next = p.next.next;
                size--;
            }

            public String toString(ListNode dummy) {
                StringBuilder sb = new StringBuilder();
                ListNode p = dummy;
                while (p != null) {
                    sb.append(p.val).append("->");
                    p = p.next;
                }
                return sb.toString();
            }

        }

        /**
         * Your MyLinkedList object will be instantiated and called as such:
         * MyLinkedList obj = new MyLinkedList();
         * int param_1 = obj.get(index);
         * obj.addAtHead(val);
         * obj.addAtTail(val);
         * obj.addAtIndex(index,val);
         * obj.deleteAtIndex(index);
         */
    }


}
