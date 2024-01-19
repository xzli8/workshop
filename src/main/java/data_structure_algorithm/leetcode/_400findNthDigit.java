package data_structure_algorithm.leetcode;

public class _400findNthDigit {

    public static class Solution1 {

        /**
         模拟：依次减去每个长度的数字所占位的数量
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int findNthDigit(int n) {
            if (n <= 9) return n;
            int remain = n - 9, digit = 2;
            while (remain > 0) {
                long digitCount = digitCount(digit);
                if (digitCount > remain) break;
                remain -= digitCount;
                digit++;
            }
            int whichNum = (remain - 1) / digit, whichPos = (remain - 1) % digit;
            return String.valueOf(getStartNum(digit) + whichNum).charAt(whichPos) - '0';
        }

        public long getStartNum(int digit) {
            StringBuilder res = new StringBuilder();
            res.append('1');
            for (int i = 1; i < digit; i++) res.append('0');
            return Long.parseLong(res.toString());
        }

        public long getEndNum(int digit) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < digit; i++) res.append('9');
            return Long.parseLong(res.toString());
        }

        public long digitCount(int digit) {
            return (getEndNum(digit) - getStartNum(digit) + 1) * digit;
        }

    }

}
