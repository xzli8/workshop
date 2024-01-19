package data_structure_algorithm.leetcode;

import java.util.Random;

public class _1206Skiplist {


    /**
     * Your Skiplist object will be instantiated and called as such:
     * Skiplist obj = new Skiplist();
     * boolean param_1 = obj.search(target);
     * obj.add(num);
     * boolean param_3 = obj.erase(num);
     */

    public static class Solution1 {

        /**
         跳表：多级链表
         时间复杂度：O(logN)
         空间复杂度：O(N)
         */
        class Skiplist {

            // 跳表节点
            class Node {
                int val;
                Node[] nexts;

                public Node(int val, int level) {
                    this.val = val;
                    this.nexts = new Node[level];
                }
            }

            private int level = 10;     // 层数最大值
            private Node head = new Node(-1, level);    // 头节点
            private Random random = new Random();   // 随机数，插入节点时用随机数动态确定层数

            // 查询(核心函数)：返回每一层小于并且最接近目标值target的节点
            private Node[] find(int target) {
                Node[] res = new Node[level];
                // 从最高层开始往下层查询
                Node cur = head;
                for (int i = level - 1; i >= 0; i--) {
                    while (cur.nexts[i] != null && cur.nexts[i].val < target) cur = cur.nexts[i];
                    res[i] = cur;
                }
                return res;
            }

            public Skiplist() {
            }

            public boolean search(int target) {
                Node[] res = find(target);
                return res[0].nexts[0] != null && res[0].nexts[0].val == target;
            }

            public void add(int num) {
                Node[] res = find(num);
                Node node = new Node(num, level);
                for (int i = 0; i < level; i++) {
                    node.nexts[i] = res[i].nexts[i];
                    res[i].nexts[i] = node;
                    if (random.nextInt(2) == 0) break;  // 有一半的概率再增加一层
                }
            }

            public boolean erase(int num) {
                Node[] res = find(num);
                Node node = res[0].nexts[0];
                if (node == null || node.val != num) return false;
                for (int i = 0; i < level && res[i].nexts[i] == node; i++) {
                    res[i].nexts[i] = res[i].nexts[i].nexts[i];
                }
                return true;
            }
        }

    }

}
