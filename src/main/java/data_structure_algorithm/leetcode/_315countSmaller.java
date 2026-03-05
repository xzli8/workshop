package data_structure_algorithm.leetcode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _315countSmaller {


    public static class Solution0 {

        /**
         分治(归并排序)：O(NlogN), O(N)
         Note: 将下标和元素当成一个整体进行归并排序，在归并过程中计算右侧小于自身的数量
         */
        public List<Integer> countSmaller(int[] nums) {
            // 初始化pairs数组
            int n = nums.length;
            int[][] pairs = new int[n][2];
            for (int i = 0; i < n; i++) {
                pairs[i][0] = i;
                pairs[i][1] = nums[i];
            }
            res = new int[n];

            // 对pairs数组归并排序，在归并排序过程中计算右侧小于自身的数量
            mergeSort(pairs, new int[n][2], 0, n - 1);
            return Arrays.stream(res).boxed().collect(Collectors.toList());
        }

        private int[] res;

        private void mergeSort(int[][] nums, int[][] tmp, int left, int right) {
            if (left >= right) return;
            int mid = left + ((right - left) >> 1);
            mergeSort(nums, tmp, left, mid);
            mergeSort(nums, tmp, mid + 1, right);
            merge(nums, tmp, left, mid, right);
        }

        private void merge(int[][] nums, int[][] tmp, int left, int mid, int right) {
            for (int i = left; i <= right; i++) tmp[i] = nums[i];
            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1) {
                    nums[k] = tmp[i++];
                } else if (tmp[i][1] <= tmp[j][1]) {    // 从大到小排序，更容易理解计数
                    nums[k] = tmp[j++];
                } else {
                    nums[k] = tmp[i++];
                    res[nums[k][0]] += right - j + 1;
                }
            }
        }

    }


    public static class Solution1 {

        /**
         暴力解法(超时)：遍历每个元素，计算其右侧小于该元素的数量
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
        // public List<Integer> countSmaller(int[] nums) {
        //     int n = nums.length;
        //     int[] res = new int[n];
        //     for (int i = 0; i < n; i++) {
        //         for (int j = i + 1; j < n; j++) {
        //             if (nums[j] < nums[i]) res[i]++;
        //         }
        //     }
        //     return Arrays.stream(res).boxed().collect(Collectors.toList());
        // }

    }


    public static class Solution2 {

        /**
         归并排序(将下标与元素一起排序): O(NlogN), O(N)
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
            return Arrays.stream(count).boxed().collect(Collectors.toList());
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

            // 两个子数组都有序，考虑左边子数组下标为i的元素，右边有几个元素比i小
            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    pairs[k] = tmp[j++];
                } else if (j == right + 1) {
                    pairs[k] = tmp[i++];
                    // 此时右边的有序数组已经到结尾，说明整个右边数组元素都比左边当前元素i要小，整个右边数组元素总共有“right - mid”个
                    count[pairs[k].getKey()] += j - 1 - mid;    // "j - 1 - mid" == "right - mid" （此时"j = right + 1")
                } else if (tmp[i].getValue() <= tmp[j].getValue()) {
                    pairs[k] = tmp[i++];
                    count[pairs[k].getKey()] += j - 1 - mid;
                } else {
                    pairs[k] = tmp[j++];
                }
            }
        }

    }


    public static class Solution3 {

        /**
         分治(归并排序)：O(NlogN), O(N)
         Note: 将下标和元素当成一个整体进行归并排序(不用Pair,用数组)，在归并过程中计算右侧小于自身的数量
            这里从大到小排序更容易理解，与之相比"LCR170求逆序对"从小到大排更容易理解。
         */
        public List<Integer> countSmaller(int[] nums) {
            // 初始化pairs数组
            int n = nums.length;
            int[][] pairs = new int[n][2];
            for (int i = 0; i < n; i++) {
                pairs[i][0] = i;
                pairs[i][1] = nums[i];
            }
            res = new int[n];

            // 对pairs数组归并排序，在归并排序过程中计算右侧小于自身的数量
            mergeSort(pairs, new int[n][2], 0, n - 1);
            return Arrays.stream(res).boxed().collect(Collectors.toList());
        }

        private int[] res;

        private void mergeSort(int[][] nums, int[][] tmp, int left, int right) {
            if (left >= right) return;
            int mid = left + ((right - left) >> 1);
            mergeSort(nums, tmp, left, mid);
            mergeSort(nums, tmp, mid + 1, right);
            merge(nums, tmp, left, mid, right);
        }

        private void merge(int[][] nums, int[][] tmp, int left, int mid, int right) {
            for (int i = left; i <= right; i++) tmp[i] = nums[i];
            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1) {
                    nums[k] = tmp[i++];
                    // 此时右边的有序数组已经到结尾，说明整个右边数组元素都比左边当前元素i要小，整个右边数组元素总共有“right - mid”个
                    res[nums[k][0]] += right - mid;
                } else if (tmp[i][1] <= tmp[j][1]) {    // 从小到大排序
                    nums[k] = tmp[i++];
                    // 左边当前元素i小于右边当前元素j，说明右边已经合并的数都小于该左数i，需要统计个数"j - mid - 1"
                    res[nums[k][0]] += j - (mid + 1);
                } else {
                    nums[k] = tmp[j++];
                }
            }
        }

    }


}
