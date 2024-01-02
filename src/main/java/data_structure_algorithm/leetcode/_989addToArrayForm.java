package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _989addToArrayForm {

    public static class Solution1 {

        /**
         模拟（注意进位）
         时间复杂度：O(max(num.length, logk))
         空间复杂度：O(1)
         */
        public List<Integer> addToArrayForm(int[] num, int k) {
            int i = num.length - 1;
            int add = k;
            List<Integer> res = new ArrayList<>();
            while (i >= 0 || add != 0) {
                int n = i >= 0 ? num[i] : 0;
                int val = n + add;
                add = val / 10;
                val %= 10;
                res.add(val);
                i--;
            }
            Collections.reverse(res);
            return res;
        }

    }

}
