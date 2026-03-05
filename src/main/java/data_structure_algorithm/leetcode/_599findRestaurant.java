package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _599findRestaurant {

    public static class Solution1 {

        /**
         哈希表：O(N), O(N)
         */
        public String[] findRestaurant(String[] list1, String[] list2) {
            Map<String, Integer> str2Idx = new HashMap<>();
            for (int i = 0; i < list1.length; i++) {
                str2Idx.put(list1[i], i);
            }

            int minIdxSum = Integer.MAX_VALUE;
            List<String> res = new ArrayList<>();
            for (int i = 0; i < list2.length; i++) {
                if (str2Idx.containsKey(list2[i]))  {
                    int idxSum = i + str2Idx.get(list2[i]);
                    if (idxSum < minIdxSum) {
                        minIdxSum = idxSum;
                        res.clear();
                        res.add(list2[i]);
                    } else if (idxSum == minIdxSum) {
                        res.add(list2[i]);
                    }
                }
            }
            return res.toArray(new String[0]);  // new String[0]只是为了指定类型，也可以写成new String[res.size()]
        }

    }


    public static class Solution2 {


        /**
         哈希表
         时间复杂度：O(M + N)
         空间复杂度：O(M + N)
         */
        public String[] findRestaurant(String[] list1, String[] list2) {
            // 将答案与索引进行映射
            Map<String, Integer> name2Idx1 = getName2Idx(list1);
            Map<String, Integer> name2Idx2 = getName2Idx(list2);

            // 计算索引和
            Map<String, Integer> name2IdxSum = new HashMap<>();
            for (String str : name2Idx1.keySet()) {
                if (name2Idx2.containsKey(str)) {
                    Integer sum = name2Idx1.get(str) + name2Idx2.get(str);
                    name2IdxSum.put(str, sum);
                }
            }

            // 找最少索引和
            Integer minSum = Integer.MAX_VALUE;
            for (String str : name2IdxSum.keySet()) {
                Integer sum = name2IdxSum.get(str);
                if (sum < minSum) {
                    minSum = sum;
                }
            }

            // 找最少索引和对应的选择
            List<String> res = new ArrayList<>();
            for (String str : name2IdxSum.keySet()) {
                if (name2IdxSum.get(str).equals(minSum)) {
                    res.add(str);
                }
            }

            // 返回结果
            return res.toArray(new String[res.size()]);
        }

        private Map<String, Integer> getName2Idx(String[] list) {
            Map<String, Integer> name2Idx = new HashMap<>();
            for (int i = 0; i < list.length; i++) {
                name2Idx.put(list[i], i);
            }
            return name2Idx;
        }

    }

}
