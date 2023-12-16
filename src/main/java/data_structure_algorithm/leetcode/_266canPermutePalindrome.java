package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class _266canPermutePalindrome {

    /**
     *  题目描述：给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
     *
     *  示例 1：
     *  输入: “code”
     *  输出: false
     *
     *  示例 2：
     *  输入: “aab”
     *  输出: true
     *
     *  示例 3：
     *  输入: “carerac”
     *  输出: true
     *
     */

    public static class Solution1 {

        /**
         *  哈希表
         *      思路：对于一个回文串，除中心字符外的所有字符都是成对出现的，换言之，回文串中落单的字符数量只能是0个或者1个。
         *      根据这个结论，就可以对s进行一趟遍历，统计其中落单字符的数量。若不超过1个，那么就返回true，否则就返回false。
         *      做法：用一个hashset存储字母，如果字母在哈希表中存在，就删除这个字母，如果不存在，就加入哈希表中，最后判断哈希表中的数量为1或者0，那么表示能组成回文。
         *
         *      时间复杂度：O(N)
         *      空间复杂度：O(N)
         */
        public boolean canPermutePalindrome(String s) {
            HashSet<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                if (set.contains(c)) {
                    set.remove(c);
                } else {
                    set.add(c);
                }
            }
            return set.size() <= 1;
        }

        @Test
        public void test() {
            Assert.assertFalse(canPermutePalindrome("code"));
            Assert.assertTrue(canPermutePalindrome("aab"));
            Assert.assertTrue(canPermutePalindrome("carerac"));
        }

    }

}
