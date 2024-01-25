package data_structure_algorithm.leetcode;

public class _238productExceptSelf {

    public static class Solution1 {

        /**
         把res看作矩阵来构建：(相同题："剑指offer66.构建乘积数组")
         思路：先计算下三角各元素乘积，直接乘入res[i]；然后计算上三角各元素乘积，记为tmp，并乘入res[i]
         ref:https://leetcode.cn/problems/product-of-array-except-self/solutions/11472/product-of-array-except-self-shang-san-jiao-xia-sa/
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] productExceptSelf(int[] nums) {
            // 初始化
            int n = nums.length;
            int[] res = new int[n];
            res[0] = 1;

            // 计算下三角各元素乘积，直接乘入res[i]
            for (int i = 1; i < n; i++) res[i] = res[i - 1] * nums[i - 1];

            // 计算上三角各元素乘积，记为tmp，并乘入res[i]
            for (int i = n - 2, tmp = 1; i >= 0; i--) {
                tmp *= nums[i + 1];
                res[i] *= tmp;
            }
            return res;
        }

    }

}
