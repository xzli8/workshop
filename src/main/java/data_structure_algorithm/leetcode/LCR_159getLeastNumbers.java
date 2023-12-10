package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LCR_159getLeastNumbers {

    public static class Solution1 {

        /**
         排序
         时间复杂度：O(NlogN)
         空间复杂度：O(logN)
         */
         public int[] getLeastNumbers(int[] arr, int k) {
             Arrays.sort(arr);
             int[] res = new int[k];
             for (int i = 0; i < k; i++) {
                 res[i] = arr[i];
             }
             return res;
         }

    }


    public static class Solution2 {

        /**
         堆
         时间复杂度：O(NlogK)
         空间复杂度：O(K)
         */
         public int[] getLeastNumbers(int[] arr, int k) {
             PriorityQueue<Integer> pq = new PriorityQueue<>((a1, a2) -> a2 - a1);

             for (int a : arr) {
                 pq.offer(a);
                 if (pq.size() > k) {
                     pq.poll();
                 }
             }

             int[] res = new int[k];
             int i = 0;
             while (!pq.isEmpty()) {
                 res[i++] = pq.poll();
             }
             return res;
         }

    }



    public static class Solution3 {

        /**
         基于快速排序思想的数组划分(题目只要求返回最小的k个数，对这k个数的顺序并没有要求，所以可以使用快速排序的分区函数)
         时间复杂度：O(N)，通过“剪枝”操作，舍去不必要的递归，来优化时间复杂度
         空间复杂度：O(logN)
         */
        public int[] getLeastNumbers(int[] arr, int k) {
            if (arr.length == 0 || k == 0) {
                return new int[0];
            }
            return quickSearch(arr, 0, arr.length-1, k-1);  // k-1：第k个数的下标是k-1
        }

        private int[] quickSearch(int[] nums, int low, int high, int k) {
            int i = partition(nums, low, high);
            if (i == k) {
                return Arrays.copyOf(nums, i+1);
            }
            return i > k ? quickSearch(nums, low, i-1, k) : quickSearch(nums, i+1, high, k);
        }

        private int partition(int[] nums, int low, int high) {
            // 选取分区点
            int pivot = nums[high];
            // 将区间内元素进行分区
            int i = low;
            for (int j = low; j < high; j++) {
                if (nums[j] < pivot) {
                    // j == i时也可以交换，但没必要
                    if (j != i) {
                        swap(nums, i, j);
                    }
                    i++;
                }
            }
            // 分区点归位
            swap(nums, i, high);
            // 返回分区点下标
            return i;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }


    }



}
