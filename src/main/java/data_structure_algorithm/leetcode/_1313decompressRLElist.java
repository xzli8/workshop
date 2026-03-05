package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _1313decompressRLElist {

    public static class Solution1 {

        public int[] decompressRLElist(int[] nums) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length - 1; i += 2) {
                int freq = nums[i], val = nums[i + 1];
                for (int j = 0; j < freq; j++) {
                    res.add(val);
                }
            }
            return res.stream().mapToInt(i -> i).toArray();
        }

    }

}
