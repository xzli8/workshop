package data_structure_algorithm.leetcode.supplyment;

import org.junit.Test;

import java.util.Scanner;

public class SmallSum {

    /**
     *  题目：https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469?tpId=101&tqId=33089&tPage=1&rp=1&ru=%2Fta%2Fprogrammer-code-interview-guide
     *      题解：https://mp.weixin.qq.com/s/rMsbcUf9ZPhvfRoyZGW6HA
     *      分析：与“数组中的逆序对”类似，有多种解法。从i的角度考虑比较简单，从j的角度考虑要复杂一些。
     */

    // 返回值 + i后面比i大的数
    public static class Solution1 {


        // ACM模式
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }
            System.out.println(new Solution1().smallSum(nums));
        }

        @Test
        public void test() {
            int[] nums = new int[] {1, 3, 5, 2, 4, 6};
            System.out.println(smallSum(nums));
        }

        public int smallSum(int[] nums) {
            int n = nums.length;
            int[] tmp = new int[n];
            return mergeSort(nums, tmp, 0, n - 1);
        }

        private int mergeSort(int[] nums, int[] tmp, int left, int right) {
            if (left >= right) return 0;
            int mid = left + ((right - left) >> 1);
            return mergeSort(nums, tmp, left, mid) + mergeSort(nums, tmp, mid + 1, right)
                    + merge(nums, tmp, left, mid, right);
        }

        private int merge(int[] nums, int[] tmp, int left, int mid, int right) {
            for (int k = left; k <= right; k++) {
                tmp[k] = nums[k];
            }

            int i = left, j = mid + 1, sum = 0;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1) {
                    nums[k] = tmp[i++];
                } else if (tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i++];
                    // tmp[i] <= tmp[j]，说明tmp[i]小于等于tmp[j...right]
                    sum += (right - j + 1) * nums[k];
                } else {
                    nums[k] = tmp[j++];
                }
            }
            return sum;
        }

    }

    // 全局变量 + j前面比j小的数
    public static class Solution2 {

        // ACM模式
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }
            System.out.println(new Solution2().smallSum(nums));
        }

        @Test
        public void test() {
            int[] nums = new int[] {1, 3, 5, 2, 4, 6};
            System.out.println(smallSum(nums));
        }

        public int smallSum(int[] nums) {
            int n = nums.length;
            int[] tmp = new int[n];
            mergeSort(nums, tmp, 0, n - 1);
            return sum;
        }

        private int sum = 0;

        private void mergeSort(int[] nums, int[] tmp, int left, int right) {
            if (left >= right) return;
            int mid = left + ((right - left) >> 1);
            mergeSort(nums, tmp, left, mid);
            mergeSort(nums, tmp, mid + 1, right);
            merge(nums, tmp, left, mid, right);
        }

        private void merge(int[] nums, int[] tmp, int left, int mid, int right) {
            for (int k = left; k <= right; k++) {
                tmp[k] = nums[k];
            }

            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1) {
                    nums[k] = tmp[i++];
                } else if (tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i++];
                    // tmp[i] <= tmp[j] -> tmp[i] <= tmp[j...right]
                    sum += (right - j + 1) * nums[k];
                } else {
                    nums[k] = tmp[j++];
                }
            }
        }

    }



}
