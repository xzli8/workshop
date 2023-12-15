package data_structure_algorithm.leetcode;

public class _345reverseVowels {

    public static class Solution1 {

        /**
         左右双指针：左指针从头开始遍历找元音，右指针从尾开始遍历找元音，找到后交换
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public String reverseVowels(String s) {
            // 将字符串转换为字符数组，便于交换
            char[] cs = s.toCharArray();

            // 左右指针
            int left = 0, right = s.length() - 1;
            while (left < right) {
                // 需要判断left < right
                while (!isVowels(cs[left]) && left < right) {
                    left++;
                }
                while (!isVowels(cs[right]) && left < right) {
                    right--;
                }
                swap(cs, left++, right--);
            }

            // 返回cs数组构成的字符串
            return String.valueOf(cs);
        }

        private boolean isVowels(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                    c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
        }

        private void swap(char[] cs, int left, int right) {
            char c = cs[left];
            cs[left] = cs[right];
            cs[right] = c;
        }

    }

}
