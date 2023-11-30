package data_structure_algorithm.leetcode;

public class _134canCompleteCircuit {

    public static class Solution1 {

        /**
         遍历
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            for (int i = 0; i < n; i++) {
                int oilSum = 0;
                int j = i;
                while (oilSum + gas[j] - cost[j] >= 0) {
                    oilSum += gas[j] - cost[j];
                    j = (j + 1) % n;
                    if (j == i) {
                        return i;
                    }
                }
                if (j < i) {
                    return -1;
                }
                i = j;
            }
            return -1;
        }

    }



    public static class Solution2 {

    }


}
