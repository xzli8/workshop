package data_structure_algorithm.leetcode;

public class _137singleNumber {

    public static class Solution0 {

        /**
         位运算：遍历统计每个二进制位上1出现的次数，然后对3取余，最后左移得到结果
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int singleNumber(int[] nums) {
            // 遍历数组，统计每个二进制位上1出现的次数
            int[] counts = new int[32];
            for (int num : nums) {
                for (int i = 0; i < 32; i++, num >>>= 1) {
                    counts[i] += num & 1;
                    // if ((num & 1) == 1) counts[i]++; // 这样写也可以
                }
            }

            // 次数对3取余
            for (int i = 0; i < 32; i++) counts[i] %= 3;
            // for (int count : counts) count %= 3; // not work, for each only adapt for read only

            // 左移得到结果
            int res = 0;
            for (int i = 0; i < 32; i++) {
                res <<= 1;
                res |= counts[31 - i];
            }
            return res;

            // 转换为String后进行二进制转换得到结果(not work, ref: https://www.iteye.com/problems/49058, https://www.lidihuo.com/java/java-binary-to-decimal.html)
            // StringBuilder sb = new StringBuilder();
            // for (int count : counts) sb.append(count);
            // return Integer.parseInt(sb.reverse().toString(), 2);
        }

    }

    public static class Solution1 {

        /**
         位运算：遍历统计每个二进制位上1出现的次数，然后对3取余，最后左移恢复（可扩展到“其余每个元素都出现k次”）
         分析：如果没有空间限制，可以用一个hash表记录每个元素及其出现的次数
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int singleNumber(int[] nums) {
            // 遍历统计
            int[] counts = new int[32];
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < 32; j++) {
                    counts[j] += nums[i] & 1;   // 更新第j位
                    nums[i] >>>= 1;     // 右移一位（无符号）
                }
            }

            // 对3取余
            for (int i = 0; i < 32; i++) {
                counts[i] %= 3;
            }

            // 左移恢复
            int res = 0;
            for (int i = 0; i < 32; i++) {
                res <<= 1;
                res |= counts[31-i];
            }
            return res;
        }

    }

}
