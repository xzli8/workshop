package interview.tesla_oa_history;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ZeroRangeSumCount {

    /**
     * Given an array nums consisting of N integers, return the number of fragments of num whose sum equals 0.
     * Return -1 when the result exceeds 1000000000
     */

    public int getZeroRangeSumCount(int[] nums) {
        Map<Integer, Integer> preSum2Count = new HashMap<>();
        preSum2Count.put(0, 1);
        int preSum = 0;
        long res = 0;
        for (int num : nums) {
            preSum += num;
            res += preSum2Count.getOrDefault(preSum, 0);
            if (res > 1000000000) return -1;
            preSum2Count.put(preSum, preSum2Count.getOrDefault(preSum, 0) + 1);
        }
        return (int) res;
    }

    @Test
    public void test() {
        System.out.println(getZeroRangeSumCount(new int[] {2, -2, 3, 0, 4, -7}));   // 4
    }

}
