package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class _2120executeInstructions {

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(Arrays.toString(executeInstructions(3, new int[]{0, 1}, "RRDDLU")));
        }

        /**
         String + Simulation
         */
        public int[] executeInstructions(int n, int[] startPos, String s) {
            int m = s.length();
            int[] res = new int[m];
            for (int i = 0; i < m; i++) {
                res[i] = executeFromI(n, startPos, s, i);
            }
            return res;
        }

        private int executeFromI(int n, int[] startPos, String s, int i) {
            int[] curPos = startPos;
            int j = i;
            while (j < s.length()) {
                int[] nextPos = move(curPos, s.charAt(j));
                if (!inside(nextPos, n)) {
                    break;
                }
                curPos = nextPos;
                j++;
            }
            return j - i;
        }

        private int[] move(int[] curPos, char c) {
            int dx = 0, dy = 0;
            if (c == 'U') dx -= 1;
            if (c == 'D') dx += 1;
            if (c == 'L') dy -= 1;
            if (c == 'R') dy += 1;
            return new int[] {curPos[0] + dx, curPos[1] + dy};
        }

        private boolean inside(int[] pos, int n) {
            return 0 <= pos[0] && pos[0] < n && 0 <= pos[1] && pos[1] < n;
        }

    }

}
