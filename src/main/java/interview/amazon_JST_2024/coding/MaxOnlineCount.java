package interview.amazon_JST_2024.coding;

public class MaxOnlineCount {

    /**
     * Date: 2024.03.02 (OA)
     * Description: 某网站的每日的活跃用户有1亿人(10^9)，该网站的日志有记录每个用户的上线时间和下线时间，求该网站同一时刻的最多在线人数，时间取样精确到秒。
     * Link: (Similar case) https://blog.csdn.net/xupan_jsj/article/details/7774517
     */

    /**
     *  Solution: Difference Array
     *  TC: O(N)
     *  SC: O(1)
     */
    public int maxOnlineCount(int[] login, int[] logout) {
        int MAX_TIME = 86400, n = login.length;
        int[] counts = new int[MAX_TIME];
        Difference diff = new Difference(counts);
        for (int i = 0; i < n; i++) {
            diff.update(login[i], logout[i], 1);
        }

        int maxOnlineCount = 0;
        for (int i = 0; i < MAX_TIME; i++) {
            maxOnlineCount = Math.max(maxOnlineCount, counts[i]);
        }
        return maxOnlineCount;
    }

    public class Difference {

        private int[] diff;

        Difference(int[] nums) {
            int n = nums.length;
            diff = new int[n];
            diff[0] = nums[0];
            for (int i = 1; i < n; i++) {
                diff[i] = nums[i] - nums[i-1];
            }
        }

        public void update(int start, int end, int num) {
            diff[start] += num;
            if (end + 1 < diff.length) {
                diff[end + 1] -= num;
            }
        }

        public int[] result() {
            int[] res = new int[diff.length];
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i-1] + diff[i];
            }
            return res;
        }

    }

}
