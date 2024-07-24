package data_structure_algorithm.leetcode;

public class _2833furthestDistanceFromOrigin {

    public static class Solution1 {

        /**
         Greedy
         */
        public int furthestDistanceFromOrigin(String moves) {
            int leftCount = 0, rightCount = 0, spaceCount = 0;
            for (char move : moves.toCharArray()) {
                if (move == 'L') leftCount++;
                if (move == 'R') rightCount++;
                if (move == '_') spaceCount++;
            }
            return Math.abs(leftCount - rightCount) + spaceCount;
        }

    }

}
