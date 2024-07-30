package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1598minOperations {

    public static class Solution1 {

        /**
         Stack + Simulation
         */
        public int minOperations(String[] logs) {
            int n = logs.length;
            Deque<String> s = new ArrayDeque<>();
            for (String log : logs) {
                log = log.substring(0, log.length() - 1);
                if ("..".equals(log)) {
                    if (!s.isEmpty()) {
                        s.pop();
                    }
                } else if (".".equals(log)) {
                    // nothing to do here
                } else {
                    s.push(log);
                }
            }
            return s.size();
        }

    }

}
