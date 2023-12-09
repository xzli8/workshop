package data_structure_algorithm.leetcode;

public class _258addDigits {

    public static class Solution1 {

        /**
         模拟
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
         public int addDigits(int num) {
             while (num > 9) {
                 num = next(num);
             }
             return num;
         }

         private int next(int num) {
             int sum = 0;
             while (num != 0) {
                 int mod = num % 10;
                 num /= 10;
                 sum += mod;
             }
             return sum;
         }

    }



    public static class Solution2 {

        /**
         树根：数学推导
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
        public int addDigits(int num) {
            return (num - 1) % 9 + 1;
        }

    }


}
