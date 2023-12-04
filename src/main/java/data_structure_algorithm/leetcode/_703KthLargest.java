package data_structure_algorithm.leetcode;

import java.util.PriorityQueue;

public class _703KthLargest {

    public static class Solution1 {

        class KthLargest {

            /**
             小顶堆
             */
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int k;

            public KthLargest(int k, int[] nums) {
                this.k = k;
                for (int i = 0; i < nums.length; i++) {
                    pq.offer(nums[i]);
                    if (pq.size() > k) {
                        pq.poll();
                    }
                }
            }

            /**
             时间复杂度：log(k)
             */
            public int add(int val) {
                if (pq.size() < k) {
                    pq.offer(val);
                } else if (val > pq.peek()) {
                    pq.poll();
                    pq.offer(val);
                }
                return pq.peek();
            }

        }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

    }

}
