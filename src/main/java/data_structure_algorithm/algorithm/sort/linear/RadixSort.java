package data_structure_algorithm.algorithm.sort.linear;

import java.util.Arrays;

public class RadixSort {

    /**
     RadixSort
     TimeComplexity: O(N)
     SpaceComplexity: O(N)
     */
    public void radixSort(int[] nums) {
        int n = nums.length;
        if (n < 2) return;
        long exp = 1;
        int[] buf = new int[n];
        int maxVal = Arrays.stream(nums).max().getAsInt();

        while (maxVal >= exp) {
            int[] cnt = new int[10];
            for (int i = 0; i < n; i++) {
                int digit = (nums[i] / (int) exp) % 10;
                cnt[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / (int) exp) % 10;
                buf[--cnt[digit]] = nums[i];
            }
            System.arraycopy(buf, 0, nums, 0, n);
            exp *= 10;
        }
    }

}
