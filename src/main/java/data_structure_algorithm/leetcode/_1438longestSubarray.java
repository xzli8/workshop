package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

public class _1438longestSubarray {

    public static class Solution1 {

        /**
         滑动窗口 + TreeMap（考虑会有重复元素，所以用TreeMap而不是TreeSet）
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public int longestSubarray(int[] nums, int limit) {
            int n = nums.length, maxLen = 0, left = 0, right = 0;
            TreeMap<Integer, Integer> map = new TreeMap<>();
            while (right < n) {
                // 更新
                int nr = nums[right++];
                map.put(nr, map.getOrDefault(nr, 0) + 1);

                // 不满足条件时移动左指针，直至重新满足条件为止
                while (map.lastKey() - map.firstKey() > limit) {
                    int nl = nums[left++];
                    map.put(nl, map.get(nl) - 1);
                    if (map.get(nl) == 0) {
                        map.remove(nl);
                    }
                }

                // 计算结果并移动右指针
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

    }



    public static class Solution2 {

        /**
         滑动窗口 + 单调队列
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int longestSubarray(int[] nums, int limit) {
            int left = 0, right = 0, maxLen = 0;
            Deque<Integer> minQueue = new LinkedList<>();
            Deque<Integer> maxQueue = new LinkedList<>();
            while (right < nums.length) {
                int nr = nums[right++];
                while (!minQueue.isEmpty() && minQueue.peekLast() > nr) {
                    minQueue.pollLast();
                }
                minQueue.offerLast(nr);
                while (!maxQueue.isEmpty() && maxQueue.peekLast() < nr) {
                    maxQueue.pollLast();
                }
                maxQueue.offerLast(nr);

                while (!minQueue.isEmpty() && !maxQueue.isEmpty()
                        && maxQueue.peekFirst() - minQueue.peekFirst() > limit) {
                    int nl = nums[left++];
                    if (minQueue.peekFirst() == nl) {
                        minQueue.pollFirst();
                    }
                    if (maxQueue.peekFirst() == nl) {
                        maxQueue.pollFirst();
                    }
                }
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

    }

}
