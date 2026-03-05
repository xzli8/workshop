package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _229majorityElement {

    public static class Solution1 {

        /**
         哈希表: O(N), O(N)
         */
        public List<Integer> majorityElement(int[] nums) {
            // 哈希表统计数字出现次数
            Map<Integer, Integer> num2Count = new HashMap<>();
            for (int num : nums) {
                num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
            }

            // 找到出现次数大于n/3的数字
            int n = nums.length;
            List<Integer> res = new ArrayList<>();
            for (int num : num2Count.keySet()) {
                if (num2Count.get(num) > n / 3) {
                    res.add(num);
                }
            }
            return res;
        }

    }


    public static class Solution2 {

        /**
         * 摩尔投票法: O(N), O(1)
         * Note: 出现次数大于n/3的元素，最多有两个，需要设置两个标签和计数器，当遍历到的数与两个标签的值都不相等时，使两个计数器都减1。
         */
        public List<Integer> majorityElement(int[] nums) {
            // 遍历投票
            int num1 = 0, num2 = 0, count1 = 0, count2 = 0;
            for (int num : nums) {
                if (count1 > 0 && num == num1) { //如果该元素为第一个元素，则计数加1
                    count1++;
                } else if (count2 > 0 && num == num2) { //如果该元素为第二个元素，则计数加1
                    count2++;
                } else if (count1 == 0) { // 选择第一个元素
                    num1 = num;
                    count1++;
                } else if (count2 == 0) { // 选择第二个元素
                    num2 = num;
                    count2++;
                } else { //如果三个元素均不相同，则相互抵消1次
                    count1--;
                    count2--;
                }
            }

            // 检测元素出现的次数是否满足要求
            int cnt1 = 0, cnt2 = 0;
            for (int num : nums) {
                if (count1 > 0 && num == num1) {
                    cnt1++;
                }
                if (count2 > 0 && num == num2) {
                    cnt2++;
                }
            }
            List<Integer> ans = new ArrayList<>();
            if (count1 > 0 && cnt1 > nums.length / 3) {
                ans.add(num1);
            }
            if (count2 > 0 && cnt2 > nums.length / 3) {
                ans.add(num2);
            }
            return ans;
        }

    }

}
