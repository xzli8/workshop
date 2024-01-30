package data_structure_algorithm.leetcode;

public class Buchongti_12getNext {

    /**
     * 题目链接：https://www.nowcoder.com/questionTerminal/9023a0c988684a53960365b889ceaf5e
     * 题解链接：https://mp.weixin.qq.com/s/yewlHvHSilMsrUMFIO8WAA
     */

    // Defination
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }


    public class Solution {

        public TreeLinkNode GetNext(TreeLinkNode pNode) {
            // 存在右子树时，返回右子树的最左侧节点
            if (pNode.right != null) {
                TreeLinkNode p = pNode.right;
                while (p.left != null) p = p.left;
                return p;
            }

            // 不存在右子树时，找第一个当前节点是父节点左孩子的节点
            TreeLinkNode p = pNode;
            while (p.next != null) {
                if (p.next.left == p) return p.next;
                p = p.next;
            }
            return null;    // 找到根节点都还没找到
        }

    }

}
