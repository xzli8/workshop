package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _556nextGreaterElement {

    public static class Solution1 {

        /**
         与“31-下一个排列”相同
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int nextGreaterElement(int n) {
            char[] cs = String.valueOf(n).toCharArray();
            int len = cs.length;
            for (int i = len - 2; i >= 0; i--) {
                if (cs[i] < cs[i + 1]) {
                    for (int j = len - 1; j > i; j--) {
                        if (cs[j] > cs[i]) {
                            swap(cs, i, j);
                            break;
                        }
                    }
                    reverse(cs, i + 1, len - 1);
                    long res = Long.parseLong(String.valueOf(cs));
                    return res > Integer.MAX_VALUE ? -1 : (int) res;
                }
            }
            return -1;
        }

        private void swap(char[] cs, int i, int j) {
            char tmp = cs[i];
            cs[i] = cs[j];
            cs[j] = tmp;
        }

        private void reverse(char[] cs, int i, int j) {
            while (i < j) {
                swap(cs, i++, j--);
            }
        }

    }

}
