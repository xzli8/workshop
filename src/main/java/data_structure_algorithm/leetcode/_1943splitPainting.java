package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class _1943splitPainting {

    public static class Solution0 {

        /**
         TreeMap: O(NlogN), O(N)
         note: 每一次绘画都会产生一段颜色的变化，只需要在绘画开始的地方记录颜色值增加，结束的地方记录颜色值减少，最后遍历一遍即可完成合并。
         ref: https://leetcode.cn/problems/describe-the-painting/solutions/894617/c-ji-lu-yan-se-fa-sheng-bian-hua-de-di-f-a7kh/
         */
        public List<List<Long>> splitPainting(int[][] segments) {
            Map<Integer, Long> diff = new TreeMap<>();
            for (int[] segment : segments) {
                int start = segment[0], end = segment[1], color = segment[2];
                diff.put(start, diff.getOrDefault(start, 0L) + color);
                diff.put(end, diff.getOrDefault(end, 0L) - color);
            }

            List<List<Long>> res = new ArrayList<>();
            int last = 0;
            long color = 0;
            for (int idx : diff.keySet()) {
                if (color != 0) {
                    List<Long> segment = new ArrayList<>();
                    segment.add((long) last);
                    segment.add((long) idx);
                    segment.add(color);
                    res.add(segment);
                }
                last = idx;
                color += diff.get(idx);
            }
            return res;
        }

    }



    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(splitPainting(new int[][]{{1,4,5}, {4,7,7}, {1,7,9}}));
            System.out.println(splitPainting(new int[][]{{1,4,5}, {1,4,7}, {4,7,1}, {4,7,11}}));    // error
        }

        /**
         Difference Array: O(N), O(N)
         (此处由于颜色和对应的颜色集合可能有很多种，使得即使出现某个边界点颜色和变化量为 0，其两侧的颜色也会不同)
         */
         public List<List<Long>> splitPainting(int[][] segments) {
             // update
             int n = (int) 1e5 + 1;
             long[] nums = new long[n];
             Difference diff = new Difference(nums);
             for (int[] segment : segments) {
                 diff.update(segment[0], segment[1] - 1, segment[2]);
             }
             nums = diff.result();

             // convert
             List<List<Long>> res = new ArrayList<>();
             int left = 0, right = 0;
             while (left < n) {
                 while (left < n && nums[left] == 0) {
                     left++;
                 }
                 if (left >= n) break;
                 right = left + 1;
                 while (right < n && nums[right] == nums[left]) {
                     right++;
                 }
                 List<Long> segment = new ArrayList<>();
                 segment.add((long) left);
                 segment.add((long) (right));
                 segment.add(nums[left]);
                 res.add(segment);
                 left = right;
             }
             return res;
         }

         class Difference {

             private long[] diff;

             public Difference(long[] nums) {
                 int n = nums.length;
                 diff = new long[n];
                 diff[0] = nums[0];
                 for (int i = 1; i < n; i++) {
                     diff[i] = nums[i] - nums[i - 1];
                 }
             }

             public void update(int start, int end, long num) {
                 diff[start] += num;
                 if (end + 1 < diff.length) {
                     diff[end + 1] -= num;
                 }
             }

             public long[] result() {
                 int n = diff.length;
                 long[] res = new long[n];
                 res[0] = diff[0];
                 for (int i = 1; i < n; i++) {
                     res[i] = res[i - 1] + diff[i];
                 }
                 return res;
             }

         }

    }



    public static class Solution2 {

        /**
         Difference: O(N), O(N)
         note: Solution1的改进，加一个isStart数组标志是否是区间起点，避免case3的情况
         */
        public List<List<Long>> splitPainting(int[][] segments) {
            int n = (int) 1e5 + 1;
            long[] diff = new long[n];
            boolean[] isStart = new boolean[n]; // handle case3
            for (int[] seg : segments) {
                int start = seg[0], end = seg[1], color = seg[2];
                diff[start] += color;
                diff[end] -= color;
                isStart[start] = true;
            }

            long[] mixColor = new long[n];
            long val = 0;
            for (int i = 0; i < n; i++) {
                val += diff[i];
                mixColor[i] = val;
            }

            List<List<Long>> res = new ArrayList<>();
            int left = 0, right = 0;
            while (left < n) {
                while (left < n && mixColor[left] == 0) left++;
                if (left == n) break;
                right = left + 1;
                while (right < n && !isStart[right] && mixColor[right] == mixColor[left]) right++;
                res.add(Arrays.asList((long) left, (long) right, mixColor[left]));
//                res.add(List.of((long) left, (long) right, mixColor[left]));
                left = right;
            }
            return res;
        }

    }



    public static class Solution3 {

        /**
         * TreeSet + Difference: O(NlogN), O(N)
         * note: TreeSet记录所有的端点，Difference计算所有的颜色和
         */
        public List<List<Long>> splitPainting(int[][] segments) {
            // 差分数组右端点值
            int max = 0;
            // 需要分割的点，TreeSet即可排序端点，又可弹出左端点
            TreeSet<Integer> set = new TreeSet<>();
            for (int[] segment: segments) {
                if (segment[1] > max) {
                    max = segment[1];
                }
                set.add(segment[0]);
                set.add(segment[1]);
            }
            // 差分数组，从这里开始直接用long，不然可能数据溢出
            long[] diff = new long[max + 1];
            for(int[] segment: segments) {
                diff[segment[0]] += segment[2];
                diff[segment[1]] -= segment[2];
            }
            long[] preSum = new long[max + 1];
            for(int i = 1; i <= max; i++) {
                preSum[i] = preSum[i-1] + diff[i];
            }
            List<List<Long>> ans = new ArrayList<>();
            while (set.size() > 1) {
                // 左边界获取并删除，右边界获取不删除
                int l = set.pollFirst(), r = set.first();
                // 区间[l, r)颜色为0，剔除
                if (preSum[l] == 0) {
                    continue;
                }
                ans.add(Arrays.asList((long)l, (long)r, preSum[l]));
            }
            return ans;
        }

    }



    public static class Solution4 {

        /**
         * Difference: O(N), O(N)
         * note: 用计数数组替代TreeSet记录所有的端点，Difference计算所有的颜色和
         */
        public List<List<Long>> splitPainting(int[][] segments) {
            List<List<Long>> result = new ArrayList<List<Long>> ();
            int max = 0;
            for (int[] segment: segments) {
                if (segment[1] > max)  {
                    max = segment[1];
                }
            }
            long[][] change = new long[max + 1][2];
            for (int[] segment : segments) {
                change[segment[0]][0] += segment[2];
                change[segment[1]][1] -= segment[2];
            }
            long sum = 0, start = 0;
            for(int i = 0; i < change.length; ++i) {
                if(change[i][0] != 0 || change[i][1] != 0) {
                    // 只要一个不为0，就差分求和，求和前先判断
                    if(sum == 0) {
                        start = i;
                    }
                    if(sum != 0) {
                        result.add(Arrays.asList(start, (long)i, sum));
                        start = i;
                    }
                    sum += change[i][0] + change[i][1];
                }
            }
            return result;
        }

    }

}
