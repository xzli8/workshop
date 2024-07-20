package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _2244minimumRounds {

    public static class Solution1 {

        /**
         Greedy
         */
        public int minimumRounds(int[] tasks) {
            // count all tasks
            Map<Integer, Integer> task2Count = new HashMap<>();
            for (int task : tasks) {
                task2Count.put(task, task2Count.getOrDefault(task, 0) + 1);
            }

            // count rounds
            int rounds = 0;
            for (int count : task2Count.values()) {
                if (count == 1) return -1;
                rounds += count / 3;
                if (count % 3 != 0) rounds += 1;
            }
            return rounds;
        }

    }

}
