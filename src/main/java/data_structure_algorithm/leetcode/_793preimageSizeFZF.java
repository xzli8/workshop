package data_structure_algorithm.leetcode;

public class _793preimageSizeFZF {

    public static class Solution1 {

        /**
         二分查找：https://segmentfault.com/a/1190000038384301?u_atoken=44c8450e-8453-4893-bd6d-5e02474c7308&u_asession=01XvZvZG8xP9cemYBkFmE-aggr1qa3Mi3CSI-YsPi2JzdzCg-rldbHkRTsytPh5n3wBbkTOEgH_0RqODlfUY8VZdsq8AL43dpOnCClYrgFm6o&u_asig=05T7GiKxNhs3y2llzrHSslEtdHyGNfHhCf7gPpKA17RbJJ1VzKLJxhxpeXyQaNqltHmYFOb0Z-214M7eo07sbVu6G45o7GQxjgMk4TQQCsfR0iC1_XC6Wx31PZur9Vgwd5GWIls3qlumsJlfpCL-TeKkJgKCH6751ho_w-5EweQXWyuQQkWhQMb-2U3C2V72kcksmHjM0JOodanL5-M1Qs1duU5djGMoLYCtKRNBaHiVDzVmsSKcMQVzs5E3OYMZ1-U1gZwvIQ1X9ohpXFWkDoTqCRYBO0tmhXZMHcR1segjrY94r_LXIIil3Y3aVPRGAe&u_aref=AZuCnHVT3IgQilEcGXVAdFdtb3U%3D
         x越大，x末尾零的数量越多，单调递增
         需要首先计算阶乘末尾0的数量，参考“172.阶乘后的0”
         然后计算阶乘后零的数量等于k的上下界，上下界相减+1即可得到数量

         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int preimageSizeFZF(int k) {
            return (int) right(k) -  (int) left(k) + 1;
        }

        // 计算n!末尾0的数量
        private long trailingZeroes(long n) {
            long count = 0;
            for (long d = n; d / 5 > 0; d /= 5) {
                count += d / 5;
            }
            return count;
        }

        // 搜素左边界：找第一个大于等于target的值
        private long left(int target) {
            // 上下界根据题目所给的范围(0 <= k <= 10^9)来确定
            long left = 0, right = Long.MAX_VALUE;
            while (left <= right) {
                long mid = left + ((right - left) >> 1);
                if (trailingZeroes(mid) >= target) {
                    if (mid == 0 || trailingZeroes(mid - 1) < target) {
                        return mid;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }

        // 搜索右边界：找最后一个小于等于target的值
        private long right(int target) {
            long left = 0, right = Long.MAX_VALUE;
            while (left <= right) {
                long mid = left + ((right - left) >> 1);
                if (trailingZeroes(mid) <= target) {
                    if (trailingZeroes(mid + 1) > target) {
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

    }

}
