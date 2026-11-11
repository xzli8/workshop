package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 163. 缺失的区间（会员题）
 *
 * ref: https://leetcode.cn/problems/missing-ranges/
 *
 * 题目
 * - 输入：有序、不重复的整数数组 nums，所有数都在 [lower, upper] 内
 * - 输出：覆盖 [lower, upper] 内所有缺失数字的最短有序区间列表，每个区间是 [start, end]
 * - 示例：nums = [0, 1, 3, 50, 75]，lower = 0，upper = 99 → [[2,2],[4,49],[51,74],[76,99]]
 *
 * 面试变体（微软面试真题）
 * - 输出拼成字符串：缺一个数写这个数，缺一段写 start-end，逗号分隔
 * - nums = [0, 1, 2, 50, 52, 75]，lower = 0，upper = 99 → "3-49,51,53-74,76-99"
 * - 解法相同，只是把区间格式化成字符串（Solution2）
 *
 * 类似题：228.汇总区间（反过来，把已有的连续数字合并成区间）
 */
public class _163MissingRanges {

    public static class Solution1 {

        /**
         * 一次遍历 + 哨兵
         * - prev 记录上一个出现的数，初始为 lower - 1（假装 lower 前面有一个数）
         * - 依次看每个数 cur，最后再看一个哨兵 upper + 1（假装 upper 后面有一个数）
         * - cur - prev >= 2 说明中间缺了 [prev + 1, cur - 1]
         * - 哨兵让开头和结尾的缺口不用单独处理
         * - lower 是 Integer.MIN_VALUE 或 upper 是 Integer.MAX_VALUE 时 ±1 会溢出，所以用 long
         * 时间复杂度：O(N)
         * 空间复杂度：O(1)（不算输出）
         */
        public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
            List<List<Integer>> res = new ArrayList<>();
            long prev = (long) lower - 1;
            for (int i = 0; i <= nums.length; i++) {
                // 最后一轮用哨兵 upper + 1
                long cur = i < nums.length ? nums[i] : (long) upper + 1;
                if (cur - prev >= 2) {
                    res.add(Arrays.asList((int) (prev + 1), (int) (cur - 1)));
                }
                prev = cur;
            }
            return res;
        }

    }

    public static class Solution2 {

        /**
         * 面试变体：输出拼成字符串
         * - 缺一个数写这个数，缺一段写 start-end，逗号分隔
         * - 遍历和 Solution1 相同，找到缺口后直接追加到 StringBuilder
         * - 第一个区间前不加逗号：sb 为空时不加
         * - 没有缺失时返回空字符串
         * - 有负数时 "-" 既是负号又是连接符（如 "-5--3"），可以问面试官要不要换成 "->" 或 "~"
         * 时间复杂度：O(N)
         * 空间复杂度：O(1)（不算输出）
         */
        public String findMissingRanges(int[] nums, int lower, int upper) {
            StringBuilder sb = new StringBuilder();
            long prev = (long) lower - 1;
            for (int i = 0; i <= nums.length; i++) {
                // 最后一轮用哨兵 upper + 1
                long cur = i < nums.length ? nums[i] : (long) upper + 1;
                if (cur - prev >= 2) {
                    long start = prev + 1, end = cur - 1;
                    if (sb.length() > 0) sb.append(',');
                    sb.append(start);
                    if (start != end) sb.append('-').append(end);
                }
                prev = cur;
            }
            return sb.toString();
        }

    }

    public static class TestSolution2 {

        private final Solution2 s = new Solution2();

        @Test
        public void example() {
            Assert.assertEquals("3-49,51,53-74,76-99",
                    s.findMissingRanges(new int[]{0, 1, 2, 50, 52, 75}, 0, 99));
        }

        @Test
        public void missingAtBothEnds() {
            Assert.assertEquals("0,2-4,6", s.findMissingRanges(new int[]{1, 5}, 0, 6));
        }

        @Test
        public void emptyArray() {
            Assert.assertEquals("1-5", s.findMissingRanges(new int[]{}, 1, 5));
            Assert.assertEquals("7", s.findMissingRanges(new int[]{}, 7, 7));
        }

        @Test
        public void nothingMissing() {
            Assert.assertEquals("", s.findMissingRanges(new int[]{1, 2, 3}, 1, 3));
        }

    }

    public static class TestSolution1 {

        private final Solution1 s = new Solution1();

        private static List<Integer> r(int start, int end) {
            return Arrays.asList(start, end);
        }

        @Test
        public void example() {
            Assert.assertEquals(Arrays.asList(r(2, 2), r(4, 49), r(51, 74), r(76, 99)),
                    s.findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
        }

        @Test
        public void nothingMissing() {
            Assert.assertEquals(Collections.emptyList(), s.findMissingRanges(new int[]{-1}, -1, -1));
        }

        @Test
        public void emptyArray() {
            Assert.assertEquals(Collections.singletonList(r(1, 5)), s.findMissingRanges(new int[]{}, 1, 5));
        }

        @Test
        public void intBoundariesDoNotOverflow() {
            Assert.assertEquals(Collections.singletonList(r(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1)),
                    s.findMissingRanges(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, Integer.MIN_VALUE, Integer.MAX_VALUE));
        }

    }

}
