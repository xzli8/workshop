package dsa.sort.advanced;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 3, 5, 4, 2};

        new QuickSort().QuickSort(nums);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    /**
     *  快速排序：从数组中选取一个元素作为主元，每次让主元正确归位，即让主元左边元素都比它小，右边元素都比它大
     *      分治思想：从上往下
     *      两种写法：递归/迭代
     *      时间复杂度：最好O(NlogN)/平均O(NlogN)/最坏O(N^2)
     *      空间复杂度：O(1)
     *      稳定性：不稳定
     */
    public void QuickSort(int[] nums) {
        // 此处用洗牌随机打乱 / partition中随机选取主元，二选一即可
        shuffle(nums);

        quickSortIte(nums);
        quickSortRec(nums);
    }

    // 洗牌，将数组元素随机打乱，防止partition不均匀，增加时间复杂度
    public void shuffle(int[] nums) {
        int n = nums.length;
        Random random = new Random();
        for (int i = n - 1; i >= 0; i--) {
            int randIdx = random.nextInt(i + 1);
            swap(nums, randIdx, i);
        }
    }

    // 迭代形式：用栈模拟递归过程。能节约递归栈的空间开销，但同时显示的stack又需要消耗额外空间。
    public void quickSortIte(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        stack.push(nums.length - 1);
        while (!stack.isEmpty()) {
            int right = stack.pop(), left = stack.pop();
            int mid = partition(nums, left, right);
            if (left < mid) {
                stack.push(left);
                stack.push(mid - 1);
            }
            if (right > mid) {
                stack.push(mid + 1);
                stack.push(right);
            }
        }
    }

    // 递归形式
    public void quickSortRec(int[] nums) {
        int n = nums.length;
        quickSortCore(nums, 0, n - 1);
    }

    public void quickSortCore(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = partition(nums, left, right);
        quickSortCore(nums, left, mid - 1);
        quickSortCore(nums, mid + 1, right);
    }

    public int partition(int[] nums, int left, int right) {
        // 随机选取主元
        int ranIdx = new Random().nextInt(right - left + 1) + left;
        swap(nums, ranIdx, right);

        // pivot表示主元，i表示小于主元的区间的终点
        int pivot = nums[right], i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                if (j != i) swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
