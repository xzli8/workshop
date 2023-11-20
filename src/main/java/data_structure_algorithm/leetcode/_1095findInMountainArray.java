package data_structure_algorithm.leetcode;

public class _1095findInMountainArray {

    /**
     * // This is MountainArray's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface MountainArray {
     *     public int get(int index) {}
     *     public int length() {}
     * }
     */

    interface MountainArray {

          int get(int index);

          int length();
    }


    /**
     二分：先找最大值，然后找目标值
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int n = mountainArr.length();

        // 用二分查找找最大值下标，最大值下标将数组分为单调递增和单调递减的两个单调区间
        int peekIndex = binarySearchPeek(mountainArr);

        // 如果目标值在前半段，二分查找其下标返回
        if (mountainArr.get(0) <= target && target <= mountainArr.get(peekIndex)) {
            int left = 0, right = peekIndex;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (mountainArr.get(mid) == target) {
                    if (mid == 0 || mountainArr.get(mid - 1) != target) {
                        return mid;
                    } else {
                        right = mid - 1;
                    }
                } else if (mountainArr.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        // 如果目标值在后半段，二分查找其下标返回
        if (target >= mountainArr.get(n - 1)) {
            int left = peekIndex + 1, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (mountainArr.get(mid) == target) {
                    if (mid == 0 || mountainArr.get(mid - 1) != target) {
                        return mid;
                    } else {
                        right = mid - 1;
                    }
                } else if (mountainArr.get(mid) < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        // 不存在，返回-1
        return -1;
    }

    private int binarySearchPeek(MountainArray mountainArr) {
        int n = mountainArr.length();
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int val = mountainArr.get(mid);
            if ((mid == 0 || mountainArr.get(mid - 1) < val)
                    && (mid == n - 1 || val > mountainArr.get(mid + 1))) {
                return mid;
            }

            if (mid == 0 || mountainArr.get(mid - 1) < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

}
