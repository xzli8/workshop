package data_structure_algorithm.leetcode;

import java.util.*;

public class _895FreqStack {

    /**
     * Your FreqStack object will be instantiated and called as such:
     * FreqStack obj = new FreqStack();
     * obj.push(val);
     * int param_2 = obj.pop();
     */

    public static class Solution1 {

        /**
         哈希表 + 按频率分组的栈: push/pop 都是 O(1)
         - val2Freq：每个值当前的出现次数
         - freq2Stack：freq2Stack[f] 存所有"第 f 次被 push"的值，栈内按 push 先后排列
         - maxFreq：当前最大频率
         - push(x)：f = ++val2Freq[x]，把 x 压入 freq2Stack[f]，更新 maxFreq
         - pop()：从 freq2Stack[maxFreq] 弹出栈顶 x（频率最高里最近 push 的），val2Freq[x]--；该栈空了就 maxFreq--
         - 为什么 maxFreq 只需要减 1：频率为 f 的值一定也在 f-1 的栈里出现过，所以 f-1 的栈非空
         - 一个值会同时出现在 1..f 每个栈里，不需要删除旧位置，这是 O(1) 的关键
         */
        class FreqStack {
            private final Map<Integer, Integer> val2Freq = new HashMap<>();
            private final Map<Integer, Deque<Integer>> freq2Stack = new HashMap<>();
            private int maxFreq = 0;

            public FreqStack() {}

            public void push(int val) {
                int f = val2Freq.merge(val, 1, Integer::sum);
                freq2Stack.computeIfAbsent(f, e -> new ArrayDeque<>()).push(val);
                maxFreq = Math.max(maxFreq, f);
            }

            public int pop() {
                Deque<Integer> stack = freq2Stack.get(maxFreq);
                int val = stack.pop();
                val2Freq.merge(val, -1, Integer::sum);
                if (stack.isEmpty()) {
                    freq2Stack.remove(maxFreq);
                    maxFreq--;
                }
                return val;
            }
        }

    }


    public static class Solution2 {

        /**
         优先队列: push/pop O(logN)
         - 堆中元素 [val, freq, seq]：先按 freq 降序，再按 seq（push 序号）降序
         - push 时把当前频率和序号一起入堆；同一个值会有多个堆元素，对应不同的 push 时刻
         - 面试中先说这个思路，再优化到 Solution1
         */
        class FreqStack {
            private final Map<Integer, Integer> val2Freq = new HashMap<>();
            private final PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] != b[1] ? b[1] - a[1] : b[2] - a[2]);
            private int seq = 0;

            public void push(int val) {
                int f = val2Freq.merge(val, 1, Integer::sum);
                pq.offer(new int[]{val, f, seq++});
            }

            public int pop() {
                int val = pq.poll()[0];
                val2Freq.merge(val, -1, Integer::sum);
                return val;
            }
        }

    }


    public static void main(String[] args) {
        // push 5,7,5,7,4,5 后 pop 应依次为 5,7,5,4
        Solution1.FreqStack s1 = new Solution1().new FreqStack();
        Solution2.FreqStack s2 = new Solution2().new FreqStack();
        for (int v : new int[]{5, 7, 5, 7, 4, 5}) {
            s1.push(v);
            s2.push(v);
        }
        for (int i = 0; i < 4; i++) {
            System.out.print(s1.pop() + "/" + s2.pop() + " ");
        }
        System.out.println();   // 5/5 7/7 5/5 4/4
    }

}
