package data_structure_algorithm.leetcode;

public class _3222losingPlayer {

    public static class Solution1 {

        /**
         Simulation
         TC: O(1)
         SC: O(1)
         */
        public String losingPlayer(int x, int y) {
            int turns = Math.min(x, y / 4);
            return turns % 2 == 0 ? "Bob" : "Alice";
        }

    }

}
