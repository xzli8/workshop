package dsa;

import org.junit.Test;

public class SmallSum {

    @Test
    public void test() {
        int[] nums = new int[] {1, 3, 5, 2, 4, 6};
        System.out.println(smallSum(nums, 0, nums.length - 1));
    }

    private long smallSum(int[] nums, int l, int r) {
        if (l >= r) return 0;
        int m = l + ((r - l) >> 1);
        return smallSum(nums, l, m) + smallSum(nums, m + 1, r) + merge(nums, l, m, r);
    }

    private long merge(int[] nums, int l, int m, int r) {
        int n = r - l + 1;
        int[] tmp = new int[n];
        int i = l, j = m + 1, k = 0, sum = 0;
        while (i <= m && j <= r) {
            if (nums[i] <= nums[j]) {
                sum += (r - j + 1) * nums[i];
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= m) tmp[k++] = nums[i++];
        while (j <= r) tmp[k++] = nums[j++];
        for (i = 0; i < n; i++) {
            nums[l + i] = tmp[i];
        }
        return sum;
    }

}
