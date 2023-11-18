package dsa.leetcode;

import java.util.Arrays;

public class _354maxEnvelopes {

    /**
     动态规划 + 贪心 + 二分
     思路参考：https://leetcode.cn/problems/longest-increasing-subsequence/的二分解法
     时间复杂度：O(NlogN)
     空间复杂度：O(N)
     */
    public int maxEnvelopes(int[][] envelopes) {

        // 按照宽度从小到大排序(宽度顺序)，宽度相同按照高度从大到小排序(高度逆序)
        Arrays.sort(envelopes, (e1, e2) -> e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0]);

        // 定义状态
        int n = envelopes.length;
        int[] tail = new int[n];

        // 初始化状态
        tail[0] = envelopes[0][1];
        int end = 0;

        // 状态转移
        for (int i = 1; i < n; i++) {
            // 当前元素比tail末尾，直接加入tail
            if (envelopes[i][1] > tail[end]) {
                tail[++end] = envelopes[i][1];
            }
            // 当前元素比tail末尾小，因为tail是单调递增的，所以可以用二分查找在tail中找第一个大于等于
            // 当前元素的tail[mid]，将其用当前元素替换(尝试将其变小)
            else {
                int left = 0, right = end;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (tail[mid] >= envelopes[i][1]) {
                        if (mid == 0 || tail[mid - 1] < envelopes[i][1]) {
                            tail[mid] = envelopes[i][1];
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

        // 返回LIS长度
        return end + 1;
    }

}
