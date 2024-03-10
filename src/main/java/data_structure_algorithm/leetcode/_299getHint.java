package data_structure_algorithm.leetcode;

public class _299getHint {

    public static class Solution1 {

        /**
         哈希计数
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public String getHint(String secret, String guess) {
            int[] secretNotMatchedCounts = new int[10], guessNotMatchedCounts = new int[10];
            int matchedCount = 0;
            for (int i = 0; i < secret.length(); i++) {
                char sc = secret.charAt(i), gc = guess.charAt(i);
                if (sc == gc) matchedCount++;
                else {
                    secretNotMatchedCounts[sc - '0']++;
                    guessNotMatchedCounts[gc - '0']++;
                }
            }
            int notMatchedCounts = 0;
            for (int i = 0; i <= 9; i++) {
                notMatchedCounts += Math.min(secretNotMatchedCounts[i], guessNotMatchedCounts[i]);
            }

            StringBuilder sb = new StringBuilder();
            sb.append(matchedCount).append("A").append(notMatchedCounts).append("B");
            return sb.toString();
        }

    }

}
