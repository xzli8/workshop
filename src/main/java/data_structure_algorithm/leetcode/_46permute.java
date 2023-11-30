package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _46permute {

    public static class Solution1 {

        public List<List<Integer>> permute(int[] nums) {
            backtrace(nums, 0);
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();
        private void backtrace(int[] nums, int k) {
            if (k == nums.length) {
                List<Integer> tmp = new ArrayList<>(nums.length);
                for (int i = 0; i < nums.length; i++) tmp.add(nums[i]);
                res.add(tmp);
                return;
            }

            for (int i = k; i < nums.length; i++) {
                swap(nums, k, i);
                backtrace(nums, k+1);
                swap(nums, k, i);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }

}
