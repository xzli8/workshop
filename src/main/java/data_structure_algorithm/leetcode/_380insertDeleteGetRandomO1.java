package data_structure_algorithm.leetcode;

import java.util.*;

public class _380insertDeleteGetRandomO1 {


    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    public static class Solution0 {

        /**
         哈希表 + 动态数组
         时间复杂度：O(1)
         空间复杂度：O(N)
         */
        class RandomizedSet {

            private List<Integer> vals = new ArrayList<>();
            private Map<Integer, Integer> val2Idx = new HashMap<>();
            private Random random = new Random();

            public RandomizedSet() {
            }

            public boolean insert(int val) {
                if (val2Idx.containsKey(val)) return false;
                vals.add(val);
                val2Idx.put(val, vals.size() - 1);
                return true;
            }

            public boolean remove(int val) {
                // if (!val2Idx.containsKey(val)) return false;
                // int idx = val2Idx.remove(val), lastVal = vals.get(vals.size() - 1);
                // if (val2Idx.isEmpty()) {
                //     vals.remove(vals.size() - 1);
                //     return true;
                // }
                // vals.set(idx, lastVal);
                // vals.remove(vals.size() - 1);
                // val2Idx.put(lastVal, idx);
                // return true;

                if (!val2Idx.containsKey(val)) return false;
                int idx = val2Idx.get(val), lastVal = vals.get(vals.size() - 1);
                vals.set(idx, lastVal);
                vals.remove(vals.size() - 1);
                val2Idx.put(lastVal, idx);
                val2Idx.remove(val);
                return true;
            }

            public int getRandom() {
                return vals.get(random.nextInt(vals.size()));
            }

        }

    }



    public static class Solution1 {

        class RandomizedSet {

            /**
             变长数组 + 哈希表 + 删除交换
             分析：insert, remove可以用哈希表在O(1)范围内实现；getRandom()可以用数组的随机下标进行返回
             */

            // 哈希表，存放数值到下标的映射
            private Map<Integer, Integer> num2Idx;

            // 变长数组，存放数值
            private List<Integer> nums;

            // 随机数
            private Random random;

            public RandomizedSet() {
                num2Idx = new HashMap<>();
                nums = new ArrayList<>();
                random = new Random();
            }

            public boolean insert(int val) {
                if (num2Idx.containsKey(val)) {
                    return false;
                }
                nums.add(val);
                num2Idx.put(val, nums.size() - 1);
                return true;
            }

            public boolean remove(int val) {
                if (!num2Idx.containsKey(val)) {
                    return false;
                }

                // 将list的末尾元素赋值到要删除元素的位置，然后删除末尾元素
                int idx = num2Idx.get(val);
                int last = nums.get(nums.size() - 1);
                // 删除操作一定要在更新操作之后，因为加入只剩下一个元素时，如果先删除再更新，就会还原，出错
                num2Idx.put(last, idx);
                num2Idx.remove(val);
                nums.set(idx, last);
                nums.remove(nums.size() - 1);
                return true;
            }

            public int getRandom() {
                return nums.get(random.nextInt(nums.size()));
            }

        }

    }

}
