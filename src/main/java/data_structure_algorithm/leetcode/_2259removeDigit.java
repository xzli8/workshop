package data_structure_algorithm.leetcode;

public class _2259removeDigit {

    public static class Solution1 {

         /**
             Greedy + StringEnumeration
                 TimeComplexity:
                 SpaceComplexity:
          */
         public String removeDigit(String number, char digit) {
             String maxNum = "0";
             int n = number.length();
             for (int i = 0; i < n; i++) {
                 if (number.charAt(i) == digit) {
                     String tmp = number.substring(0, i) + number.substring(i + 1);
                     if (less(maxNum, tmp)) {
                         maxNum = tmp;
                     }
                 }
             }
             return maxNum;
         }

         private boolean less(String num1, String num2) {
             return num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0);
         }

    }



    public static class Solution2 {

        /**
         Greedy + StringEnumeration
         TimeComplexity:
         SpaceComplexity:
         */
        public String removeDigit(String number, char digit) {
            // Init
            int n = number.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n - 1; i++) {
                sb.append("0");
            }
            String maxNum = sb.toString();

            // Enumeration
            for (int i = 0; i < n; i++) {
                if (number.charAt(i) == digit) {
                    String tmp = number.substring(0, i) + number.substring(i + 1);
                    if (less(maxNum, tmp)) {
                        maxNum = tmp;
                    }
                }
            }
            return maxNum;
        }

        private boolean less(String num1, String num2) {
            // In the main function, make sure num1 and num2 always has the same length.
            return num1.compareTo(num2) < 0;
        }

    }

}
