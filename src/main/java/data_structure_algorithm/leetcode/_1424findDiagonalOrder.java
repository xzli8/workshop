package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class _1424findDiagonalOrder {

    public static class Solution1 {

        /**
         遍历模拟(类似题："498.对角线遍历")：TEL(Time Exceeded Limit)
         思路：根据矩形的特点，设行的标号为i，列的标号为j。则对于每一条对角线而言，i + j的值是唯一的。
         时间复杂度：O(M * N)
         空间复杂度：O(1)
         */
         public int[] findDiagonalOrder(List<List<Integer>> nums) {
             // 通过矩阵的行列数m和n，计算对角线条数m+n-1
             int m = nums.size(), n = 0;
             for (List<Integer> row : nums) n = Math.max(n, row.size());

             // 遍历所有的对角线
             List<Integer> res = new ArrayList<>();
             for (int i = 0; i < m + n - 1; i++) {
                 // 找每个对角线的起点
                 int x = i < m ? i : m - 1, y = i - x;
                 while (x >= 0) {
                     List<Integer> row = nums.get(x);
                     if (row.size() > y) res.add(row.get(y));
                     x--;
                     y++;
                 }
             }
             return res.stream().mapToInt(Integer::intValue).toArray();
         }

    }



    public static class Solution2 {

        /**
         哈希表:https://leetcode.cn/problems/diagonal-traverse-ii/solutions/220288/treemapan-dui-jiao-xian-ju-he-zhi-by-zuo-zhou-ren/
         思路：根据矩形的特点，设行的标号为i，列的标号为j。则对于每一条对角线而言，i + j的值是唯一的。
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int[] findDiagonalOrder(List<List<Integer>> nums) {
            // 构建映射
            Map<Integer, List<Integer>> idxSum2Nums = new TreeMap<>();  // key:行列下标的和；value:元素列表
            int count = 0;  // 矩阵中元素个数
            for (int i = 0; i < nums.size(); i++) {
                count += nums.get(i).size();
                for (int j = 0; j < nums.get(i).size(); j++) {
                    idxSum2Nums.putIfAbsent(i + j, new ArrayList<>());
                    idxSum2Nums.get(i + j).add(nums.get(i).get(j));
                }
            }

            // 遍历映射，构建结果
            int[] res = new int[count];
            int idx = 0;
            for (int idxSum : idxSum2Nums.keySet()) {
                List<Integer> list = idxSum2Nums.get(idxSum);
                for (int j = list.size() - 1; j >= 0; j--) res[idx++] = list.get(j);
            }
            return res;
        }

    }

}
