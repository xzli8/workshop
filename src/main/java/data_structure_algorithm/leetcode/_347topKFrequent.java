package data_structure_algorithm.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _347topKFrequent {

    public static class Solution1 {

        /**
         哈希表 + (小顶)堆
         思路：遍历数组，用哈希表记录每个元素及其出现次数，用堆来取出哈希表中出现次数最多的K的元素
         时间复杂度：O(NlogK)
         空间复杂度：O(N)
         */
        public int[] topKFrequent(int[] nums, int k) {
            // 遍历数组，用哈希表记录每个元素的出现次数
            Map<Integer, Integer> occurrences = new HashMap<>();
            for (int num : nums) {
                occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
            }

            // int[]的第一个元素代表元素值，第二个元素代表元素的出现次数
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(m -> m[1]));
            for (Integer num : occurrences.keySet()) {
                pq.offer(new int[] {num, occurrences.get(num)});
                if (pq.size() > k) {
                    pq.poll();
                }
            }

            // 取出堆中元素，返回最终结果
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = pq.poll()[0];
            }
            return res;
        }

    }


    public static class Solution2 {

        /**
         哈希表 + 快排分区: O(N), O(N)
         Note: 先用哈希表统计出现次数，然后快排分区的时候按照出现次数分区
         */
        public int[] topKFrequent(int[] nums, int k) {
            // 用哈希表统计每个数字出现的次数
            Map<Integer, Integer> num2Count = new HashMap<>();
            for (int num : nums) {
                num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
            }

            // 将统计结果转换成数组形式
            int n = num2Count.size(), j = 0;
            int[][] counts = new int[n][2];
            for (int num : num2Count.keySet()) {
                counts[j][0] = num;
                counts[j][1] = num2Count.get(num);
                j++;
            }

            // 用快排分区对counts求解(类似二分法)
            int target = n - k, left = 0, right = n - 1;
            while (left <= right) {
                int mid = partition(counts, left, right);
                if (mid == target) {
                    int[] res = new int[k];
                    for (int i = mid; i < counts.length; i++) {
                        res[i - mid] = counts[i][0];
                    }
                    return res;
                } else if (mid < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return null;
        }

        private int partition(int[][] nums, int left, int right) {
            int pivot = nums[right][1] , i = left;
            for (int j = left; j < right; j++) {
                if (nums[j][1] <= pivot) {
                    if (j != i) swap(nums, i, j);
                    i++;
                }
            }
            swap(nums, i, right);
            return i;
        }

        private void swap(int[][] nums, int i, int j) {
            int[] tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }


    public static class Solution2_1 {

        /**
         Not work!没有考虑重复数字(如果结果要求保留重复数字这样写ok)
         哈希表 + 快排分区: O(N), O(N)
         Note: 先用哈希表统计出现次数，然后快排分区的时候按照出现次数分区。
         分区数组: int[n][2] (int[0]/int[1]分别是数字及其出现次数) or Pair<Integer, Integer>[] (k-数字, v-数字出现次数)
         */
        public int[] topKFrequent(int[] nums, int k) {
            // 用哈希表统计每个数字出现的次数
            for (int num : nums) {
                num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
            }

            // 类似二分法分区求解
            int n = nums.length, target = n - k, left = 0, right = n - 1;
            while (left <= right) {
                int mid = partition(nums, left, right);
                if (mid == target) {
                    int[] res = new int[k];
                    for (int i = mid; i < n; i++) {
                        res[i - mid] = nums[i];
                    }
                    return res;
                } else if (mid < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return null;
        }

        private Map<Integer, Integer> num2Count = new HashMap<>();
        private int partition(int[] nums, int left, int right) {
            int pivot = num2Count.get(nums[right]), i = left;
            for (int j = left; j < right; j++) {
                if (num2Count.get(nums[j]) <= pivot) {
                    if (j != i) swap(nums, i, j);
                    i++;
                }
            }
            swap(nums, i, right);
            return i;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }


    public static class Solution3 {

        /**
         * 桶排序: O(N), O(N)
         * Note:
         * - 出现次数的范围是 [1, N]，所以可以按次数分桶：buckets[c] 存所有出现了 c 次的数
         * - 从次数最大的桶往小的取，取够 k 个就返回
         * - 和堆的对比：桶排序 O(N) 但要开 N + 1 个桶；堆 O(NlogK)，k 远小于 N 时更省空间
         * Ref: https://leetcode.cn/problems/top-k-frequent-elements/solutions/3655287/tong-pai-xu-on-xian-xing-zuo-fa-pythonja-oqq2/
         */
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> num2Count = new HashMap<>();
            for (int num : nums) {
                num2Count.merge(num, 1, Integer::sum);
            }

            java.util.List<Integer>[] buckets = new java.util.List[nums.length + 1];
            for (Map.Entry<Integer, Integer> e : num2Count.entrySet()) {
                int c = e.getValue();
                if (buckets[c] == null) buckets[c] = new java.util.ArrayList<>();
                buckets[c].add(e.getKey());
            }

            int[] res = new int[k];
            int i = 0;
            for (int c = nums.length; c >= 1 && i < k; c--) {
                if (buckets[c] == null) continue;
                for (int num : buckets[c]) {
                    if (i == k) break;
                    res[i++] = num;
                }
            }
            return res;
        }

    }


    /**
     追问：数据量很大、单机放不下，或是持续到来的流（比如统计 Top K 错误码）
     - 离线大文件：按 hash(key) % M 拆成 M 个小文件（同一个 key 必然落在同一个文件）
         - 每个小文件内部用哈希表计数 + 大小为 k 的小顶堆
         - 再把 M 个局部 Top K 合并，求全局 Top K
     - 分布式：MapReduce / Spark 先按 key 聚合计数，再每个分区求 Top K，最后汇总
     - 实时流：滑动窗口计数
         - 精确：按时间分桶（比如每分钟一个哈希表），窗口滑动时减去过期桶
         - 近似（key 太多、内存不够）：Count-Min Sketch 估算次数 + 小顶堆维护 Top K；或 Space-Saving 算法
     */

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(java.util.Arrays.toString(new Solution1().topKFrequent(nums, 2)));   // [2, 1]（堆弹出顺序：次数小的先出）
        System.out.println(java.util.Arrays.toString(new Solution3().topKFrequent(nums, 2)));   // [1, 2]
    }

}
