package data_structure_algorithm.leetcode;

import java.util.Arrays;
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



    public static class Solution2 {


        /**
         跳表:多层级链表
         ref: https://leetcode.cn/problems/design-skiplist/solutions/1696545/she-ji-tiao-biao-by-leetcode-solution-e8yh/
         时间复杂度：O(logN)
         空间复杂度：O(N)
         */
        class Skiplist {

            // 跳表节点定义
            class SkipListNode {
                int val;
                SkipListNode[] forward;

                public SkipListNode(int val, int maxLevel) {
                    this.val = val;
                    this.forward = new SkipListNode[maxLevel];
                }
            }

            // 常量定义
            public static final int MAX_LEVEL = 32;
            public static final double P_FACTOR = 0.25;

            private SkipListNode head = new SkipListNode(-1, MAX_LEVEL);
            private int level = 0;
            private Random random = new Random();

            public Skiplist() {
                // nothing here
            }

            public boolean search(int target) {
                SkipListNode cur = this.head;
                for (int i = level - 1; i >= 0; i--) {
                    while (cur.forward[i] != null && cur.forward[i].val < target) {
                        cur = cur.forward[i];
                    }
                }
                cur = cur.forward[0];
                if (cur != null && cur.val == target) return true;
                return false;
            }

            public void add(int num) {
                // 找到第i层小于且最接近num的元素
                SkipListNode[] update = new SkipListNode[MAX_LEVEL];
                Arrays.fill(update, head);
                SkipListNode cur = this.head;
                for (int i = level - 1; i >= 0; i--) {
                    while (cur.forward[i] != null && cur.forward[i].val < num) {
                        cur = cur.forward[i];
                    }
                    update[i] = cur;
                }

                // 对i层的状态进行更新，将当前元素的forward指向新的节点
                int lv = randomLevel();
                level = Math.max(level, lv);
                SkipListNode newNode = new SkipListNode(num, lv);
                for (int i = 0; i < lv; i++) {
                    newNode.forward[i] = update[i].forward[i];
                    update[i].forward[i] = newNode;
                }
            }

            public boolean erase(int num) {
                // 找到第i层小于且最接近num的元素
                SkipListNode[] update = new SkipListNode[MAX_LEVEL];
                SkipListNode cur = this.head;
                for (int i = level - 1; i >= 0; i--) {
                    while (cur.forward[i] != null && cur.forward[i].val < num) {
                        cur = cur.forward[i];
                    }
                    update[i] = cur;
                }

                // 如果不存在返回false
                cur = cur.forward[0];
                if (cur == null || cur.val != num) return false;

                // 更新每一层的状态，将forward指针指向下一跳
                for (int i = 0; i < level; i++) {
                    if (update[i].forward[i] != cur) break;
                    update[i].forward[i] = cur.forward[i];
                }

                // 更新当前的level
                while (level > 1 && head.forward[level - 1] == null) level--;
                return true;
            }

            // 随机生成新加入节点的层数
            private int randomLevel() {
                int lv = 1;
                while (random.nextDouble() < P_FACTOR && lv < MAX_LEVEL) lv++;
                return lv;
            }
        }

    }

}
