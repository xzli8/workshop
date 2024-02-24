package data_structure_algorithm.leetcode;

public class _201rangeBitwiseAnd {

    public static class Solution1 {

        /**
         位运算：问题转换为"给定两个整数，我们要找到它们对应的二进制字符串的公共前缀"
         ref:https://leetcode.cn/problems/bitwise-and-of-numbers-range/solutions/384938/shu-zi-fan-wei-an-wei-yu-by-leetcode-solution/?envType=study-plan-v2&envId=top-interview-150
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int rangeBitwiseAnd(int left, int right) {
            // 利用"n & (n - 1)消去n的二进制表示末尾的1"
            while (left < right) right = right & (right - 1);
            return right;
        }

    }

}
