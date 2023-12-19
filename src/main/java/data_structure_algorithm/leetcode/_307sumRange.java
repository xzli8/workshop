package data_structure_algorithm.leetcode;

public class _307sumRange {


    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * obj.update(index,val);
     * int param_2 = obj.sumRange(left,right);
     */

    public static class Solution1 {

        class NumArray {

            /**
             树状数组（单点更新，区间查询）
             NOTE：元素下标从1开始
             */
            private int n;
            private int[] nums;
            private int[] tree;

            /**
             提取i二进制形式的最后一个为1的位
             input: 6 = 110(2)
             output: 2 = 010(2)
             */
            private int lowBit(int index) {
                return index & (-index);
            }

            /**
             将下标为index的元素加上val
             */
            private void add(int index, int val) {
                for (int i = index; i <= n; i += lowBit(i)) {
                    tree[i] += val;
                }
            }

            /**
             查询下标为index的前缀和
             */
            private int query(int index) {
                int sum = 0;
                for (int i = index; i > 0; i -= lowBit(i)) {
                    sum += tree[i];
                }
                return sum;
            }

            /**
             时间复杂度：O(NlogN)
             空间复杂度：O(N)
             */
            public NumArray(int[] nums) {
                n = nums.length;
                this.nums = nums;
                tree = new int[n+1];
                for (int i = 0; i < n; i++) {
                    add(i + 1, nums[i]);
                }
            }

            /**
             时间复杂度：O(logN)
             空间复杂度：O(1)
             */
            public void update(int index, int val) {
                add(index + 1, val - nums[index]);
                nums[index] = val;
            }

            /**
             时间复杂度：O(logN)
             空间复杂度：O(1)
             */
            public int sumRange(int left, int right) {
                return query(right + 1) - query(left);
            }

        }

    }

}
