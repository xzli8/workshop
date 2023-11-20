package data_structure_algorithm.sort.linear;

public class CountingSort {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 3, 5, 4, 2};

        new CountingSort().countingSort(nums);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }


    /**
     *  计数排序：先统计数组中各元素出现的次数，然后对次数数组求前缀和，再倒序遍历原数组，
     *          同时在前缀和数组中找该数组对应的值，这个值减1即为该元素排序后对应的下标
     *
     *          时间复杂度：O(N)
     *          空间复杂度：O(N * M)，N为数据数量，M为数据范围
     *          稳定性：稳定（倒序遍历稳定，正序遍历不稳定）
     */
    public void countingSort(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;

        // 遍历数据，计算数据范围
        int min = nums[0], max = nums[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        // 统计每个元素的出现次数
        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            counts[num - min]++;
        }

        // 计算counts数组的前缀和数组
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 倒序遍历原数组，计算每个元素的下标
        int[] tmp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            tmp[--counts[nums[i] - min]] = nums[i];
        }

        // 将临时数组中的元素copy到原数组中
        for (int i = 0; i < n; i++) {
            nums[i] = tmp[i];
        }
    }




}
