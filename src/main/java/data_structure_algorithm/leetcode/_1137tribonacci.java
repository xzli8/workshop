package data_structure_algorithm.leetcode;

public class _1137tribonacci {

    /**
     动态规划
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        int t0 = 0, t1 = 1, t2 = 1, t3 = 0;
        for (int i = 3; i <= n; i++) {
            t3 = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = t3;
        }
        return t3;
    }

}
