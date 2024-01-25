package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _149maxPoints {


    public static class Solution1 {

        /**
         暴力枚举：固定一个点，枚举另外两个点，看这三个点是否在同一条直线上
         时间复杂度：O(N^3)
         空间复杂度：O(1)
         */
         public int maxPoints(int[][] points) {
             int n = points.length, res = 1; // 至少有两个点在同一直线上，所以res初始化为1
             for (int i = 0; i < n; i++) {
                 int[] x = points[i];
                 for (int j = i + 1; j < n; j++) {
                     int[] y = points[j];

                     // 对于点x和y确定的直线，统计有多少点在该直线上，cnt初始化为2表示刚开始只有x, y在同一直线上
                     int cnt = 2;
                     for (int k = j + 1; k < n; k++) {
                         int[] p = points[k];
                         // 将除法判定转换为乘法，避免舍入误差带来精度损失造成计算结果不准确
                         if ((y[1] - x[1]) * (p[0] - y[0]) == (y[0] - x[0]) * (p[1] - y[1])) cnt++;
                     }
                     res = Math.max(res, cnt);
                 }
             }
             return res;
         }

    }



    public static class Solution2 {

        /**
         哈希表(空间换时间)
         时间复杂度：O(N^2 * logM)
         空间复杂度：O(N)
         */
        public int maxPoints(int[][] points) {
            int n = points.length, res = 1;
            // 遍历每个点
            for (int i = 0; i < n; i++) {
                // 记录从当前点出发的斜率及其对应的数量
                Map<String, Integer> slop2Count = new HashMap<>();
                int max = 0;
                for (int j = i + 1; j < n; j++) {
                    int dx = points[i][0] - points[j][0], dy = points[i][1] - points[j][1];

                    // 为了避免精度损失，直接使用字符串保存斜率，在保存前需要通过最大公约数将分子分母约干净
                    int gcd = gcd(dx, dy);
                    String slop = (dx / gcd) + "/" + (dy / gcd);
                    int count = slop2Count.getOrDefault(slop, 0) + 1;
                    slop2Count.put(slop, count);
                    max = Math.max(max, count);
                }
                res = Math.max(res, max + 1);
            }
            return res;
        }

        // 辗转相除法求最大公约数
        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

    }

}
