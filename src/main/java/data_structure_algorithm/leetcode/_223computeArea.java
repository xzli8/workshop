package data_structure_algorithm.leetcode;

public class _223computeArea {

    public static class Solution1 {

        /**
         几何
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
        public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            int s1 = (ax2 - ax1) * (ay2 - ay1), s2 = (bx2 - bx1) * (by2 - by1);
            int x = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));
            int y = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));
            return s1 + s2 - x * y;
        }

    }

}
