package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _1248numberOfSubarrays {

    public static class Solution1 {

        /**
         SlideWindow
         TC: O(N)
         SC: O(1)
         */
        public int numberOfSubarrays(int[] nums, int k) {
            int n = nums.length, left = 0, right = 0, oddCount = 0, res = 0;
            while (right < n) {
                while (right < n && oddCount < k) {
                    if (nums[right] % 2 == 1) {
                        oddCount++;
                    }
                    right++;
                }
                if (oddCount != k) return res;
                int leftEvenCount = 0;
                while (left < right && nums[left] % 2 == 0) {
                    left++;
                    leftEvenCount++;
                }
                int rightEvenCount = 0;
                while (right < n && nums[right] % 2 == 0) {
                    right++;
                    rightEvenCount++;
                }
                res += (leftEvenCount + 1) * (rightEvenCount + 1);
                left++;
                oddCount--;
            }
            return res;
        }

    }



    public static class Solution2 {

        /**
         PreSum: Replace each even by zero and every odd by one
         TC: O(N)
         SC: O(N)
         */
        public int numberOfSubarrays(int[] nums, int k) {
            int res = 0, preSum = 0;
            Map<Integer, Integer> preSum2Count = new HashMap<>();
            preSum2Count.put(0, 1);
            for (int num : nums) {
                preSum += (num % 2 == 0 ? 0 : 1);
                if (preSum2Count.containsKey(preSum - k)) {
                    res += preSum2Count.get(preSum - k);
                }
                preSum2Count.put(preSum, preSum2Count.getOrDefault(preSum, 0) + 1);
            }
            return res;
        }

    }

}
