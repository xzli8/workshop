package data_structure_algorithm.leetcode;

public class _844backspaceCompare {

    public static class Solution1 {

        /**
         栈：遇到#将栈顶元素pop，这里可以用StringBuilder模拟栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean backspaceCompare(String s, String t) {
             return deleteBackspace(s).equals(deleteBackspace(t));
         }

         private String deleteBackspace(String s) {
             StringBuilder sb = new StringBuilder();
             for (char c : s.toCharArray()) {
                 if (c != '#') {
                     sb.append(c);
                 } else {
                     if (sb.length() > 0) {
                         sb.deleteCharAt(sb.length() - 1);
                     }
                 }
             }
             return sb.toString();
         }

   }



   public static class Solution2 {

       /**
        双指针：(类似题:"26、27、283")
        思路：先用快慢指针将字符串中的#消除，变成有效字符串后再比较
        时间复杂度：O(N)
        空间复杂度：O(N)，java中String为不可变对象，cpp中可以做到O(1)的空间复杂度
        */
        public boolean backspaceCompare(String s, String t) {
            return deleteBackspace(s.toCharArray()).equals(deleteBackspace(t.toCharArray()));
        }

        private String deleteBackspace(char[] cs) {
            int slow = 0, fast = 0;
            while (fast < cs.length) {
                if (cs[fast] != '#') {
                    cs[slow++] = cs[fast];
                } else {
                    if (slow > 0) slow--;
                }
                fast++;
            }
            return String.valueOf(cs).substring(0, slow);
        }

   }



    public static class Solution3 {

        /**
         双指针：因为#只对前面字符生效，不对后面字符生效，所以可以从后往前遍历
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean backspaceCompare(String s, String t) {
            int i = s.length() - 1, j = t.length() - 1;
            int skipS = 0, skipT = 0;
            while (i >= 0 || j >= 0) {
                // 在s中确定待比较的字符
                while (i >= 0) {
                    if (s.charAt(i) == '#') {
                        skipS++;
                        i--;
                    } else if (skipS > 0) {
                        skipS--;
                        i--;
                    } else {
                        break;
                    }
                }

                // 在t中确定待比较的字符
                while (j >= 0) {
                    if (t.charAt(j) == '#') {
                        skipT++;
                        j--;
                    } else if (skipT > 0) {
                        skipT--;
                        j--;
                    } else {
                        break;
                    }
                }

                // 比较当前字符
                if (i >= 0 && j >= 0) {
                    if (s.charAt(i) != t.charAt(j)) {
                        return false;
                    }
                } else {
                    if (i >= 0 || j >= 0) {
                        return false;
                    }
                }

                // 指针往前移
                i--;
                j--;
            }
            return true;
        }


    }



}
