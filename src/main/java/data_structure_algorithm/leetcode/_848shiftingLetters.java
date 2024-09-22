package data_structure_algorithm.leetcode;

public class _848shiftingLetters {

    public static class Solution0 {

        /**
         traverse from end
         */
        public String shiftingLetters(String s, int[] shifts) {
            int n = s.length(), shift = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = n - 1; i >= 0; i--) {
                shift += shifts[i] % 26;
                int num = s.charAt(i) - 'a' + shift;
                sb.append((char) ((num % 26) + 'a'));
            }
            return sb.reverse().toString();
        }

    }

    public static class Solution1 {

        /**
         Difference Array
         */
        public String shiftingLetters(String s, int[] shifts) {
            // convert
            int n = s.length();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = s.charAt(i) - 'a';
            }

            // transform
            Difference diff = new Difference(nums);
            for (int i = 0; i < shifts.length; i++) {
                diff.update(0, i, shifts[i]);
            }
            nums = diff.result();

            // convert
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int num = ((nums[i] % 26) + 26) % 26;
                char c = (char) (num + 'a');
                sb.append(c);
            }
            return sb.toString();
        }

        class Difference {

            private int[] diff;

            public Difference(int[] nums) {
                int n = nums.length;
                diff = new int[n];
                diff[0] = nums[0];
                for (int i = 1; i < n; i++) {
                    diff[i] = nums[i] - nums[i - 1];
                }
            }

            public void update(int start, int end, int num) {
                diff[start] += num % 26;
                if (end + 1 < diff.length) {
                    diff[end + 1] -= num % 26;
                }
            }

            public int[] result() {
                int[] res = new int[diff.length];
                res[0] = diff[0];
                for (int i = 1; i < diff.length; i++) {
                    res[i] = res[i - 1] + diff[i];
                }
                return res;
            }

        }

    }

}
