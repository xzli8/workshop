package dsa.sort.advanced;

public class MergeSort {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 3, 5, 4, 2};

        new MergeSort().mergeSort(nums);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    /**
     *  归并排序：先将数组不断二分，再先让小数组有序，然后合并有序小数组得到有序大数组
     *      分治思想：从下往上
     *      两种写法：递归/迭代
     *      时间复杂度：最好/平均/最坏 O(NlogN)
     *      空间复杂度：O(N)
     *      稳定性：稳定
     */
    public void mergeSort(int[] nums) {
        mergeSortRec(nums);
        mergeSortIte(nums);
    }

    // 迭代形式：相比递归能节约递归栈的开销
    public void mergeSortIte(int[] nums) {
        int n = nums.length;
        int[] tmp = new int[n];
        // 分组大小
        for (int subsize = 2; subsize <= (n << 1); subsize <<= 1) {
            // 每组起点下标
            for (int i = 0; i < n; i += subsize) {
                int left = i, right = Math.min(i + subsize - 1, n - 1);
                int mid = left + ((right - left) >> 1);
                merge(nums, tmp, left, mid, right);
            }
        }
    }

    // 递归形式
    public void mergeSortRec(int[] nums) {
        int n = nums.length;
        int[] tmp = new int[n];
        mergeSortCore(nums, tmp, 0, n - 1);
    }

    public void mergeSortCore(int[] nums, int[] tmp, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + ((right - left) >> 1);
        mergeSortCore(nums, tmp, left, mid);
        mergeSortCore(nums, tmp, mid + 1, right);
        merge(nums, tmp, left, mid, right);
    }

    public void merge(int[] nums, int[] tmp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = tmp[j++];
            } else if (j == right + 1) {
                nums[k] = tmp[i++];
            } else if (tmp[i] < tmp[j]) {
                nums[k] = tmp[i++];
            } else {
                nums[k] = tmp[j++];
            }
        }
    }

}
