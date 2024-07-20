package data_structure_algorithm.leetcode;

import java.util.*;

public class _239maxSlidingWindow {

    public static class Solution0 {

        /**
         单调队列
         时间复杂度：O(N)
         空间复杂度：O(K)
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] res = new int[n - k + 1];
            MaxQueue q = new MaxQueue();
            for (int i = 0; i < k; i++) q.offer(nums[i]);
            for (int i = k; i < n; i++) {
                res[i - k] = q.max();
                q.poll();
                q.offer(nums[i]);
            }
            res[n - k] = q.max();
            return res;
        }

        class MaxQueue {
            private Queue<Integer> main = new ArrayDeque<>();

            // 辅助队列，单调递减，队头元素一直是max
            private Deque<Integer> help = new ArrayDeque<>();

            public void offer(int val) {
                main.offer(val);
                while (!help.isEmpty() && help.peekLast() < val) help.pollLast();
                help.offerLast(val);
            }

            public int poll() {
                int val = main.poll();
                if (help.peekFirst() == val) help.pollFirst();
                return val;
            }

            public int max() {
                return help.peek();
            }

        }

    }


    public static class Solution1 {

        /**
         TreeMap：因为数字可能重复，所以用map不用set
         时间复杂度：O(NlogK)
         空间复杂度：O(K)
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            // 初始化
            int n = nums.length;
            int[] res = new int[n - k + 1];
            TreeMap<Integer, Integer> num2Count = new TreeMap<>();

            // 处理前k个数
            for (int i = 0; i < k; i++) {
                num2Count.put(nums[i], num2Count.getOrDefault(nums[i], 0) + 1);
            }
            res[0] = num2Count.lastKey();

            // 处理后面的数
            for (int i = k; i < n; i++) {
                int nl = nums[i - k];
                num2Count.put(nl, num2Count.get(nl) - 1);
                if (num2Count.get(nl) == 0)  {
                    num2Count.remove(nl);
                }

                int nr = nums[i];
                num2Count.put(nr, num2Count.getOrDefault(nr, 0) + 1);
                res[i - k + 1] = num2Count.lastKey();
            }
            return res;
        }

    }

    public static class Solution2 {

        /**
         单调队列
         时间复杂度：O(N)
         空间复杂度：O(K)
         */
        public int[] maxSlidingWindow(int[] nums, int k) {

            // 初始化
            int n = nums.length;
            int[] res = new int[n - k + 1];
            MaxQueue maxQueue = new MaxQueue();

            // 前k个元素加入单调队列
            for (int i = 0; i < k; i++) {
                maxQueue.offer(nums[i]);
            }

            // 遍历后续元素
            res[0] = maxQueue.max();
            for (int i = k; i < n; i++) {
                maxQueue.poll();
                maxQueue.offer(nums[i]);
                res[i - k + 1] = maxQueue.max();
            }
            return res;
        }

        /**
         最大队列(参考：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/description/)
         */
        public class MaxQueue {

            private Queue<Integer> main = new ArrayDeque<>();

            private Deque<Integer> help = new ArrayDeque<>();

            public void offer(int val) {
                main.offer(val);
                while (!help.isEmpty() && help.peekLast() < val) {
                    help.pollLast();
                }
                help.offerLast(val);
            }

            public int poll() {
                int res = main.poll();
                if (res == help.peekFirst()) {
                    help.pollFirst();
                }
                return res;
            }

            public int max() {
                return help.peek();
            }

        }
    }

}
