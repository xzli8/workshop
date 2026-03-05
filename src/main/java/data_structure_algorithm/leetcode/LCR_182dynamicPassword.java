package data_structure_algorithm.leetcode;

public class LCR_182dynamicPassword {

    public static class Solution1 {

        /**
         切片+拼接: O(N), O(N)
         */
        public String dynamicPassword(String password, int target) {
            return password.substring(target) + password.substring(0, target);
        }

    }

}
