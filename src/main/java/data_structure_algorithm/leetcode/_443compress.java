package data_structure_algorithm.leetcode;

public class _443compress {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int compress(char[] chars) {
            int n = chars.length, i = 0, len = 0;
            while (i < n) {
                char c = chars[i];
                int count = 1;
                int j = i+1;
                while (j < n && chars[j] == c) {
                    count++;
                    j++;
                }

                chars[len++] = c;
                if (count > 1) {
                    char[] nums = String.valueOf(count).toCharArray();
                    for (int k = 0; k < nums.length; k++) {
                        chars[len++] = nums[k];
                    }
                }
                i = j;
            }
            return len;
        }

    }

}
