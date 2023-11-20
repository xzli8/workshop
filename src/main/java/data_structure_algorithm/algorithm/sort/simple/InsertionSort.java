package data_structure_algorithm.algorithm.sort.simple;

public class InsertionSort {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 3, 5, 4, 2};

        new InsertionSort().insertionSort(nums);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    /**
     *  插入排序：将数组分为两部分，前半部分为有序区间，后半部分为无序区间。每次让无序区间中的第一个数插入到有序区间中。
     *      时间复杂度：最好O(N)/平均O(N^2)/最坏O(N^2)
     *      空间复杂度：O(1)
     *      稳定性：稳定
     */
    public void insertionSort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int tmp = nums[i], j = i - 1;
            while (j >= 0 && nums[j] > tmp) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = tmp;
        }
    }

}
