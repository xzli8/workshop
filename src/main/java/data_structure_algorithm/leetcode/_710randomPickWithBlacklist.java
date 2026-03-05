package data_structure_algorithm.leetcode;

import java.util.*;

public class _710randomPickWithBlacklist {


    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(n, blacklist);
     * int param_1 = obj.pick();
     */

    public static class Solution1 {

        // 将backlist中的元素交换到数组的尾部，再在非尾部区域生成随机数
        class SolutionII {
            private int[] nums;
            private int bound;     // 有效区间是 [0, bound)
            private Random rand = new Random();

            public SolutionII(int n, int[] blacklist) {
                // 1. 把数组建出来：a = [0, 1, ..., n-1]
                nums = new int[n];
                for (int i = 0; i < n; i++) nums[i] = i;

                bound = n - blacklist.length;   // 黑名单恰好占满尾部 [bound, n)

                Set<Integer> black = new HashSet<>();
                for (int b : blacklist) black.add(b);

                // 2. 把落在前部区 [0, bound) 的黑名单值，交换到尾部
                int last = n - 1;
                for (int b : blacklist) {
                    if (b < bound) {                       // 只有前部区的才需要处理
                        while (black.contains(nums[last])) {  // 在尾部找一个“白名单值”的槽
                            last--;
                        }
                        int tmp = nums[b]; nums[b] = nums[last]; nums[last] = tmp;  // 换：白名单值进前部，黑名单值去尾部
                        last--;
                    }
                    // b >= bound 本来就在尾部，跳过
                }
            }

            public int pick() {
                // 3. 只在非尾部区间随机取下标
                return nums[rand.nextInt(bound)];
            }
        }

        class Solution {

            /**
             思路：将backlist中的元素交换到数组的尾部，再在非尾部区域生成随机数
             注意：n可能会非常大，内存中无法放下int[n]数组，所以需要另寻他法～
             方法：用一个map将backlist中的元素映射到数组尾部
             */

            private int size;
            private Map<Integer, Integer> map;
            private Random random = new Random();

            public Solution(int n, int[] blacklist) {
                size = n - blacklist.length;
                map = new HashMap<>(blacklist.length);

                // 先将元素都放进map中(key一定得是元素，value是啥无所谓)，后面判断用
                for (int i = 0; i < blacklist.length; i++) {
                    map.put(blacklist[i], i);
                }

                // 将backlist中的元素映射为尾部元素
                int last = n - 1;
                for (int black : blacklist) {
                    // 如果black在尾部，不需要映射
                    if (black >= size) {
                        continue;
                    }
                    // 尾部元素last在map中，跳过
                    while (map.containsKey(last)) {
                        last--;
                    }
                    // 映射
                    map.put(black, last--);
                }
            }

            public int pick() {
                // 随机生成一个size范围内的索引
                int idx = random.nextInt(size);
                // 如果索引在黑名单中，需要映射
                if (map.containsKey(idx)) {
                    return map.get(idx);
                }
                // 如果索引不在黑名单中，直接返回
                return idx;
            }

        }

    }

}
