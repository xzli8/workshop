package data_structure_algorithm.algorithm.match;

/**
 * KMP 字符串匹配 —— 带教学注释与运行轨迹
 *
 * 全程只围绕一句话：匹配失败时，文本指针永不后退，靠 next 数组让模式串"跳着滑"。
 *
 * 运行：直接跑 main 即可。
 */
public class KMP {

    // ------------------------------------------------------------------
    // 第一步：构建 next 数组
    //   next[i] = "P 的前 i+1 个字符" 的最长公共前后缀长度
    //   （既是前缀、又是后缀，且不能是整个串本身的那个最长长度）
    //   本质：让 P 自己跟自己跑一遍 KMP
    // ------------------------------------------------------------------
    static int[] buildNext(String p) {
        int n = p.length();
        int[] next = new int[n];
        next[0] = 0;          // 单个字符，没有"真前缀/真后缀"，长度恒为 0

        // len：当前已匹配上的最长公共前后缀长度，
        //      它同时也是"接下来要拿来比的前缀字符的下标"
        int len = 0;
        int i = 1;            // 从第 2 个字符开始算

        while (i < n) {
            if (p.charAt(i) == p.charAt(len)) {
                // 情况一：又能延长一位公共前后缀
                len++;
                next[i] = len;
                i++;
            } else if (len > 0) {
                // 情况二：失配了，但别归零——跳到更短的已知答案再试
                //         这一步就是 KMP "滑动" 思想在求 next 时的复刻
                len = next[len - 1];
            } else {
                // 情况三：len 已经退无可退，这个位置就是 0
                next[i] = 0;
                i++;
            }
        }
        return next;
    }

    // ------------------------------------------------------------------
    // 第二步：用 next 数组做匹配
    //   返回 p 在 t 中第一次出现的起始下标，找不到返回 -1
    //   trace = true 时打印每一步指针，方便观察
    // ------------------------------------------------------------------
    static int search(String t, String p, int[] next, boolean trace) {
        int i = 0;   // 文本指针：只前进，永不后退（这就是 KMP 线性的原因）
        int j = 0;   // 模式指针：指向当前要比对 p 的哪个字符

        while (i < t.length()) {
            if (t.charAt(i) == p.charAt(j)) {
                // 配上一个，两根指针一起前进
                if (trace) System.out.printf("  匹配 t[%d]=%c == p[%d]=%c%n",
                        i, t.charAt(i), j, p.charAt(j));
                i++;
                j++;
                if (j == p.length()) {
                    // p 整个走完了，匹配成功
                    return i - j;
                }
            } else if (j > 0) {
                // 失配：j 跳到 next[j-1]，i 一动不动！
                if (trace) System.out.printf("  失配 t[%d]=%c != p[%d]=%c  ->  j 跳 %d->%d (i 不动)%n",
                        i, t.charAt(i), j, p.charAt(j), j, next[j - 1]);
                j = next[j - 1];
            } else {
                // j 已经在 0，没法再跳，只能让文本指针前进一格
                if (trace) System.out.printf("  失配 t[%d]=%c != p[0]=%c  ->  i 前进%n",
                        i, t.charAt(i), p.charAt(0));
                i++;
            }
        }
        return -1; // 文本走完都没匹配上
    }

    static int search(String t, String p) {
        return search(t, p, buildNext(p), false);
    }

    // ------------------------------------------------------------------
    // 例子
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        String p = "ababc";

        // --- 例子 1：看看 next 数组长什么样 ---
        int[] next = buildNext(p);
        System.out.println("模式串 P = " + p);
        System.out.print("下标:  ");
        for (int k = 0; k < p.length(); k++) System.out.printf("%2d ", k);
        System.out.print("\n字符:  ");
        for (int k = 0; k < p.length(); k++) System.out.printf("%2c ", p.charAt(k));
        System.out.print("\nnext:  ");
        for (int v : next) System.out.printf("%2d ", v);
        System.out.println("\n");

        // --- 例子 2：带轨迹的匹配，亲眼看指针怎么跳 ---
        String t = "ababaababc";
        System.out.println("在 T = " + t + " 中查找 P = " + p);
        System.out.println("逐步轨迹：");
        int pos = search(t, p, next, true);
        System.out.println("结果：起始下标 = " + pos + "\n");

        // --- 例子 3：几个边界情况 ---
        System.out.println("找不到的情况: " + search("abcabc", "abx"));   // -1
        System.out.println("开头就命中:   " + search("hello", "he"));      // 0
        System.out.println("结尾才命中:   " + search("hello", "lo"));      // 3
    }
}
