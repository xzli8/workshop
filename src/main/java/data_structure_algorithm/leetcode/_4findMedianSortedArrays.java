package data_structure_algorithm.leetcode;

public class _4findMedianSortedArrays {

    /**
     * 参考题解：
     *      https://leetcode.cn/problems/median-of-two-sorted-arrays/solutions/8999/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
     *      https://www.cnblogs.com/grandyang/p/4465932.html
     */

    public static class Solution1 {

        /**
         合并两有序数组（归并排序部分逻辑），然后寻找数组中位数
         时间复杂度：O(m+n)
         空间复杂度：O(m+n)
         */
         public double findMedianSortedArrays(int[] nums1, int[] nums2) {
             // 合并两有序数组
             int n1 = nums1.length;
             int n2 = nums2.length;
             int n = n1 + n2;
             int[] nums = new int[n];
             int i = 0, j = 0,  k = 0;
             while (i < n1 && j < n2) {
                 if (nums1[i] < nums2[j]) nums[k++] = nums1[i++];
                 else nums[k++] = nums2[j++];
             }
             while (i < n1) nums[k++] = nums1[i++];
             while (j < n2) nums[k++] = nums2[j++];

             // 找中位数
             if (n % 2 == 0) return (nums[n / 2] + nums[n / 2 - 1]) / 2.0;
             else return nums[n / 2];
         }

    }



    public static class Solution2 {

        /**
         二分：递归
         时间复杂度：O(log(M + N))
         空间复杂度：O(1)，虽然有递归，但是尾递归，不会一直压栈
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n1 = nums1.length, n2 = nums2.length, n = n1 + n2;
            if ((n & 1) == 0) {
                return (getKth(nums1, 0, n1 - 1, nums2, 0, n2 - 1, n / 2) + getKth(nums1, 0, n1 - 1, nums2, 0, n2 - 1, n / 2 + 1)) * 0.5;
            } else {
                return getKth(nums1, 0, n1 - 1, nums2, 0, n2 - 1, n / 2 + 1);
            }
        }

        private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            int len1 = end1 - start1 + 1, len2 = end2 - start2 + 1;
            if (len1 == 0) return nums2[start2 + k - 1];
            if (len2 == 0) return nums1[start1 + k - 1];
            if (k == 1) return Math.min(nums1[start1], nums2[start2]);

            int i = start1 + Math.min(len1, k / 2) - 1, j = start2 + Math.min(len2, k / 2) - 1;
            if (nums1[i] > nums2[j]) {
                return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
            } else {
                return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            }
        }

    }



    public static class Solution3 {

        /**
         二分：迭代
         时间复杂度：O(log(M + N))
         空间复杂度：O(1)
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            if (m > n) return findMedianSortedArrays(nums2, nums1); // 保证 m <= n

            // 二分查找i(判断i和j的边界时，为什么用m, n而不是m-1, n-1)
            int iMin = 0, iMax = m;
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = (m + n + 1) / 2 - i;
                if (i != m && j != 0 && nums2[j-1] > nums1[i]) iMin = i + 1;
                else if (i != 0 && j != n && nums1[i-1] > nums2[j]) iMax = i - 1;
                else {  // 达到要求，需要考虑边界条件
                    int maxLeft = 0;
                    if (i == 0) maxLeft = nums2[j-1];
                    else if (j == 0) maxLeft = nums1[i-1];
                    else maxLeft = Math.max(nums1[i-1], nums2[j-1]);
                    if ((m + n) % 2 != 0) return maxLeft; // 奇数时不需要考虑右半部分

                    int minRight = 0;
                    if (i == m) minRight = nums2[j];
                    else if (j == n) minRight = nums1[i];
                    else minRight = Math.min(nums1[i], nums2[j]);
                    return (maxLeft + minRight) / 2.0;  // 偶数时返回
                }
            }
            return 0.0;
        }

    }

}
