package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _47permuteUnique {

    public static class Solution1 {

        // 回溯
        public List<List<Integer>> permuteUnique(int[] nums) {
            dfs(nums, 0);
            return res;
        }

        List<List<Integer>> res = new ArrayList<>();

        private void dfs(int[] nums, int k) {
            int n = nums.length;
            if (k == n-1) {
                List<Integer> tmp = new ArrayList<>(n);
                for (int i = 0; i < n; i++) tmp.add(nums[i]);
                res.add(tmp);
                return;
            }

            for (int i = k; i < n; i++) {
                if (!canSwap(nums, i, k)) continue;
                swap(nums, k, i);
                dfs(nums, k+1);
                swap(nums, k, i);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        private boolean canSwap(int[] nums, int i, int k) {
            for (int j = k; j < i; j++) {
                if (nums[j] == nums[i]) return false;
            }
            return true;
        }

    }

}
