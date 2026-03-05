package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _321maxNumber {

    public static class Solution1 {

        /**
         分治+单调栈：O(k^2 * (m + n)), O(max(m, n, k))
         Note:
         先考虑用单调栈解决从nums中选k个数字组成最大数字的问题，
         然后将问题分解成从nums1中选k1个数字组成最大数字，从nums2中选k2个数字组成最大数字，k1 + k2 = k，
         最后将nums1[k1]和nums2[k2]合并得到一个最大数字。
         */
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            int[] res = new int[k];
            int n1 = nums1.length, n2 = nums2.length;

            // 枚举：尝试从nums1中取i个数字，从nums2中取k-i个数字
            for (int i = Math.max(0, k - n2); i <= Math.min(k, n1); i++) {
                int[] candidate = merge(pickMax(nums1, i), pickMax(nums2, k - i));
                if (cmp(candidate, 0, res, 0)) {
                    res = candidate;
                }
            }
            return res;
        }

        /**
         Function: 从nums中选择k个数字组成最大数字
         Note: 类似"402.移掉k位数字"
         */
        private int[] pickMax(int[] nums, int k) {
            // 用单调栈删除(单调栈只能控制什么时候删除数字，无法控制最后剩下几个数字，所以要先计算删除数字的个数)
            int n = nums.length, drop = n - k;
            Deque<Integer> s = new ArrayDeque<>();
            for (int num : nums) {
                while (!s.isEmpty() && s.peek() < num && drop > 0) {
                    s.pop();
                    drop--;
                }
                s.push(num);
            }

            // 如果遍历完了还没删除够，继续删除栈顶元素直至够数(栈顶元素较小)
            for (int i = 0; i < drop; i++) {
                s.pop();
            }

            // 组装结果返回
            int[] res = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                res[i] = s.pop();
            }
            return res;
        }

        /**
         Function: 合并两个数组，使合并后的数组最大
         Note: 与归并排序中的merge类似
         */
        private int[] merge(int[] nums1, int[] nums2) {
            int n1 = nums1.length, n2 = nums2.length, n = n1 + n2;
            int[] res = new int[n];
            for (int i = 0, j = 0, k = 0; k < n; k++) {
                // 这里不能只比较nums1[i]和nums2[j]，因为可能nums1[i] == nums2[j]
                res[k] = cmp(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
            }
            return res;
        }

        /**
         Function: 比较两个数组从指定位置开始的子数组大小
         return true if nums1[i...] greater than nums2[j...], else return false
         */
        private boolean cmp(int[] nums1, int i, int[] nums2, int j) {
            while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
                i++;
                j++;
            }
            // 如果nums2先遍历完，或者nums1的当前元素大于nums2的当前元素，则nums1更大
            return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
        }


        @Test
        public void testPickMax() {
            System.out.println(Arrays.toString(pickMax(new int[] {9,1,2,5,8,3}, 1)));
            System.out.println(Arrays.toString(pickMax(new int[] {9,1,2,5,8,3}, 2)));
            System.out.println(Arrays.toString(pickMax(new int[] {9,1,2,5,8,3}, 3)));
            System.out.println(Arrays.toString(pickMax(new int[] {9,1,2,5,8,3}, 4)));
            System.out.println(Arrays.toString(pickMax(new int[] {9,1,2,5,8,3}, 5)));
            System.out.println(Arrays.toString(pickMax(new int[] {9,1,2,5,8,3}, 6)));

            System.out.println(Arrays.toString(pickMax(new int[] {9,2,2,5,8,3}, 4)));
            System.out.println(Arrays.toString(pickMax(new int[] {9,2,2,5,8,3}, 5)));
        }

    }

}
