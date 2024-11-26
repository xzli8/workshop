package data_structure_algorithm.leetcode;

public class _3206numberOfAlternatingGroups {

    public static class Solution1 {

        /**
         SlidingWindow
         T: O(N)
         S: O(1)
         */
        public int numberOfAlternatingGroups(int[] colors) {
            int n = colors.length, res = 0;
            for (int start = 0; start < n; start++) {
                if (isAlternatingGroup(colors, start)) {
                    res++;
                }
            }
            return res;
        }

        private boolean isAlternatingGroup(int[] colors, int start) {
            int n = colors.length;
            if (colors[start] == 1) {
                return colors[(start + 1) % n] == 0 && colors[(start + 2) % n] == 1;
            } else {
                return colors[(start + 1) % n] == 1 && colors[(start + 2) % n] == 0;
            }
        }

    }

}
