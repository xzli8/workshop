package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _989addToArrayForm {

    public static class Solution1 {

        /**
         模拟加法: O(N), O(1)
         */
        public List<Integer> addToArrayForm(int[] num, int k) {
            List<Integer> res = new ArrayList<>();
            int i = num.length - 1;
            while (i >= 0 || k != 0) {
                int n = i >= 0 ? num[i--] : 0;
                int val = n + k;
                res.add(val % 10);
                k = val / 10;
            }
            Collections.reverse(res);
            return res;
        }

    }


}
