package data_structure_algorithm.sort.simple;

public class SelectionSort {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 3, 5, 4, 2};

        new SelectionSort().selectionSort(nums);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     *  选择排序：将数组分为两部分，前半部分为有序区间，后半部分为无序区间。每次从无序区间中选择最小值，放到无序区间的头部。
     *      时间复杂度：最好/平均/最坏：O(N^2)
     *      空间复杂度：O(1)
     *      稳定性：不稳定
     */
    public void selectionSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int leastIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[leastIndex]) {
                    leastIndex = j;
                }
            }
            swap(nums, i, leastIndex);
        }
    }

}
