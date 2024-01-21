package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _138copyRandomList {

    /**
     分析：本题的难点在于随机指针，在顺序复制的时候，随机指针指向的节点可能还没有被创建
     */

    public static class Solution1 {

        // Definition for a Node.
        class Node {
            int val;
            Node next;
            Node random;

            public Node(int val) {
                this.val = val;
                this.next = null;
                this.random = null;
            }
        }

        /**
         哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public Node copyRandomList(Node head) {
             if (head == null) return null;

             // 遍历原链表，复制原链表的节点，再以原链表的node为key，新链表的node为val建立映射
             Map<Node, Node> map = new HashMap<>();
             Node p = head;
             while (p != null) {
                 Node newNode = new Node(p.val);
                 map.put(p, newNode);
                 p = p.next;
             }

             // 再次遍历原链表，补充新链表的next, random指针信息
             p = head;
             while (p != null) {
                 Node newNode = map.get(p);
                 newNode.next = p.next == null ? null : map.get(p.next);
                 newNode.random = p.random == null ? null : map.get(p.random);
                 p = p.next;
             }
             return map.get(head);
         }

    }



    public static class Solution2 {

        // Definition for a Node.
        class Node {
            int val;
            Node next;
            Node random;

            public Node(int val) {
                this.val = val;
                this.next = null;
                this.random = null;
            }
        }

        /**
         “有丝分裂”：复制当前节点到相邻位置，再次遍历时，new的random指针指向original的random指针的next
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public Node copyRandomList(Node head) {
            if (head == null) return null;

            // 复制节点的val和next
            for (Node node = head; node != null; node = node.next.next) {
                Node newNode = new Node(node.val);
                newNode.next = node.next;
                node.next = newNode;
            }
            // 确定新节点的random指针
            for (Node node = head; node != null; node = node.next.next) {
                Node newNode = node.next;
                newNode.random = node.random == null ? null : node.random.next;
            }
            // 将两个链表分离
            Node newHead = head.next;
            for (Node node = head; node != null; node = node.next) {
                Node newNode = node.next;
                node.next = node.next.next;
                newNode.next = newNode.next == null ? null : newNode.next.next;
            }
            return newHead;
        }

    }

}
