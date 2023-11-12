package leetcode;

public class _136singleNumber {

    /**
     异或
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

}
