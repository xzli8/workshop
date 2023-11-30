package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _870advantageCount {

    public static class Solution1 {

        /**
         贪心：将nums1, nums2都排序，然后从最大的开始，如果nums1的最大值 > nums2的最大值，
         拿nums1的最大值比；否则，拿nums1的最小值比

         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public int[] advantageCount(int[] nums1, int[] nums2) {
            // 用优先队列存储nums2的元素及其下标，大顶堆
            int n = nums1.length;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
            for (int i = 0; i < n; i++) {
                pq.offer(new int[] {i, nums2[i]});
            }

            // 将nums1排序(默认从小到大排序，最后一个元素最大)
            Arrays.sort(nums1);

            // 双指针遍历
            int[] res = new int[n];
            int left = 0, right = n - 1;
            while (!pq.isEmpty()) {
                int[] item = pq.poll();
                int idx = item[0], num = item[1];
                if (num < nums1[right]) {
                    res[idx] = nums1[right--];
                } else {
                    res[idx] = nums1[left++];
                }
            }
            return res;
        }

    }



    public static class Solution2 {

        /**
         贪心：思路和上面一样，实现上用数组代替优先队列
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] advantageCount(int[] nums1, int[] nums2) {
            // 构建nums2的"下标-元素"数组，然后将下标和元素捆绑在一起排序
            int n = nums1.length;
            int[][] pairs = new int[n][2];
            for (int i = 0; i < n; i++) {
                pairs[i][0] = i;
                pairs[i][1] = nums2[i];
            }

            // 排序
            Arrays.sort(pairs, (pair1, pair2) -> pair2[1] - pair1[1]);
            Arrays.sort(nums1);

            // 双指针遍历
            int[] res = new int[n];
            int left = 0, right = n - 1;
            for (int[] pair : pairs) {
                int idx = pair[0], num = pair[1];
                if (num < nums1[right]) {
                    res[idx] = nums1[right--];
                } else {
                    res[idx] = nums1[left++];
                }
            }
            return res;
        }

    }



}
