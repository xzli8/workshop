package data_structure_algorithm.leetcode;

public class _2844minimumOperations {

    public static class Solution1 {

        /**
         贪心：枚举末尾所有的可能，取最小值
         ref:https://leetcode.cn/problems/minimum-operations-to-make-a-special-number/solutions/2424068/mei-ju-shan-chu-hou-yi-00255075-jie-wei-zhjlu
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int minimumOperations(String num) {
            // 如果num包含0，那么将剩余的n-1个数字删掉；如果不包含0，需要将所有数字都删掉
            int maxCount = num.length() - (num.contains("0") ? 1 : 0);
            return min(maxCount, count(num, "00"), count(num, "25"), count(num, "50"), count(num, "75"));
        }

        private int count(String num, String tail) {
            int n = num.length();
            int i = num.lastIndexOf(tail.charAt(1));
            if (i <= 0) return n;
            i = num.lastIndexOf(tail.charAt(0), i - 1);
            return i < 0 ? n : n - i - 2; // n - 1 - i - 1
        }

        private int min(int x, int... y) {
            for (int v : y) {
                x = Math.min(x, v);
            }
            return x;
        }

    }

}
