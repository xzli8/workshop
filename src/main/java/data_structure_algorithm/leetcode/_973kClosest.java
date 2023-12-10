package data_structure_algorithm.leetcode;

import java.util.*;

public class _973kClosest {

    public static class Solution1 {

        /**
         排序：计算出每个点到原点距离的平方后，再将其排序
         时间复杂度：O(NlogN)
         空间复杂度：O(logN)
         */
         public int[][] kClosest(int[][] points, int k) {
             // 按照到原点距离的平方，从小到大排序
             Arrays.sort(points, (p1, p2) -> (p1[0] * p1[0] + p1[1] * p1[1]) - (p2[0] * p2[0] + p2[1] * p2[1]));
             // 返回前k个点
             return Arrays.copyOfRange(points, 0, k);
         }

    }



    public static class Solution2 {

        /**
         堆：找最小的K个数，用大顶堆
         时间复杂度：O(NlogK)
         空间复杂度：O(N)
         */
         public int[][] kClosest(int[][] points, int k) {
             // 首先计算每个点到原点距离的平方
             int n = points.length;
             int[] dist = new int[n];
             for (int i = 0; i < n; i++) {
                 int x = points[i][0], y = points[i][1];
                 dist[i] = x * x + y * y;
             }

             // 用堆找最小的K个数
             PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a2[1] - a1[1]);
             for (int i = 0; i < n; i++) {
                 pq.offer(new int[] {i, dist[i]});
                 if (pq.size() > k) {
                     pq.poll();
                 }
             }

             // 返回结果
             List<int[]> res = new ArrayList<>(k);
             while (!pq.isEmpty()) {
                 int i = pq.poll()[0];
                 res.add(points[i]);
             }
             return res.toArray(new int[k][0]);
         }

    }



    public static class Solution3 {

        /**
         分治：利用快速排序中partition方法
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[][] kClosest(int[][] points, int k) {
            // 计算每个点到原点距离的平方，并且记录其与下标的对应关系
            int n = points.length;
            int[][] idx2Dist = new int[n][2];
            for (int i = 0; i < n; i++) {
                idx2Dist[i][0] = i;
                idx2Dist[i][1] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            }

            // 寻找分区点
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = partition(idx2Dist, left, right);
                if (mid == k) {
                    break;
                } else if (mid < k) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // 返回
            int[][] res = new int[k][2];
            for (int i = 0; i < k; i++) {
                res[i][0] = points[idx2Dist[i][0]][0];
                res[i][1] = points[idx2Dist[i][0]][1];
            }
            return res;
        }

        private final Random random = new Random();

        private int partition(int[][] idx2Dist, int left, int right) {
            // 随机选取主元
            int ranIdx = random.nextInt(right - left + 1) + left;
            swap(idx2Dist, ranIdx, right);

            // 遍历
            int pivot = idx2Dist[right][1];
            int i = left;
            for (int j = left; j <= right; j++) {
                if (idx2Dist[j][1] < pivot) {
                    if (i != j) swap(idx2Dist, i, j);
                    i++;
                }
            }
            swap(idx2Dist, i, right);
            return i;
        }

        private void swap(int[][] idx2Dist, int i, int j) {
            int[] tmp = idx2Dist[i];
            idx2Dist[i] = idx2Dist[j];
            idx2Dist[j] = tmp;
        }

    }



}
