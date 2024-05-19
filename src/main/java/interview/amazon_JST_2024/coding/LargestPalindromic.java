package interview.amazon_JST_2024.coding;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LargestPalindromic {

    /**
     * Date: 2024.03.06 (Online interview)
     * Description: 给你一个string，全部由‘0-9’组成，从这个string中挑选若干字符，组成一个回文数，返回能组成的最大回文数
     * Link: https://leetcode.cn/problems/largest-palindromic-number/description/
     * Reference: https://www.acwing.com/solution/content/134575/
     */

    /**
     * Solution: greedy
     *  TC: O(N)
     *  SC: O(1)
     */

    /**
     贪心：先统计所有字符出现的次数，然后将出现次数大于2的字符从大到小排列，然后选出出现次数为奇数的字符中最大的那个，放在中间
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
    public String largestPalindromic(String num) {
        // 统计所有字符出现的次数
        int[] cnts = new int[10];
        for (char c : num.toCharArray()) cnts[c - '0']++;

        // 将出现次数大于2的字符从大到小排列
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (i == 0 && sb.length() == 0) break;  // 已经枚举到了0，但之前从未加入过别的数字，直接跳过避免前导0
            int cnt = cnts[i] / 2;
            for (int j = 0; j < cnt; j++) sb.append((char) (i + '0'));
            cnts[i] -= cnt * 2;
        }

        // // 去除前导零(这样做不行。bad case: "0000"，因为此时不能把0的个数先减去)
        // int start = 0;
        // while (start < sb.length() && sb.charAt(start) == '0') start++;
        // String half = sb.substring(start);

        // 找出出现次数为奇数的字符中最大的那个(放在中间)
        StringBuilder res = new StringBuilder(sb.toString());
        System.out.println(num.length());
        for (int i = 9; i >= 0; i--) {
            if (cnts[i] > 0) {
                res.append((char) (i + '0'));
                break;
            }
        }
        res.append(sb.reverse());
        return res.toString();
    }

    @Test
    public void test() {
        System.out.println(largestPalindromic("444947137"));
        System.out.println(largestPalindromic("00009"));
        System.out.println(largestPalindromic("000"));
    }

}
