package data_structure_algorithm.algorithm.sort.advanced;

public class HeapSort {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 3, 5, 4, 2};

        new HeapSort().heapSort(nums);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }


    /**
     *  堆排序：先建堆，将所有非叶子节点正确归位；然后将堆顶的最大元素交换到无序区间的末尾，将堆大小减1，继续调整堆～
     *      时间复杂度：O(NlogN)
     *      空间复杂度：O(1)
     *      稳定性：不稳定
     */
    public void heapSort(int[] nums) {
        // 建堆：从最后一个非叶子节点开始。时间复杂度：O(N)
        int n = nums.length;
        for (int i = n >> 1; i >= 0; i--) {
            adjust(nums, n, i);
        }

        // 排序
        for (int i = n - 1; i >= 0; i--) {
            swap(nums, i, 0);
            adjust(nums, i, 0);
        }
    }

    // 计算下标为i的节点的左子节点的下标
    private int leftChild(int i) {
        // 这里可以用位运算来加速，等价于：2 * i + 1
        // 不同与一般实现堆时的做法（将下标为0的元素空出来，这样左右子节点下标分别为2i和2i+1，左子节点少一次加法计算）
        // 这里因为是排序，下标为0的元素无法空出来，所以左子节点计算是：2i+1
        return (i << 1) + 1;
    }

    // 调整堆(下滤)。堆大小为n，待调整的元素下标为i
    private void adjust(int[] nums, int n, int i) {
        int tmp = nums[i];
        while (leftChild(i) < n) {
            // find larger child
            int child = leftChild(i);
            if (child + 1 < n && nums[child + 1] > nums[child]) {
                child++;
            }

            if (nums[child] < tmp) {
                break;
            } else {
                nums[i] = nums[child];
                i = child;
            }
        }
        nums[i] = tmp;
    }

    // 交换两个数组元素
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
