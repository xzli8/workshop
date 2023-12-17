package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _350intersect {

    /**
     进阶问题：
     1.如果给定的数组已经排好序呢？你将如何优化你的算法？
     用双指针可以达到O(M + N)的时间复杂度和O(1)的空间复杂度。
     2.如果nums1的大小比nums2小，哪种方法更优？
     将较小的数组哈希计数，随后在另一个数组中根据哈希来寻找。
     3.如果nums2的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     (1)使用外部排序(归并排序)方式将nums2排序好，然后每次读取一部分。
     (2)每次读取nums2的一部分到内存中，然后用哈希表处理。
     */


    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(M + N)
         空间复杂度：O(1)，数据范围有限
         */
         public int[] intersect(int[] nums1, int[] nums2) {
             // 分别统计nums1, nums2中元素出现的次数
             int[] counts1 = numsCount(nums1);
             int[] counts2 = numsCount(nums2);

             List<Integer> res = new ArrayList<>();
             for (int i = 0; i <= 1000; i++) {
                 int count = Math.min(counts1[i], counts2[i]);
                 while (count-- > 0) {
                     res.add(i);
                 }
             }

             // 结果转换成int[]返回
             return res.stream().mapToInt(Integer::intValue).toArray();
         }

         private int[] numsCount(int[] nums) {
             // 因为nums元素范围有限(0-1000)，所以可以用哈希数组代替哈希map
             int[] counts = new int[1001];
             for (int num : nums) {
                 counts[num]++;
             }
             return counts;
         }

    }


    public static class Solution2 {

        /**
         哈希表(改进)
         做法：遍历较短数组并在哈希表中记录每个数字及其出现的次数，然后遍历较长数组，如果在哈希表中存在这个数字，
         就将该数字添加到答案，并且将哈希表中该数字出现次数减一。
         */
        public int[] intersect(int[] nums1, int[] nums2) {
            // 选取较短数组
            if (nums1.length > nums2.length) {
                return intersect(nums2, nums1);
            }

            // 遍历较短数组，统计每个字符出现的次数
            int[] counts = new int[1001];
            for (int num : nums1) {
                counts[num]++;
            }

            // 遍历较长数组，找交集
            List<Integer> res = new ArrayList<>();
            for (int num : nums2) {
                if (counts[num]-- > 0) {
                    res.add(num);
                }
            }
            return res.stream().mapToInt(Integer::intValue).toArray();
        }

    }


    public static class Solution3 {

        /**
         排序 + 双指针
         时间复杂度：O(MlogM + NlogN)，排序
         空间复杂度：O(logM + logN)，排序
         */
         public int[] intersect(int[] nums1, int[] nums2) {
             // 排序
             Arrays.sort(nums1);
             Arrays.sort(nums2);

             // 双指针
             List<Integer> res = new ArrayList<>();
             int idx1 = 0, idx2 = 0;
             while (idx1 < nums1.length && idx2 < nums2.length) {
                 int num1 = nums1[idx1], num2 = nums2[idx2];
                 if (num1 == num2) {
                     res.add(num1);
                     idx1++;
                     idx2++;
                 } else if (num1 < num2) {
                     idx1++;
                 } else {
                     idx2++;
                 }
             }

             // 转换成int[]返回
             return res.stream().mapToInt(Integer::intValue).toArray();
         }

    }

}
