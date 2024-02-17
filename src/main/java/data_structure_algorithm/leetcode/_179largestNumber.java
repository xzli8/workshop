package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _179largestNumber {

    public static class Solution0 {

        /**
         贪心：若"a + b > b + a"，那么将a排在b前面。需要将nums转换为string，然后自定义比较器
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public String largestNumber(int[] nums) {
            // 数字转换为字符串
            int n = nums.length;
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) strs[i] = String.valueOf(nums[i]);

            // 自定义比较器进行排序
            Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));

            // 特殊情况处理：第一个是0，那么返回0
            if (strs[0].equals("0")) return "0";

            // 组装结果返回
            StringBuilder sb = new StringBuilder();
            for (String str : strs) sb.append(str);
            return sb.toString();
        }

    }



    public static class Solution1 {

        /**
         贪心：若 a+b > b+a，那么将a排在b前面
         java：将int数组转换成String数组后实现自定义比较器
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public String largestNumber(int[] nums) {
            // 将int数组转换成string数组
            int n = nums.length;
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = "" + nums[i];
            }

            // 自定义比较器进行排序
            Arrays.sort(strs, (a, b) -> (b+a).compareTo(a+b));

            // 如果第一个是0，那么后面的肯定是0，直接返回"0"即可
            if (strs[0].equals("0")) return "0";

            // 输出结果
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(strs[i]);
            }
            return sb.toString();
        }

    }

}
