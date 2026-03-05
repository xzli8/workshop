package data_structure_algorithm.leetcode;

import java.util.PriorityQueue;
import java.util.Random;

public class _215findKthLargest {

    public static class Solution0 {

        /**
         分治(快速排序中的partition): O(N), 迭代O(1)/递归O(logN)
         ref: https://leetcode.cn/problems/kth-largest-element-in-an-array/solutions/2361969/215-shu-zu-zhong-de-di-k-ge-zui-da-yuan-d786p/
         时间复杂度分析：每轮划分后，向下迭代/递归子数组的平均长度为N/2(要求数据随机打乱)，所以总的时间复杂度为N + N/2 + N/4 + ... + N/N = 2N - 1(等比数列求和)
         */
        public int findKthLargest(int[] nums, int k) {
            int n = nums.length, target = n - k, left = 0, right = n - 1;
            while (left <= right) {
                int mid = partition(nums, left, right);
                if (mid == target) return nums[mid];
                else if (mid < target) left = mid + 1;
                else right = mid - 1;
            }
            return -1;
        }

        private Random random = new Random();
        private int partition(int[] nums, int left, int right) {
            int idx = random.nextInt(right - left + 1) + left;
            swap(nums, idx, right);

            // 这样写会超时(之前可以)
            // int i = left;
            // for (int j = left; j < right; j++) {
            //     if (nums[j] <= nums[right]) {
            //         if (i != j) swap(nums, i, j);
            //         i++;
            //     }
            // }
            // swap(nums, i, right);
            // return i;

            // 以nums[left]为主元
            // int le = left + 1, ge = right;
            // while (true) {
            //     while (le <= ge && nums[le] < nums[left]) le++;
            //     while (le <= ge && nums[ge] > nums[left]) ge--;
            //     if (le >= ge) break;
            //     swap(nums, le++, ge--);
            // }
            // swap(nums, ge, left);
            // return ge;

            // 以nums[right]为主元
            int le = left, ge = right - 1;
            while (true) {
                while (le <= ge && nums[le] < nums[right]) le++;
                while (le <= ge && nums[ge] > nums[right]) ge--;
                if (le >= ge) break;
                swap(nums, le++, ge--);
            }
            swap(nums, le, right);
            return le;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }



    public static class Solution1 {

        /**
         1.优先队列（第K个最大元素 -> 小顶堆）
         时间复杂度：O(NlogK) -> O(NlogN)
         空间复杂度：O(K) -> O(N)
         */
         public int findKthLargest(int[] nums, int k) {
             PriorityQueue<Integer> pq = new PriorityQueue<>();
             for (int i = 0; i < k; i++) {
                 pq.offer(nums[i]);
             }
             for (int i = k; i < nums.length; i++) {
                 int top = pq.peek();
                 if (nums[i] > top) {
                     pq.poll();
                     pq.offer(nums[i]);
                 }
             }
             return pq.peek();
         }

    }



    public static class Solution2 {

        /**
         快排序思想
         时间复杂度：O(N)，注意时间复杂度是怎么求出来的
         空间复杂度：O(1)
         */
        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;
            int target = n - k;
            int left = 0, right = n - 1;
            while (true) {
                int index = randomPartition(nums, left, right);
                if (index == target) return nums[index];
                else if (index < target) {
                    left = index + 1;
                } else {
                    right = index - 1;
                }
            }
        }

        private int randomPartition(int[] nums, int low, int high) {
            int i = new Random().nextInt(high-low+1)+low;
            swap(nums, i, high);
            return partition(nums, low, high);
        }

        private int partition(int[] nums, int low, int high) {
            int pivot = nums[high];
            int i = low;
            for (int j = low; j < high; j++) {
                if (nums[j] <= pivot) {
                    // swap(nums, i++, j);
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
