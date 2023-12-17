package data_structure_algorithm.leetcode;

import java.util.*;

public class _398randomPickIndex {


    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int param_1 = obj.pick(target);
     */

    public static class Solution1 {

        class Solution {

            /**
             哈希表：记录nums中相同元素的下标
             时间复杂度：初始化为O(N)，pick为O(1)
             空间复杂度：O(N)
             */
            private Map<Integer, List<Integer>> value2Index = new HashMap<>();
            private Random random = new Random();

            public Solution(int[] nums) {
                for (int i = 0; i < nums.length; i++) {
                    value2Index.putIfAbsent(nums[i], new ArrayList<>());
                    value2Index.get(nums[i]).add(i);
                }
            }

            public int pick(int target) {
                List<Integer> indexList = value2Index.get(target);
                return indexList.get(random.nextInt(indexList.size()));
            }

        }

    }



    public static class Solution2 {

        class Solution {

            /**
             水塘抽样(reservoir sampling)：时间换空间
             时间复杂度：初始化为O(1)，pick为O(N)
             空间复杂度：O(N)
             */

             private int[] nums;
             private Random random = new Random();

             public Solution(int[] nums) {
                 this.nums = nums;
             }

             public int pick(int target) {
                 int res = 0;
                 for (int i = 0, j = 0; i < nums.length; i++) {
                     // 遍历到第j个target时，有1/j的概率选择当前元素，1 - 1/j的概率保持原有选择
                     if (nums[i] == target) {
                         j++;
                         if (random.nextInt(j) == 0) {
                             res = i;
                         }
                     }
                 }
                 return res;
             }

        }

    }

}
