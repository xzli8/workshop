package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _77combine {

    public static class Solution1 {

        /**
         回溯(类似题："78.子集")
         思路：与"78.子集"思路基本相同，唯一不同的是，需要加上递归终止条件
         时间复杂度：O(C(k, n) * k)，组合数C(k, n)中结果，每种结果K个数需要O(K)的时间复杂度枚举
         空间复杂度：O(K)，递归栈深度为k，临时数组大小为k
         */
        public List<List<Integer>> combine(int n, int k) {
            backtrace(n, k, 1, new LinkedList<>());
            return res;
        }

        private List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int n, int k, int start, LinkedList<Integer> path) {
            // 递归终止条件：找到k个数后就停下来
            if (path.size() == k) {
                res.add(new LinkedList<>(path));
                return;
            }

            // 遍历当前层
            for (int i = start; i <= n; i++) {
                // 当前节点加入
                path.addLast(i);
                // 进入下一层(注意"i+1"防止重复选择)
                backtrace(n, k, i + 1, path);
                // 回到当前层，当前节点不选择
                path.removeLast();
            }

        }

    }

}
