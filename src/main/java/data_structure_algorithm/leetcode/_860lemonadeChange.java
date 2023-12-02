package data_structure_algorithm.leetcode;

public class _860lemonadeChange {

    public static class Solution1 {

        /**
         贪心：5能够给10或20找零，10只能给20找零。每次来20的时候优先用10找零，没有10了才用5。
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean lemonadeChange(int[] bills) {
            int fiveCount = 0, tenCount = 0;
            for (int bill : bills) {
                // 来的是5，不用找零，5的个数加1
                if (bill == 5) {
                    fiveCount++;
                }
                // 来的是10，只能用5找零，如果没有5，返回false；如果有5，5的个数减1，10的个数加1
                else if (bill == 10) {
                    if (fiveCount == 0) return false;
                    fiveCount--;
                    tenCount++;
                }
                // 来的是20，优先用10+5找零，没有10再用5+5+5找零
                else {
                    if (fiveCount == 0) return false;
                    if (tenCount > 0) {
                        tenCount--;
                        fiveCount--;
                    } else if (fiveCount >= 3) {
                        fiveCount -= 3;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }

    }


}
