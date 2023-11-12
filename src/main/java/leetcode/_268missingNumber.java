package leetcode;

public class _268missingNumber {


    /**
     异或
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
//    public int missingNumber(int[] nums) {
//        int res = nums.length;  // 初始化为nums.length，而不是0
//        for (int i = 0; i < nums.length; i++) {
//            res ^= i;
//            res ^= nums[i];
//        }
//        return res;
//    }

    /**
     原地哈希：通过交换将nums[i]放到下标为nums[i]的位置
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] < n && nums[i] != i) {
                swap(nums, i, nums[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) return i;
        }
        return n;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
