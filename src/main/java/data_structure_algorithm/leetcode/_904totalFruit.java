package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _904totalFruit {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)，哈希表中最多有3个键值对
         */
        public int totalFruit(int[] fruits) {
            Map<Integer, Integer> fruit2Count = new HashMap<>();
            int left = 0, right = 0, maxCount = 0;
            while (right < fruits.length) {
                // 右指针右移，扩大窗口，找可行解
                int in = fruits[right++];
                fruit2Count.put(in, fruit2Count.getOrDefault(in, 0) + 1);

                // 左指针右移，缩小窗口，排除非法解
                while (fruit2Count.size() > 2) {
                    int out = fruits[left++];
                    fruit2Count.put(out, fruit2Count.get(out) - 1);
                    if (fruit2Count.get(out).equals(0)) {
                        fruit2Count.remove(out);
                    }
                }

                // 更新最大值
                maxCount = Math.max(maxCount, right - left);
            }
            return maxCount;
        }

    }

}
