package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class _414thirdMax {


    public static class Solution1 {

        /**
         排序 + 哈希表:O(NlogN), O(N)
         */
        public int thirdMax(int[] nums) {
            Arrays.sort(nums);

            // 有重复元素就不行: [2, 2, 3, 1]
            // int n = nums.length;
            // return n < 3 ? nums[n - 1] : nums[n - 3];

            int n = nums.length, res = nums[n - 1];
            Set<Integer> s = new HashSet<>();
            s.add(res);
            for (int i = n - 2; i >= 0; i--) {
                if (!s.contains(nums[i])) {
                    res = nums[i];
                    s.add(res);
                    if (s.size() == 3) return res;
                }
            }
            return nums[n - 1];
        }

    }


    public static class Solution2 {

        /**
         排序 + 双指针: O(NlogN), O(1)
         */
        public int thirdMax(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length, res = nums[n - 1], k = 2, i = n - 2;
            while (i >= 0) {
                while (i >= 0 && nums[i] == nums[i + 1]) i--;
                if (i < 0) break;
                res = nums[i--];
                if (--k == 0) return res;
            }
            return nums[n - 1];
        }

    }


    public static class Solution3 {

        /**
         优先队列: O(NlogK) -> O(N), O(1)
         */
        public int thirdMax(int[] nums) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            Set<Integer> s = new HashSet<>();
            int k = 3, n = nums.length, max = nums[0];
            for (int i = 0; i < Math.min(k, n); i++) {
                max = Math.max(max, nums[i]);
                if (!s.contains(nums[i])) {
                    pq.offer(nums[i]);
                    s.add(nums[i]);
                }
            }
            for (int i = k; i < n; i++) {
                max = Math.max(max, nums[i]);
                if (!s.contains(nums[i]) && (pq.size() < 3 || nums[i] > pq.peek())) {
                    if (pq.size() >= 3) s.remove(pq.poll());
                    pq.offer(nums[i]);
                    s.add(nums[i]);
                }
            }
            return pq.size() >= k ? pq.peek() : max;
        }

    }


    public static class Solution4 {

        /**
         有序集合TreeSet: O(N), O(1)
         Note: 可以用TreeSet来代替PriorityQueue+Set，更简单
         */
        public int thirdMax(int[] nums) {
            TreeSet<Integer> s = new TreeSet<Integer>();
            for (int num : nums) {
                s.add(num);
                if (s.size() > 3) {
                    s.remove(s.first());
                }
            }
            return s.size() == 3 ? s.first() : s.last();
        }

    }


    public static class Solution5 {

        /**
         三变量: O(N), O(1)
         */
        public int thirdMax(int[] nums) {
            long firstMax = Long.MIN_VALUE, secondMax = Long.MIN_VALUE, thirdMax = Long.MIN_VALUE;
            for (long num : nums) {
                if (num > firstMax) {
                    thirdMax = secondMax;
                    secondMax = firstMax;
                    firstMax = num;
                    // 不能写成"num > secondMax"，虽然if条件互斥，但可能存在"num == firstMax"的边界条件
                } else if (secondMax < num && num < firstMax) {
                    thirdMax = secondMax;
                    secondMax = num;
                } else if (thirdMax < num && num < secondMax) {
                    thirdMax = num;
                }
            }
            return thirdMax == Long.MIN_VALUE ? (int) firstMax : (int) thirdMax;
        }


        /**
         三变量(用Integer代替long，避免范围问题): O(N), O(1)
         */
        public int thirdMaxII(int[] nums) {
            Integer firstMax = null, secondMax = null, thirdMax = null;
            for(int num : nums) {
                if (firstMax == null || num > firstMax) {
                    thirdMax = secondMax;
                    secondMax = firstMax;
                    firstMax = num;
                } else if (num < firstMax && (secondMax == null || num > secondMax)) {
                    thirdMax = secondMax;
                    secondMax = num;
                } else if (secondMax != null && num < secondMax && (thirdMax == null || num > thirdMax)) {
                    thirdMax = num;
                }
            }
            return thirdMax == null ? firstMax : thirdMax;
        }

    }

}
