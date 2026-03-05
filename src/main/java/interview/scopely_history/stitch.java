package interview.scopely_history;

import java.util.Arrays;

public class stitch {

    /**
     * 图片拼接（Scopely 面试题）
     *
     * 背景
     *
     * 两张图片按行组织，需要把它们拼成一张更长的图片。
     *
     * 第一张图片的下半部分，可能和第二张图片的上半部分重叠（比如截长图时上下两段有重复区域）。我们要找出最大的重叠，去重后拼接。
     *
     * 数据表示
     *
     * 一张图片用 int[][] 表示：
     *
     * - 外层数组 = 多行
     * - 每个 int[] = 一行（可以是该行的像素值，或已提取好的特征）
     *
     * 任务
     *
     * 给定 image1 和 image2，分两步：
     *
     * 第一步：求最大重叠行数 k
     *
     * 找到最大的 k，使得：
     *
     * image1 的最后 k 行  ==  image2 的前 k 行
     *
     * （两行相等 = 两个 int[] 逐元素相等）
     *
     * 第二步：合并
     *
     * 结果 = image1  +  image2[k...]
     *
     * 即保留 image1 全部，再接上 image2 去掉前 k 行之后的部分。
     *
     * 示例
     *
     * image1 = [ A, B, C, D ]
     * image2 = [ C, D, E, F ]
     *
     * image1 末尾 [C, D] == image2 开头 [C, D]  →  k = 2
     *
     * 结果 = [ A, B, C, D, E, F ]
     *
     * 约束 / 注意点
     *
     * - k 的范围是 0 ≤ k ≤ min(image1行数, image2行数)
     * - k = 0 表示完全没有重叠，结果就是两张图首尾相接
     * - 重叠必须是 image1 的后缀 对 image2 的前缀，不能错位匹配
     *
     * 考点
     *
     * - 本质是字符串匹配：把"一行"看成"一个字符"，求 image1 的后缀 == image2 的前缀 的最长长度
     * - 对应 LeetCode 1392. Longest Happy Prefix（前缀函数 / next 数组）
     * - 核心技巧：拼接 image2 + 分隔行 + image1，对其求 next 数组，next[最后一位] 即为 k
     *
     * 复杂度目标
     *
     * - 设总行数 N、每行长 L
     * - 暴力：O(N²·L)
     * - KMP / next 数组：O(N·L)，空间 O(N)
     * - 进阶：每行先做哈希（RK），把行比较降到接近 O(1)，但需处理哈希碰撞
     */

    public static class Solution1 {

        /**
         KMP: next数组，失效函数: O(N·L), O(N)
         思路: 拼成 image2 + 分隔行 + image1，next[最后一位]就是最大重叠行数
         */
        public int[][] stitch(int[][] image1, int[][] image2) {
            int n1 = image1.length, n2 = image2.length;

            // 拼接序列: image2 + SEP + image1。SEP只用引用当"墙"，挡住跨接缝的假匹配
            int[] SEP = new int[0];
            int[][] comb = new int[n2 + 1 + n1][];
            int idx = 0;
            for (int[] row : image2) comb[idx++] = row;
            comb[idx++] = SEP;
            for (int[] row : image1) comb[idx++] = row;

            int m = comb.length, k = 0;     // k表示已经匹配的前缀的长度
            int[] next = new int[m];        // next[i]表示comb[0, i]的最长前缀=最长后缀的长度(以"行"为单位)
            for (int i = 1; i < m; i++) {
                while (k > 0 && !rowEquals(comb[i], comb[k], SEP)) {
                    k = next[k - 1];        // 不匹配时回退
                }
                if (rowEquals(comb[i], comb[k], SEP)) k++;   // 匹配成功时长度+1
                next[i] = k;
            }
            int overlap = next[m - 1];      // 最大重叠行数

            // 合并: image1全部 + image2[overlap..]
            int[][] res = new int[n1 + n2 - overlap][];
            int p = 0;
            for (int[] row : image1) res[p++] = row;
            for (int i = overlap; i < n2; i++) res[p++] = image2[i];
            return res;
        }

        // "字符相等" 换成 "整行相等"; 分隔行和谁都不等
        private boolean rowEquals(int[] a, int[] b, int[] sep) {
            if (a == sep || b == sep) return false;
            return Arrays.equals(a, b);
        }

    }


    public static class Solution2 {

        /**
         RK: 两层哈希。先把每行压成哈希值, 再像1392那样滚动比较
         时间 O(N·L) 预处理 + O(N) 匹配, 空间 O(N)
         */
        public int[][] stitch(int[][] image1, int[][] image2) {
            int n1 = image1.length, n2 = image2.length;
            long base = 31, mod = (long)(1e9 + 7);   // mod 比1392的1e7+7大, 减少碰撞

            // 第一层: 每行 int[] 压成一个哈希值
            long[] h1 = rowHashes(image1, base, mod);
            long[] h2 = rowHashes(image2, base, mod);

            // 第二层: 和1392一模一样, prefix从image2前面滚, suffix从image1后面滚
            int L = Math.min(n1, n2), overlap = 0;
            long prefix = 0, suffix = 0, pow = 1;
            for (int k = 1; k <= L; k++) {
                prefix = (prefix * base + h2[k - 1]) % mod;   // image2 的前 k 行
                suffix = (suffix + h1[n1 - k] * pow) % mod;   // image1 的后 k 行
                if (prefix == suffix) overlap = k;            // 哈希相等 → 认为重叠 k 行
                pow = pow * base % mod;
            }

            // 合并: image1 全部 + image2[overlap..]
            int[][] res = new int[n1 + n2 - overlap][];
            int p = 0;
            for (int[] row : image1) res[p++] = row;
            for (int i = overlap; i < n2; i++) res[p++] = image2[i];
            return res;
        }

        // 把每一行 int[] 压成一个哈希值 (多项式哈希)
        private long[] rowHashes(int[][] img, long base, long mod) {
            long[] h = new long[img.length];
            for (int i = 0; i < img.length; i++) {
                long v = 0;
                for (int x : img[i]) v = (v * base + x) % mod;
                h[i] = v;
            }
            return h;
        }

    }

}
