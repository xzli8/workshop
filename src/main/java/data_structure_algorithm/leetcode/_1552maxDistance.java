package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _1552maxDistance {

    public static class Solution1 {

        /**
         排序 + 二分查找
         分析：两球之间的磁力大小取决于距离，距离越大磁力越大，所以要想最大化最小磁力，就应该最大化最小距离。
         最小距离越大，球越少；最小距离越小，球越多。所以可以采用二分查找确定。
         二分边界：两球之间最小距离为1，最大距离为(pMax - pMin)/(m - 1)。
         (类似：410，1011，这两题是最小化最大值，本题是最大化最小值)
         */
        public int maxDistance(int[] position, int m) {
            // 排序
            Arrays.sort(position);

            // 二分查找，距离的范围是[left, right]
            int n = position.length, left = 1, right = (position[n - 1] - position[0]) / (m - 1);
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                // 球的数量少，说明距离大，需要减小距离
                if (count(position, mid) < m) {
                    right = mid - 1;
                }
                // 球的数量大，说明距离小，需要增大距离
                else {
                    if (mid == right || count(position, mid + 1) < m) {
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return -1;
        }

        private int count(int[] position, int minDist) {
            int n = position.length, count = 1, prev = position[0];
            for (int i = 1; i < n; i++) {
                if (position[i] - prev >= minDist) {
                    count++;
                    prev = position[i];
                }
            }
            return count;
        }

    }

}
