package data_structure_algorithm.leetcode;

public class _274hIndex {

    public static class Solution1 {

        /**
         二分查找：值域二分，找最后一个满足条件的值
         时间复杂度：O(NlogN)
         空间复杂度：O(1)
         */
         public int hIndex(int[] citations) {
             int n = citations.length, left = 0, right = n;
             while (left <= right) {
                 int mid = left + ((right - left) >> 1);
                 if (count(citations, mid) >= mid) {
                     if (mid == n || count(citations, mid + 1) < mid + 1) return mid;
                     else left = mid + 1;
                 } else {
                     right = mid - 1;
                 }
             }
             return -1;
         }

         private int count(int[] citations, int mid) {
             int count = 0;
             for (int citation : citations) {
                 if (citation >= mid) count++;
             }
             return count;
         }

    }



    public static class Solution2 {

        /**
         计数
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int hIndex(int[] citations) {
            // 因为h最大为n，所以引用次数超过n的文章，等价于n
            int n = citations.length;
            int[] counts = new int[n + 1];
            for (int citation : citations) counts[Math.min(citation, n)]++;
            for (int i = n, sum = 0; i >= 0; i--) {
                sum += counts[i];
                if (sum >= i) return i;
            }
            return -1;
        }

    }

}
