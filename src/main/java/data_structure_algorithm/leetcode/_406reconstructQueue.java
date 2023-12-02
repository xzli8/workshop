package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _406reconstructQueue {

    public static class Solution1 {

        /**
         贪心(类似题："135.分发糖果")
         思路：多个条件需要满足，先满足其中一个，再进行调整使满足另外的条件。
         分析：有身高h和人数k需要满足，可以先按身高排序，然后再调整满足人数要求。
         做法：先把身高按照从大到小顺序排列，身高相同的k较小的放在前面。然后从前往后遍历，调整顺序使得人数满足

         正确性分析：先按身高从高到低排队，当身高相同时，k小的排在前面，这样h的相对排序就ok了，
         后面在插入队列的时候，只需要考虑k就行了，然后从按照k前往后插入元素，
         因为大的都在前面，所以保证了局部最优，最终就可以实现全局最优。

         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public int[][] reconstructQueue(int[][] people) {
            // 身高按照从小到大排列，身高相同的k较小的排在前面
            Arrays.sort(people, (p1, p2) -> {
                if (p1[0] == p2[0]) {
                    return p1[1] - p2[1];
                }
                return p2[0] - p1[0];
            });

            // 从前往后遍历，根据k调整顺序
            List<int[]> res = new LinkedList<>();
            for (int[] p : people) {
                res.add(p[1], p);
            }
            return res.toArray(new int[people.length][]);
        }
    }


}
