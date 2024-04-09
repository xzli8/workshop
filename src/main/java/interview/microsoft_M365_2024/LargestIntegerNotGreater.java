package interview.microsoft_M365_2024;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LargestIntegerNotGreater {

    // 2024.04.09

    /**
     * I have a few lucky numbers between 0 to 9.
     * And given a positive integer N,
     * find the largest integer that only contains my lucky numbers as digits on each position,
     * and not greater than N.
     *
     * N can be very big, like an integer contains thousands or millions of digits.
     *
     * For example, I have lucky numbers: 3, 5, and 7
     * So 777 will get to 777 (remain the same)
     * 864 will get to 777 (largest integer smaller or equal to the input, and only contains lucky numbers which are 3, 5, or 7 in this case)
     * 764 will get to 757
     * 719 will get to 577
     */

    public String largestIntegerNotGreater(String num, int[] luckyNumbers) {
        Arrays.sort(luckyNumbers);

        Set<Character> s = new HashSet<>();
        for (int luckyNumber : luckyNumbers) {
            s.add((char) (luckyNumber));
        }
        int n = num.length(), i = 0;
        while (i < n) {
            if (!s.contains(num.charAt(i))) {
                break;
            }
            i++;
        }

        char[] nums = num.toCharArray();
        if (num.charAt(i) < luckyNumbers[0]) {
            // 这种情况并非无解，减少位数
            if (i == 0) return "";

            // 输入"319"，减少位数
            int j = luckyNumbers.length - 1;
            while (j >= 0) {
                if (nums[i - 1] > (char) luckyNumbers[j]) break;
                j++;
            }
            nums[i - 1] = (char) luckyNumbers[j];
            for (int k = i; k < n; k++) {
                nums[k] = (char) luckyNumbers[luckyNumbers.length - 1];
            }
            return String.valueOf(nums);
        } else {
            int j = luckyNumbers.length - 1;
            while (j >= 0) {
                if (nums[i] > (char) luckyNumbers[j]) break;
                j--;
            }
            nums[i] = (char) luckyNumbers[j];
            for (int k = i + 1; k < n; k++) {
                nums[k] = (char) luckyNumbers[luckyNumbers.length - 1];
            }
            return String.valueOf(nums);
        }
    }

}
