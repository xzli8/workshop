package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _170twoSum {

    /**
     * 题目描述：设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。
     *
     * 实现 TwoSum 类：
     * TwoSum() 使用空数组初始化 TwoSum 对象
     * void add(int number) 向数据结构添加一个数 number
     * boolean find(int value) 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 true ；否则，返回 false 。
     */

    public static class Solution1 {

        public class TwoSum {

            private final Map<Integer, Integer> num2Count;

            public TwoSum() {
                num2Count = new HashMap<>();
            }

            /**
             * 时间复杂度：O(1)
             */
            public void add(int number) {
                num2Count.put(number, num2Count.getOrDefault(number, 0) + 1);
            }

            /**
             * 时间复杂度：O(N)
             */
            public boolean find(int value) {
                for (int num : num2Count.keySet()) {
                    int remain = value - num;
                    if (num2Count.containsKey(remain)) {
                        // 注意remain可能与num相等，此时当count >= 2时才能返回true
                        if (remain == num && num2Count.get(num) < 2) {
                            continue;
                        }
                        return true;
                    }
                }
                return false;
            }

        }

        @Test
        public void test() {

            TwoSum twoSum = new TwoSum();
            twoSum.add(1); // [] --> [1]
            twoSum.add(1); // [1] --> [1, 1]
            twoSum.add(3); // [1] --> [1, 1, 3]
            twoSum.add(5); // [1,3] --> [1, 1, 3, 5]
            Assert.assertTrue(twoSum.find(4));  // 1 + 3 = 4，返回true
            Assert.assertTrue(twoSum.find(2));  // 1 + 1 = 2，返回true
            Assert.assertFalse(twoSum.find(7));  // 没有两个整数加起来等于7 ，返回false
            Assert.assertFalse(twoSum.find(10)); // 没有两个整数加起来等于10，返回false

        }

    }

}
