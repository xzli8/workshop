package data_structure_algorithm.leetcode;

public class _273numberToWords {

    public static class Solution1 {

        /**
         哈希表：
         英文三位作为一个单元，后面跟"Thousand", "Million", "Billion"作为单位；
         首先实现一个三位数的num2Str，然后组合单位即可表示出任意数字；
         w*/
        public String numberToWords(int num) {
            if (num == 0) return "Zero";

            // 单位的映射
            String[] num2StrLarge = new String[] {"Billion", "Million", "Thousand", ""};

            // 从10亿(1e9)开始计算
            StringBuilder res = new StringBuilder();
            for (int i = (int) 1e9, j = 0; i >= 1; i /= 1000, j++) {
                if (num < i) continue;
                res.append(num2Str(num / i) + num2StrLarge[j] + " ");
                num %= i;
            }

            // 去除末尾的空格
            while (res.charAt(res.length() - 1) == ' ') res.deleteCharAt(res.length() - 1);
            return res.toString();
        }

        private String num2Str(int num) {
            // 小于20的数的英文表示 和 数组下标 一一对应关系
            String[] num2StrSmall = new String[] {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                    "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
                    "Nineteen"};
            // 小于100，大于20的数的英文表示 和 数组下标 一一对应关系
            String[] num2StrMedium = new String[] {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
                    "Eighty", "Ninety"};

            StringBuilder res = new StringBuilder();
            // 先构建百位
            if (num >= 100) {
                res.append(num2StrSmall[num / 100]).append(" Hundred ");
                num %= 100;
            }
            // 再构建十位
            if (num >= 20) {
                res.append(num2StrMedium[num / 10]).append(" ");
                num %= 10;
            }
            // 最后构建个位
            if (num != 0) {
                res.append(num2StrSmall[num]).append(" ");
            }
            return res.toString();
        }

    }

}
