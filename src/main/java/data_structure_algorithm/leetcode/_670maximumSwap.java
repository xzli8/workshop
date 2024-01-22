package data_structure_algorithm.leetcode;

public class _670maximumSwap {

    public static class Solution1 {

        /**
         (?)直接遍历：尽可能靠前的小数与尽可能靠后的大数交换
         时间复杂度：O(log^3N)
         空间复杂度：O(log^3N)
         */
        public int maximumSwap(int num) {
            char[] nums = String.valueOf(num).toCharArray();
            int n = nums.length;

            // 逆序遍历，找从结尾到当前数字的最大数字的下标
            int[] maxIndex = new int[n];
            int index = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (nums[i] > nums[index]) index = i;
                maxIndex[i] = index;
            }

            // 正序遍历，如果当前数字后有比当前数字更大的数，交换
            for (int i = 0; i < n; i++) {
                if (nums[i] != nums[maxIndex[i]]) {
                    char tmp = nums[i];
                    nums[i] = nums[maxIndex[i]];
                    nums[maxIndex[i]] = tmp;
                    break;
                }
            }
            return Integer.valueOf(new String(nums));
        }

    }



    public static class Solution2 {

        /**
         贪心：将后面较小的数字(数字相同选择尽可能靠后的数字)与前面较大的数字交换(数字相同选择尽可能靠前的数字)
         ref:https://leetcode.cn/problems/maximum-swap/solutions/2614470/yi-ci-bian-li-jian-ji-xie-fa-pythonjavac-c9b1/?envType=daily-question&envId=2024-01-22
         时间复杂度：O(logN)
         空间复杂度：O(logN)
         */
        public int maximumSwap(int num) {
            char[] nums = String.valueOf(num).toCharArray();
            int n = nums.length, maxIdx = n - 1, p = -1, q = 0;

            // 逆序遍历
            for (int i = n - 2; i >= 0; i--) {
                if (nums[i] > nums[maxIdx]) maxIdx = i; // 找目前为止最大的数字
                else if (nums[i] < nums[maxIdx]) {  // 更新待交换的下标p和q
                    p = i;
                    q = maxIdx;
                }
            }
            if (p == -1) return num;    // p没有更新，意味着num是降序的，直接返回

            // 交换p和q
            char tmp = nums[p];
            nums[p] = nums[q];
            nums[q] = tmp;
            return Integer.parseInt(new String(nums));
        }

    }

}
