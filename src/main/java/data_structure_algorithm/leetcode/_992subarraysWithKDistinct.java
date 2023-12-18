package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _992subarraysWithKDistinct {

    public static class Solution1 {

        /**
         滑动窗口：恰好由k个不同整数组成的子数组个数 = 最多由k个不同整数组成的子数组个数 - 最多由k-1个不同整数组成的子数组个数
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int subarraysWithKDistinct(int[] nums, int k) {
             return maxSub(nums, k) - maxSub(nums, k-1);
         }

         private int maxSub(int[] nums, int k) {
             int n = nums.length;
             int[] counter = new int[n+1];
             int left = 0, right = 0, res = 0;
             while (right < n) {
                 if (counter[nums[right]] == 0) k--;
                 counter[nums[right]]++;
                 while (k < 0) {
                     counter[nums[left]]--;
                     if (counter[nums[left]] == 0) k++;
                     left++;
                 }
                 res += right - left + 1;
                 right++;
             }
             return res;
         }

        @Test
        public void test() {
            Assert.assertEquals(7, subarraysWithKDistinct(new int[] {1,2,1,2,3}, 2));
            Assert.assertEquals(3, subarraysWithKDistinct(new int[] {1,2,1,3,4}, 3));
        }

    }



    public static class Solution2 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int subarraysWithKDistinct(int[] nums, int k) {
            int n = nums.length;
            int[] counts1 = new int[n + 1], counts2 = new int[n + 1];
            int right = 0, left1 = 0, left2 = 0, total1 = 0, total2 = 0, res = 0;
            while (right < n) {
                // 右指针右移，分别统计左1和左2指针对应窗口内各数字出现次数和不同数字数量
                int nr = nums[right++];
                if (counts1[nr]++ == 0) total1++;
                if (counts2[nr]++ == 0) total2++;

                // 当左1指针对应窗口内不同字符数量大于k时，左1指针右移
                while (total1 > k) {
                    int nl = nums[left1++];
                    if (--counts1[nl] == 0) total1--;
                }

                // 当左2指针对应窗口内不同字符数量大于k-1时，左2指针右移
                while (total2 > k - 1) {
                    int nl = nums[left2++];
                    if (--counts2[nl] == 0) total2--;
                }

                // 对于某个确定的右指针，其好子数组数量等于左2指针-左1指针
                res += left2 - left1;
            }
            return res;
        }

    }

}
