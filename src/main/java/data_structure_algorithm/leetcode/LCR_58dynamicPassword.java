package data_structure_algorithm.leetcode;

public class LCR_58dynamicPassword {

    public static class Solution1 {

        /**
         三次反转：在字符串是可变类型的语言中，可以实现O(1)的空间复杂度
         做法：先将前k个字符反转，然后将后面剩余字符反转，再整体反转
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public String dynamicPassword(String password, int target) {
            char[] cs = password.toCharArray();
            reverse(cs, 0, target - 1);
            reverse(cs, target, password.length() - 1);
            reverse(cs, 0, password.length() - 1);
            return String.valueOf(cs);
        }

        private void reverse(char[] cs, int left, int right) {
            while (left < right) {
                char tmp = cs[left];
                cs[left] = cs[right];
                cs[right] = tmp;
                left++;
                right--;
            }
        }

    }

}
