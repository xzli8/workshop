package interview.tesla_oa_history;

import org.junit.Test;

public class Bitwise {

    /**
     * Given two non-negative integers m and n (m <= n), then their bitwise product is the bitor of all integers from m to n,
     * m bitwise n = m bitxor (m + 1) bitxor (m + 2) ... bitxor (n - 1) ... bitxor (n)
     * For example: 5 bitwise 8 = 5 bitxor 6 bitxor 7 bitxor 8 = 12
     *
     * m and n are integers within the range [0, 1000000000], m <= n
     */

    public int bitwise(int m, int n) {
        // 从 m 到 n 的异或 = (0~m-1 的异或) XOR (0~n 的异或)
        return xor0(m - 1) ^ xor0(n);
    }

    // 而 0 ~ n 的异或有直接规律：
    //      n % 4 == 0 → 结果 = n
    //      n % 4 == 1 → 结果 = 1
    //      n % 4 == 2 → 结果 = n+1
    //      n % 4 == 3 → 结果 = 0
    private int xor0(int n) {
        switch (n % 4) {
            case 0: return n;
            case 1: return 1;
            case 2: return n + 1;
            default: return 0;
        }
    }

    @Test
    public void test() {
        System.out.println(bitwise(5, 8)); // 12
    }

}
