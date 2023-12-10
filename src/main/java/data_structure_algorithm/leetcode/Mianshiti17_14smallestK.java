package data_structure_algorithm.leetcode;

import java.util.PriorityQueue;
import java.util.Random;

public class Mianshiti17_14smallestK {

    public static class Solution1 {

        /**
         优先队列
         时间复杂度：O(NlogK)
         空间复杂度：O(K)
         */
         public int[] smallestK(int[] arr, int k) {
             // 用大顶堆，来筛选出最小的k个数
             PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
             for (int num : arr) {
                 pq.offer(num);
                 if (pq.size() > k) {
                     pq.poll();
                 }
             }

             // 将堆中元素poll到数组中返回
             int[] res = new int[k];
             for (int i = 0; i < k; i++) {
                 res[i] = pq.poll();
             }
             return res;
         }

    }



    public static class Solution2 {


        /**
         用快速排序中的partion思想
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] smallestK(int[] arr, int k) {
            if (k == 0) return new int[0];

            int left = 0, right = arr.length - 1;
            while (true) {
                int index = randomPartition(arr, left, right);
                if (index == k) {
                    break;
                } else if (index < k) {
                    left = index + 1;
                } else {
                    right = index - 1;
                }
            }

            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = arr[i];
            }
            return res;
        }

        private int randomPartition(int[] nums, int low, int high) {
            int i = new Random().nextInt(high - low + 1) + low;
            swap(nums, i, high);
            return partition(nums, low, high);
        }

        private int partition(int[] nums, int low, int high) {
            int pivot = nums[high];
            int i = low;
            for (int j = low; j < high; j++) {
                if (nums[j] < pivot) {
                    if (j != i) swap(nums, i, j);
                    i++;
                }
            }
            swap(nums, i, high);
            return i;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }



    }



}
