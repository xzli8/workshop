package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _556nextGreaterElement {

    public static class Solution1 {

        /**
         与“31-下一个排列”相同
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int nextGreaterElement(int n) {
            // 将n表示为数组
            String str = n + "";
            int len = str.length();
            int[] nums = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = str.charAt(i) - '0';
            }

            // 从后往前遍历，找第一个逆序对，交换逆序对中较小元素与已遍历过的元素中最大的元素
            // 然后将逆序对中较小元素后的所有元素升序排列
            for (int i = len - 2; i >= 0; i--) {
                // 找到逆序对
                if (nums[i] < nums[i+1]) {
                    // 从已遍历过的区间中找第一个大于nums[i]的元素，已遍历区间为升序（从后往前看）
                    for (int j = len - 1; j > i; j--) {
                        if (nums[j] > nums[i]) {
                            int tmp = nums[j];
                            nums[j] = nums[i];
                            nums[i] = tmp;
                            break;
                        }
                    }

                    // 将从i+1到末尾的元素，按照升序排列
                    Arrays.sort(nums, i+1, len);

                    // 输出结果
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < len; j++) {
                        sb.append(nums[j]);
                    }
                    String res = sb.toString();
                    return Long.valueOf(res) > Integer.MAX_VALUE ? -1 : Integer.valueOf(res);
                }
            }

            // 不存在这样的正整数（已经是最大数，没有逆序对），返回-1
            return -1;
        }

    }

}
