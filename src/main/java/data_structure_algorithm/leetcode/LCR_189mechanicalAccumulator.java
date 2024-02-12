package data_structure_algorithm.leetcode;

public class LCR_189mechanicalAccumulator {

    public static class Solution1 {

        /**
         公式计算(用到了乘法)
         时间复杂度：O(1)
         空间复杂度：O(1)
         */
         public int mechanicalAccumulator(int target) {
             return (target + 1) * target / 2;
         }

    }



    public static class Solution2 {

        /**
         迭代(用到了循环)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int mechanicalAccumulator(int target) {
             int sum = 0;
             for (int i = 1; i <= target; i++) sum += i;
             return sum;
         }

    }



    public static class Solution3 {

        /**
         递归(用到了条件表达式，相当于if)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int mechanicalAccumulator(int target) {
             return target == 0 ? 0 : target + mechanicalAccumulator(target - 1);
         }

    }



    public static class Solution4 {

        /**
         递归(用逻辑运算符的来替代条件表达式)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int mechanicalAccumulator(int target) {
            boolean flag = target > 0 && (target += mechanicalAccumulator(target - 1)) > 0;
            return target;
        }

    }

}
