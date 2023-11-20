package data_structure_algorithm.data_structure;


/**
 *  大顶堆
 */
public class Heap {

    private int capacity;

    private int count;

    private int[] nums;

    public Heap(int capacity) {
        this.capacity = capacity;
        count = 0;
        nums = new int[capacity + 1];
    }

    public void insert(int num) {
        // 堆满了
        if (count >= capacity) {
            return;
        }

        // 在数组末尾添加新元素
        nums[++count] = num;

        // 调整堆(上滤)
        int i = count, tmp = nums[i];
        while ((i >> 1) > 0 && nums[i] > nums[i >> 1]) {
            nums[i >> 1] = tmp;
            i = i >> 1;
        }
        nums[i] = tmp;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }



}
