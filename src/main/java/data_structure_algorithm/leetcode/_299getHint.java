package data_structure_algorithm.leetcode;

public class _299getHint {

    public static class Solution1 {

        /**
         哈希表:O(N), O(1)
         ref: https://leetcode.doocs.org/lc/299/#_2
         */
        public String getHint(String secret, String guess) {
            int[] cnt1 = new int[10], cnt2 = new int[10];
            int x = 0, y = 0;
            for (int i = 0; i < secret.length(); i++) {
                int n1 = secret.charAt(i) - '0', n2 = guess.charAt(i) - '0';
                if (n1 == n2) {
                    x++;
                } else {
                    cnt1[n1]++;
                    cnt2[n2]++;
                }
            }
            for (int i = 0; i < 10; i++) {
                y += Math.min(cnt1[i], cnt2[i]);
            }
            return String.format("%dA%dB", x, y);
        }

    }

    public static class Solution2 {

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
