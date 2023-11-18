package dsa.leetcode;

import java.util.Arrays;

public class _300lengthOfLIS {

    /**
     动态规划
     定义状态：dp[i]表示以nums[i]结尾的最长递增子序列
     状态转移：对于nums[i]，如果nums[i]大于nums[j](0 <= j < i)，那么nums[i] = max(dp[i], dp[j]+1)
     初始状态：dp[i]=1

     时间复杂度：O(N^2)
     空间复杂度：O(N)
     */
    public int lengthOfLIS1(int[] nums) {
        // 定义并初始化状态
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // 状态转移
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    /**
     动态规划 + 贪心 + 二分：
     参考题解：题解：https://leetcode.cn/problems/longest-increasing-subsequence/solutions/7196/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/

     状态设计思想：依然着眼于某个上升子序列的 结尾的元素，如果 已经得到的上升子序列的结尾的数越小，
     那么遍历的时候后面接上一个数，会有更大的可能构成一个长度更长的上升子序列。既然结尾越小越好，
     我们可以记录 在长度固定的情况下，结尾最小的那个元素的数值，这样定义以后容易得到「状态转移方程」。

     定义状态：tail[i]表示长度为i+1的LIS的末尾元素(tail单调递增)

     时间复杂度：O(NlogN)
     空间复杂度：O(N)
     */
    public int lengthOfLIS2(int[] nums) {

        // 定义状态
        int n = nums.length;
        int[] tail = new int[n];

        // 初始化状态
        tail[0] = nums[0];
        int end = 0;    // end表示tail的最后一个有效数字的索引

        // 状态转移
        for (int i = 1; i < n; i++) {
            // 当前元素比tail末尾元素大，直接加入tail
            if (nums[i] > tail[end]) {
                tail[++end] = nums[i];
            }
            // 当前元素比tail末尾元素小，因为tail是单调递增的，所以在tail中用二分查找，
            // 找到第一个大于等于nums[i]的元素，尝试让其更小
            else {
                int left = 0, right = end;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (tail[mid] >= nums[i]) {
                        if (mid == 0 || tail[mid - 1] < nums[i]) {
                            tail[mid] = nums[i];
                            break;
                        } else {
                            right = mid - 1;
                        }
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }

        // 返回LIS的长度
        return end + 1;
    }

}
