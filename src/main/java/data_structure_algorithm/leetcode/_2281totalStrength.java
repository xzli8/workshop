package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _2281totalStrength {

    public static class Solution1 {

        /**
         单调栈(求影响范围)+前缀和(求子数组和):
         思路：设当前枚举的巫师的能力值为v，那么他对答案产生的贡献是v乘上在左右边界[L,R]内的所有包含v的子数组的元素和的和。
         */
        public int totalStrength(int[] strength) {
            // 求最弱能力值的影响范围(用单调栈求左右第一个小于本身的值来确定)
            int n = strength.length, mod = (int) 1e9 + 7;
            int[] leftMin = new int[n], rightMin = new int[n];
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!s.isEmpty() && strength[s.peek()] >= strength[i]) {
                    s.pop();
                }
                leftMin[i] = s.isEmpty() ? -1 : s.peek();
                s.push(i);
            }
            s.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && strength[s.peek()] > strength[i]) {
                    s.pop();
                }
                rightMin[i] = s.isEmpty() ? n : s.peek();
                s.push(i);
            }

            // 求前缀和数组(这里需要求前缀和数组的前缀和数组，因为需要计算子数组和的和)
            int[] preSums = new int[n + 1];
            for (int i = 0; i < n; i++) {
                preSums[i + 1] = (preSums[i] + strength[i]) % mod;
            }
            int[] preSumsPreSums = new int[n + 2];
            for (int i = 0; i < n + 1; i++) {
                preSumsPreSums[i + 1] = (preSumsPreSums[i] + preSums[i]) % mod;
            }

            // 计算每个点的贡献
            long sum = 0;
            for (int i = 0; i < n; i++) {
                // 前面计算的leftMin和rightMin是开区间，这里转换为闭区间
                int l = leftMin[i] + 1, r = rightMin[i] - 1;
                // 计算区间[l, r]内所包含的子数组的元素和的和(被i分隔成两部分)
                long a = (long) (i - l + 1) * (preSumsPreSums[r + 2] - preSumsPreSums[i + 1]);
                long b = (long) (r - i + 1) * (preSumsPreSums[i + 1] - preSumsPreSums[l]);
                sum = (sum + strength[i] * ((a - b) % mod)) % mod;
            }
            return (int) (sum + mod) % mod; // 防止计算出负数，这里进行一下修正
        }

    }

}
