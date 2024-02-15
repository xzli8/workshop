package data_structure_algorithm.leetcode;

public class _278firstBadVersion {

    class VersionControl {
        public boolean isBadVersion(int version) {
            return version % 2 == 0;    // 随便模拟一下判断逻辑
        }
    }

    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    public class Solution0 extends VersionControl {

        /**
         二分查找(找第一个符合条件的解)
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int firstBadVersion(int n) {
            int left = 1, right = n;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (isBadVersion(mid)) {
                    if (mid == 0 || !isBadVersion(mid - 1)) return mid;
                    else right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }

    }

}
