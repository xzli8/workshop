package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _401readBinaryWatch {

    public static class Solution1 {

        /**
         枚举
         */
        public List<String> readBinaryWatch(int turnedOn) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 60; j++) {
                    if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                        res.add(String.format("%d:%02d", i, j));
                    }
                }
            }
            return res;
        }

    }

}
