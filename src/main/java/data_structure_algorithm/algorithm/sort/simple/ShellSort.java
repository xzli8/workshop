package data_structure_algorithm.algorithm.sort.simple;

public class ShellSort {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 3, 5, 4, 2};

        new ShellSort().shellSort(nums);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    /**
     *  希尔排序：插入排序的改进版本，通过增量划分分组，每组都应用插入排序，增量从大到小，先达到宏观有序，然后达到微观有序
     *      时间复杂度：O(N^2)，通过选择合适的增量可以突破O(N^2)的壁垒
     *      空间复杂度：O(1)
     *      稳定性：不稳定
     */
    public void shellSort(int[] nums) {
        int n = nums.length;
        for (int gap = n / 2; gap >= 1; gap >>= 1) {
            for (int i = gap; i < n; i++) {
                int tmp = nums[i], j = i - gap;
                while (j >= 0 && nums[j] > tmp) {
                    nums[j + gap] = nums[j];
                    j -= gap;
                }
                nums[j + gap] = tmp;
            }
        }
    }


}
