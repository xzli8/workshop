package data_structure_algorithm.leetcode;

import java.util.Scanner;

public class KamaCoder_55rightRotateString {

    public static class Solution1 {

        /**
         三次反转：在字符串是可变类型的语言中，可以实现O(1)的空间复杂度
         做法：先将前k个字符反转，然后将后面剩余字符反转，再整体反转
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public static class Main {
            public static void main(String[] args) {
                Scanner in = new Scanner(System.in);
                int n = Integer.parseInt(in.nextLine());
                String s = in.nextLine();

                int len = s.length();  //获取字符串长度
                char[] chars = s.toCharArray();
                reverseString(chars, 0, len - n - 1);  //反转前一段字符串，此时的字符串首尾是0,len - n - 1
                reverseString(chars, len - n, len - 1);  //反转后一段字符串，此时的字符串首尾是len - n,len - 1
                reverseString(chars, 0, len - 1);  //反转整个字符串

                System.out.println(chars);

            }

            public static void reverseString(char[] ch, int start, int end) {
                //异或法反转字符串，参照题目 344.反转字符串的解释
                while (start < end) {
                    ch[start] ^= ch[end];
                    ch[end] ^= ch[start];
                    ch[start] ^= ch[end];
                    start++;
                    end--;
                }
            }
        }

    }

}
