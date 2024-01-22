package data_structure_algorithm.leetcode;

public class _670maximumSwap {

    public static class Solution1 {

        /**
         尽可能靠前的小数 与 尽可能靠后的大数 交换

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maximumSwap(int num) {
            char[] nums = String.valueOf(num).toCharArray();
            int n = nums.length;

            // 逆序遍历，找从结尾到当前数字的最大数字的下标
            int[] maxIndex = new int[n];
            int index = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (nums[i] > nums[index]) index = i;
                maxIndex[i] = index;
            }

            // 正序遍历，如果当前数字后有比当前数字更大的数，交换
            for (int i = 0; i < n; i++) {
                if (nums[i] != nums[maxIndex[i]]) {
                    char tmp = nums[i];
                    nums[i] = nums[maxIndex[i]];
                    nums[maxIndex[i]] = tmp;
                    break;
                }
            }
            return Integer.valueOf(new String(nums));
        }

    }

}
