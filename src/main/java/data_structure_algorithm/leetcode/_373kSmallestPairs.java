package data_structure_algorithm.leetcode;

import java.util.*;

public class _373kSmallestPairs {

    public static class Solution1 {

        /**
         多路归并 + 小顶堆: O(klogk), O(k)
         - 把 nums1[i] 和整个 nums2 配对看成一条有序链：(i,0) <= (i,1) <= (i,2) ...
         - 共 n1 条有序链，问题变成合并 n1 条有序链表取前 k 个（同 23.合并K个升序链表）
         - 初始把每条链的头 (i,0) 入堆（只需前 min(n1,k) 条，因为第 k 条以后的链头不可能进前 k）
         - 每次弹出最小的 (i,j)，把同一条链的下一个 (i,j+1) 入堆
         */
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<List<Integer>> res = new ArrayList<>();
            // 堆元素 [i, j]，按 nums1[i] + nums2[j] 升序
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(p -> nums1[p[0]] + nums2[p[1]]));
            for (int i = 0; i < Math.min(nums1.length, k); i++) {
                pq.offer(new int[]{i, 0});
            }
            while (res.size() < k && !pq.isEmpty()) {
                int[] p = pq.poll();
                int i = p[0], j = p[1];
                res.add(Arrays.asList(nums1[i], nums2[j]));
                if (j + 1 < nums2.length) {
                    pq.offer(new int[]{i, j + 1});
                }
            }
            return res;
        }

    }


    public static class Solution2 {

        /**
         变体（Azure Storage 面经）：只求第 k 小的和
         值域二分: O((N1 + N2)logU), O(1)
         Note:
         - 和 378.有序矩阵中第K小的元素 是同一题：sum[i][j] = nums1[i] + nums2[j] 构成一个行、列都递增的矩阵
         - 在值域 [nums1[0] + nums2[0], nums1[n1-1] + nums2[n2-1]] 上二分，找第一个满足"和 <= mid 的对数 >= k"的 mid
         - 和 Solution1 弹出 k 次的对比：k 很大（接近 N1 * N2）时值域二分更好
         - 用 long：两数之和可能溢出 int，对数最多 N1 * N2 也可能溢出 int
         */
        public long kthSmallestSum(int[] nums1, int[] nums2, int k) {
            int n1 = nums1.length, n2 = nums2.length;
            long left = (long) nums1[0] + nums2[0], right = (long) nums1[n1 - 1] + nums2[n2 - 1];

            // 找第一个满足条件(>=k)的值
            while (left <= right) {
                long mid = left + ((right - left) >> 1);
                if (countLessOrEqual(nums1, nums2, mid) >= k) {
                    if (mid == (long) nums1[0] + nums2[0] || countLessOrEqual(nums1, nums2, mid - 1) < k) {
                        return mid;
                    }
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // 不会走到这里
            return -1;
        }

        /**
         计算和小于等于mid的数对个数(相似题:240，从右上角开始，每次排除一行或者一列；378的countByCol是从左下角按列计数)
         时间复杂度：O(N1 + N2) i只增、j只减，加起来移动次数不超过N1 + N2
         空间复杂度：O(1)
         */
        private long countLessOrEqual(int[] nums1, int[] nums2, long mid) {
            // 和小于等于mid的数对个数
            long count = 0;

            // 从右上角开始(行i对应nums1，列j对应nums2)：i 从 nums1 头部往后，j 从 nums2 尾部往前
            int n1 = nums1.length, n2 = nums2.length, i = 0, j = n2 - 1;
            while (i < n1 && j >= 0) {
                // nums1[i] 和 nums2[0..j] 的和都小于等于mid，共j + 1对
                if ((long) nums1[i] + nums2[j] <= mid) {
                    count += j + 1;
                    i++;
                }
                // 和大于mid，需要让 nums2 的数小一点
                else {
                    j--;
                }
            }
            return count;
        }

    }


    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11}, nums2 = {2, 4, 6};
        System.out.println(new Solution1().kSmallestPairs(nums1, nums2, 3));   // [[1, 2], [1, 4], [1, 6]]
        System.out.println(new Solution2().kthSmallestSum(nums1, nums2, 3));    // 7
        System.out.println(new Solution2().kthSmallestSum(nums1, nums2, 4));    // 9
    }

}
