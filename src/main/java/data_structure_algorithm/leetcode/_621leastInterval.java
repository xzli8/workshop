package data_structure_algorithm.leetcode;

public class _621leastInterval {

    public static class Solution1 {

        /**
         桶思想
         ref:https://leetcode.cn/problems/task-scheduler/solutions/196302/tong-zi-by-popopop/
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int leastInterval(char[] tasks, int n) {
            // 统计每个任务的次数，并计算出现次数最多的任务
            int[] task2Count = new int[26];
            int max = 0;
            for (char task : tasks) {
                int idx = task - 'A';
                task2Count[idx]++;
                max = Math.max(max, task2Count[idx]);
            }

            // 计算出现次数最多的任务数(出现次数最多的任务可能有多个)，用于标记最后一个桶的任务数
            int maxCount = 0;
            for (int i = 0; i < 26; i++) {
                if (task2Count[i] == max) maxCount++;
            }

            // 计算最终结果
            return Math.max(tasks.length, (max - 1) * (n + 1) + maxCount);
        }

    }

}
