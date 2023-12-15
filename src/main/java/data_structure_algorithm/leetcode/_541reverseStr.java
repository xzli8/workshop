package data_structure_algorithm.leetcode;

public class _541reverseStr {

    public static class Solution1 {


        /**
         快慢双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public String reverseStr(String s, int k) {

            // 将字符串转换成字符数组，方便交换字符
            char[] cs = s.toCharArray();

            // 快慢指针
            int start = 0, slow = 0, fast = 0, n = s.length();
            while (fast < n) {
                start = slow;
                slow += k - 1;
                fast += 2 * k - 1;

                // 还没遍历到结尾，反转前k个字符
                if (fast < n) {
                    reverseBetween(cs, start, slow);
                }
                // 剩余字符小于2k但大于等于k个，反转前k个字符
                else if (fast >= n && n > slow) {
                    reverseBetween(cs, start, slow);
                }
                // 剩余字符少于k个，全部反转
                else {
                    reverseBetween(cs, start, n - 1);
                }

                // 开始新的一轮，需要首先将快慢指针归位
                fast++;
                slow = fast;
            }

            return String.valueOf(cs);
        }

        // 反转cs[left, right]
        private void reverseBetween(char[] cs, int left, int right) {
            while (left < right) {
                swap(cs, left++, right--);
            }
        }

        // 交换cs[i]和cs[j]
        private void swap(char[] cs, int i, int j) {
            char c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
        }

    }

}
