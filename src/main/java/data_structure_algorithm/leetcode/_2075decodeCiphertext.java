package data_structure_algorithm.leetcode;

public class _2075decodeCiphertext {

    public static class Solution1 {

        /**
         模拟: O(N), O(N)
         */
        public String decodeCiphertext(String encodedText, int rows) {
            int cols = encodedText.length() / rows;
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                for (int x = 0, y = c; x < rows && y < cols; x++, y++) {
                    sb.append(encodedText.charAt(x * cols + y));
                }
            }
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }

    }

}
