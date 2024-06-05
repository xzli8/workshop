package data_structure_algorithm.leetcode;

public class _2903findIndices {

    public static class Solution1 {

        /**
         模拟：双层循环
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
             int n = nums.length;
             for (int i = 0; i < n; i++) {
                 for (int j = i + indexDifference; j < n; j++) {
                     if (Math.abs(nums[i] - nums[j]) >= valueDifference) {
                         return new int[] {i, j};
                     }
                 }
             }
             return new int[] {-1, -1};
         }

    }



    public static class Solution2 {

        /**
         模拟：一次遍历
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
            int n = nums.length, maxId = 0, minId = 0;
            for (int j = indexDifference; j < n; j++) {
                int i = j - indexDifference;
                if (nums[i] > nums[maxId]) maxId = i;
                if (nums[i] < nums[minId]) minId = i;
                if (nums[maxId] - nums[j] >= valueDifference) return new int[] {maxId, j};
                if (nums[j] - nums[minId] >= valueDifference) return new int[] {minId, j};
            }
            return new int[] {-1, -1};
        }

    }

}
