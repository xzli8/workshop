package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _1524numOfSubarrays {

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(numOfSubarrays(new int[] {1,3,5}));
//            System.out.println(numOfSubarrays(new int[] {2,4,6}));
        }

        /**
         前缀和
         */
        public int numOfSubarrays(int[] arr) {
            int MOD = 1000000007, preSum = 0;
            Map<Boolean, Integer> odd2CountMod = new HashMap<>();
            odd2CountMod.put(true, 0);
            odd2CountMod.put(false, 1);
            for (int num : arr) {
                preSum += num;
                boolean isOdd = (preSum ^ 1) != 0;
                int countMod = (odd2CountMod.get(!isOdd) + 1) % MOD;
                odd2CountMod.put(!isOdd, countMod);
            }
            return (odd2CountMod.get(true) + odd2CountMod.get(false)) % MOD;
        }


    }

}
