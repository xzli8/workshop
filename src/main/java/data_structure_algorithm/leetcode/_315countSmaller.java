package data_structure_algorithm.leetcode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class _315countSmaller {

    public static class Solution1 {

        /**
         归并排序(将下标与元素一起排序)
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public List<Integer> countSmaller(int[] nums) {
            // 构建新数组(下标-元素)
            int n = nums.length;
            Pair<Integer, Integer>[] pairs = new Pair[n];
            for (int i = 0; i < n; i++) {
                pairs[i] = new Pair<>(i, nums[i]);
            }

            // 对pairs进行归并排序
            tmp = new Pair[n];
            count = new int[n];
            mergeSort(pairs, 0, n - 1);

            // 取结果
            List<Integer> res = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                res.add(count[i]);
            }
            return res;
        }

        private Pair<Integer, Integer>[] tmp;
        private int[] count;

        private void mergeSort(Pair<Integer, Integer>[] pairs, int left, int right) {
            if (left >= right) {
                return;
            }

            int mid = left + ((right - left) >> 1);
            mergeSort(pairs, left, mid);
            mergeSort(pairs, mid + 1, right);
            merge(pairs, left, mid, right);
        }

        private void merge(Pair<Integer, Integer>[] pairs, int left, int mid, int right) {
            // 先复制到临时数组中，后面排序的时候用临时数组，排序结果直接放入原数组
            for (int i = left; i <= right; i++) {
                tmp[i] = pairs[i];
            }

            // 两个字数组都有序，考虑左边字数组下标为i的元素，右边有几个元素比i小
            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    pairs[k] = tmp[j++];
                } else if (j == right + 1) {
                    pairs[k] = tmp[i++];
                    count[pairs[k].getKey()] += j - 1 - mid;
                } else if (tmp[i].getValue() <= tmp[j].getValue()) {
                    pairs[k] = tmp[i++];
                    count[pairs[k].getKey()] += j - 1 - mid;
                } else {
                    pairs[k] = tmp[j++];
                }
            }
        }

    }


}
