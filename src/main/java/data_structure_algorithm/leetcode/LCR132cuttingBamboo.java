package data_structure_algorithm.leetcode;

public class LCR132cuttingBamboo {

    /**
     贪心
     不能用dp的原因：结果太大，用long都存不下。但无法在求max过程中求余，因为余数并不等价于结果大。
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
    public int cuttingBamboo(int bamboo_len) {
        if (bamboo_len == 2) return 1;
        if (bamboo_len == 3) return 2;
        if (bamboo_len == 4) return 4;

        long res = 1;
        while (bamboo_len > 4) {
            bamboo_len -= 3;
            res = res * 3 % 1000000007;
        }
        res = res * bamboo_len % 1000000007;
        return (int) res;
    }

}
