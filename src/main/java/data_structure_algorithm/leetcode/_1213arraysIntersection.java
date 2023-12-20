package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class _1213arraysIntersection {

    /**
     * LeetCode 1213. 三个有序数组的交集(Intersection of Three Sorted Arrays)
     *
     *      题目描述：给出三个均为 严格递增排列 的整数数组 arr1，arr2 和 arr3。
     *              返回一个由 仅 在这三个数组中 同时出现 的整数所构成的有序数组。
     *      样例：
     *          输入: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
     *          输出: [1,5]
     *          解释: 只有 1 和 5 同时在这三个数组中出现。
     *      限制：
     *          1 <= arr1.length, arr2.length, arr3.length <= 1000
     *          1 <= arr1[i], arr2[i], arr3[i] <= 2000
     */

    /**
     *  参考题解：
     *      https://www.acwing.com/solution/LeetCode/content/5028/
     *      https://www.cnblogs.com/Dylan-Java-NYC/p/12047372.html
     *      https://www.cnblogs.com/seyjs/p/11629272.html
     *
     *      类似题：1198.找出所有公共行的最小元素
     *      https://www.cnblogs.com/seyjs/p/11596008.html
     */

    public static class Solution1 {

        /**
         *  三指针
         *      时间复杂度：O(N1 + N2 + N3)
         *      空间复杂度：O(1)
         */
        public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
            List<Integer> res = new ArrayList<>();
            int i = 0, j = 0, k = 0;
            while (i < arr1.length && j < arr2.length && k < arr3.length) {
                // 找到相同元素则加入交集
                if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                    res.add(arr1[i]);
                    i++;
                    j++;
                    k++;
                } else {
                    // 将最小元素对应的指针往后移一位
                    int min = Math.min(arr1[i], Math.min(arr2[j], arr3[k]));
                    if (arr1[i] == min) {
                        i++;
                    }
                    if (arr2[j] == min) {
                        j++;
                    }
                    if (arr3[k] == min) {
                        k++;
                    }
                }
            }
            return res;
        }

        @Test
        public void test() {
            int[] expected = new int[] {1,5};
            List<Integer> res = arraysIntersection(new int[] {1,2,3,4,5}, new int[] {1,2,5,7,9}, new int[] {1,3,4,5,8});
            for (int i = 0; i < res.size(); i++) {
                Assert.assertEquals(expected[i], res.get(i).intValue());
            }
        }

    }

}
