package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _1823findTheWinner {

    public static class Solution1 {

        /**
         用环形链表模拟
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int findTheWinner(int n, int k) {
             // 构建环形链表
             Node head = new Node(1), prev = head;
             for (int i = 2; i <= n; i++) {
                 Node node = new Node(i);
                 prev.next = node;
                 prev = node;
             }
             prev.next = head;

             // 模拟
             for (int i = 1; i < n; i++) {
                 for (int j = 1; j < k; j++) {
                     prev = prev.next;
                 }
                 prev.next = prev.next.next;
             }
             return prev.val;
         }

         // 链表节点定义
         class Node {
             int val;
             Node next;

             public Node(int val) {
                 this.val = val;
             }

             public Node(int val, Node next) {
                 this.val = val;
                 this.next = next;
             }
         }

    }



    public static class Solution2 {

        /**
         用队列模拟
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int findTheWinner(int n, int k) {
             Queue<Integer> q = new ArrayDeque<>();
             for (int i = 1; i <= n; i++) q.offer(i);
             while (q.size() > 1) {
                 for (int i = 1; i < k; i++) q.offer(q.poll());
                 q.poll();
             }
             return q.peek();
         }

    }



    public static class Solution3 {

        /**
         递推公式：约瑟夫环
         ref: https://blog.csdn.net/u011500062/article/details/72855826
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findTheWinner(int n, int k) {
            if (n <= 1) return n;
            int res = (findTheWinner(n - 1, k) + k) % n;
            return res == 0 ? n : res;
        }

    }

}
