package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _66plusOne {

    public static class Solution1 {

        /**
         模拟竖式加法
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] plusOne(int[] digits) {
            List<Integer> tmp = new ArrayList<>();
            int i = digits.length - 1, carry = 0;
            while (i >= 0 || carry != 0) {
                int x = i >= 0 ? digits[i] : 0;
                int y = i == digits.length - 1 ? 1 : 0;
                int sum = x + y + carry;
                tmp.add(sum % 10);
                carry = sum / 10;
                i--;
            }
            Collections.reverse(tmp);

            int[] res = new int[tmp.size()];
            for (int j = 0; j < tmp.size(); j++) {
                res[j] = tmp.get(j);
            }
            return res;
        }

    }

}
