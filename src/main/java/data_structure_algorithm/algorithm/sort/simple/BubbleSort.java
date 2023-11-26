package data_structure_algorithm.algorithm.sort.simple;

public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 3, 5, 4, 2};

        new BubbleSort().bubbleSort(nums);

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
     *  冒泡排序：通过比较并交换相邻元素，每次让第i大的数正确归位
     *      时间复杂度：最好O(N)/平均O(N^2)/最坏O(N^2)
     *      空间复杂度：O(1)
     *      稳定性：稳定
     */
    public void bubbleSort(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            // 用于判断内循环是否有数据交换，如果没有数据交换，说明已经有序，可以提前退出
            boolean exchange = false;
            for (int j = 0; j < i; j++) {
//                if (nums[j] >= nums[j + 1])   // 这样写就是不稳定排序
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    exchange = true;
                }
            }
            if (!exchange) break;
        }
    }

}
