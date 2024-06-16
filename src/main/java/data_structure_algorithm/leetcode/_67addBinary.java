package data_structure_algorithm.leetcode;

public class _67addBinary {

    public static class Solution0 {

        /**
         模拟竖式加法(10进制改为2进制)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String addBinary(String a, String b) {
            StringBuilder sb = new StringBuilder();
            int i = a.length() - 1, j = b.length() - 1, carry = 0;
            while (i >= 0 || j >= 0 || carry != 0) {
                int n1 = i >= 0 ? a.charAt(i--) - '0' : 0;
                int n2 = j >= 0 ? b.charAt(j--) - '0' : 0;
                int sum = n1 + n2 + carry;
                carry = sum / 2;
                sb.append(sum % 2);
            }
            return sb.reverse().toString();
        }

    }



    public static class Solution1 {

        /**
         模拟加法运算（与415.字符串相加-https://leetcode.cn/problems/add-strings/类似）
         时间复杂度：O(max(len1, len2))
         空间复杂度：O(1)
         */
        public String addBinary(String a, String b) {
            int i = a.length() - 1, j = b.length() - 1, carry = 0;
            StringBuilder res = new StringBuilder();
            while (i >= 0 || j >= 0 || carry != 0) {
                int x = i >= 0 ? a.charAt(i) - '0' : 0;
                int y = j >= 0 ? b.charAt(j) - '0' : 0;
                int sum = x + y + carry;
                res.append(sum % 2);
                carry = sum / 2;
                i--;
                j--;
            }
            res.reverse();
            return res.toString();
        }

    }

}
