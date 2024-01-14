package data_structure_algorithm.leetcode;

import java.util.List;

public class Mianshiti_08_06hanota {

    public static class Solution1 {

        /**
         分治：将n个盘子看成由两部分组成，一部分有1个盘子，另一部分有n-1个盘子。将这两部分借助中间B移动从A移动到C
         */
        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            int n = A.size();
            move(n, A, B, C);
        }

        private void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
            // 当A上只有一个盘子时，直接将其从A移动到C
            if (n == 1) {
                C.add(0, A.remove(0));
                return;
            }

            // 先将上面的n-1个盘子，从A移动到B(借助C)
            move(n - 1, A, C, B);
            // 然后将最后一个盘子移动到C
            C.add(0, A.remove(0));
            // 再将B上的n-1个盘子移动到C(借助A)
            move(n - 1, B, A, C);
        }

    }

}
