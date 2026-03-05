package data_structure_algorithm.leetcode;

import java.util.*;

public class _381RandomizedCollection {

    public static class Solution1 {

        class RandomizedCollection {

            private List<Integer> vals = new ArrayList<>();
            private Map<Integer, Set<Integer>> val2Idxs = new HashMap<>();
            private Random random = new Random();

            public RandomizedCollection() {
            }

            public boolean insert(int val) {
                vals.add(val);
                Set<Integer> idxs = val2Idxs.getOrDefault(val, new HashSet<>());
                idxs.add(vals.size() - 1);
                val2Idxs.put(val, idxs);
                return idxs.size() == 1;
            }

            public boolean remove(int val) {
                if (!val2Idxs.containsKey(val)) {
                    return false;
                }
                int idx = val2Idxs.get(val).iterator().next(), lastVal = vals.get(vals.size() - 1);
                vals.set(idx, lastVal);
                val2Idxs.get(val).remove(idx);
                val2Idxs.get(lastVal).remove(vals.size() - 1);

                if (idx < vals.size() - 1) {
                    val2Idxs.get(lastVal).add(idx);
                }
                if (val2Idxs.get(val).size() == 0) {
                    val2Idxs.remove(val);
                }
                vals.remove(vals.size() - 1);
                return true;
            }

            public int getRandom() {
                return vals.get(random.nextInt(vals.size()));
            }

        }

        /**
         * Your RandomizedCollection object will be instantiated and called as such:
         * RandomizedCollection obj = new RandomizedCollection();
         * boolean param_1 = obj.insert(val);
         * boolean param_2 = obj.remove(val);
         * int param_3 = obj.getRandom();
         */

    }

}
