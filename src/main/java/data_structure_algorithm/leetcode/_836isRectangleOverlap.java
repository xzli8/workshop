package data_structure_algorithm.leetcode;

public class _836isRectangleOverlap {

    public static class Solution1 {

        /**
         * 几何：分别判断在x/y方向上是否重叠
         * 时间复杂度：O(1)
         * 空间复杂度：O(1)
         */
        public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
            return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
                    && Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
        }

    }

}
