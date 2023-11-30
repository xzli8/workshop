package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _455findContentChildren {

    public static class Solution1 {

        /**
         贪心：能用小的尺寸满足胃口，就不用大的尺寸
         时间复杂度：O(NlogN)，排序的时间复杂度为O(NlogN)，遍历的时间复杂度为O(N)
         空间复杂度：O(1)
         */
         public int findContentChildren(int[] g, int[] s) {
             // 胃口和尺寸都从小到大排序
             Arrays.sort(g);
             Arrays.sort(s);

             // 倒序遍历胃口和饼干(注意这里一定要先遍历胃口，再遍历尺寸，顺序不能反)
             int index = s.length - 1, count = 0;
             for (int i = g.length - 1; i >= 0; i--) {
                 if (index >= 0 && s[index] >= g[i]) {
                     index--;
                     count++;
                 }
             }
             return count;
         }

    }



    public static class Solution2 {

        /**
         贪心：小饼干先喂饱小胃口
         */
        public int findContentChildren(int[] g, int[] s) {
            // 从小到大排序
            Arrays.sort(g);
            Arrays.sort(s);

            // 正序遍历饼干和胃口（注意：这里要先遍历饼干，再遍历胃口）
            int index = 0;
            for (int i = 0; i < s.length; i++) {
                if (index < g.length && s[i] >= g[index]) {
                    index++;
                }
            }
            return index;
        }

    }



}
