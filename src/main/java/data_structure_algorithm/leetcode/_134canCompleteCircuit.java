package data_structure_algorithm.leetcode;

public class _134canCompleteCircuit {

    public static class Solution0 {

        /**
         贪心
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            for (int start = 0; start < n; start++) {
                int rest = 0, end = start;
                while (rest + gas[end] - cost[end] >= 0) {
                    rest += gas[end] - cost[end];
                    if (++end >= n) end -= n;
                    if (end == start) return start;
                }
                if (end < start) return -1;
                start = end;
            }
            return -1;
        }

    }



    public static class Solution1 {

        /**
         贪心：可以不用挨个枚举起点，下一个可能的起点是上一次的终点
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            for (int i = 0; i < n; i++) {   // 枚举起点
                int restOil = 0; // 剩余油量
                int j = i;  // 终点
                while (restOil + gas[j] - cost[j] >= 0) {
                    restOil += gas[j] - cost[j];
                    j = (j + 1) % n;
                    if (j == i) return i;
                }
                if (j < i) return -1;
                i = j;  // 贪心贪在这儿：j之前都不可能是起点，所以从j开始新的尝试（因为j之前的任何位置作为起点到这里都会断油，ref:https://leetcode.cn/problems/gas-station/solutions/488357/jia-you-zhan-by-leetcode-solution/?envType=study-plan-v2&envId=top-interview-150）
            }
            return -1;
        }

    }



    public static class Solution2 {

    }


}
