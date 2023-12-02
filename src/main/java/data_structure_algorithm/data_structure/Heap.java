package data_structure_algorithm.data_structure;


import org.junit.Test;

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
        if (count == capacity) {
            return;
        }

        // 在数组末尾添加新元素
        nums[++count] = num;

        // 从下往上调整堆
        int i = count, tmp = nums[i];
        while ((i >> 1) > 0 && tmp > nums[i >> 1]) {
            nums[i] = nums[i >> 1];
            i = i >> 1;
        }
        nums[i] = tmp;
    }

    public int poll() {
        // 堆空了
        if (count == 0) return -1;

        // 取出堆顶元素(最大值)
        int max = nums[1];

        // 将最后一个元素放入堆顶
        nums[1] = nums[count--];

        // 从上往下调整堆
        int i = 1, tmp = nums[i];
        while (i << 1 <= count) {
            // 找较大的子节点
            int child = i << 1;
            if (child + 1 != count && nums[child + 1] > nums[child]) {
                child++;
            }

            if (nums[child] <= tmp) {
                break;
            }

            nums[i] = nums[child];
            i = child;
        }
        nums[i] = tmp;

        // 返回堆顶元素(最大值)
        return max;
    }

    public static class TestHeap {

        @Test
        public void test() {
            Heap heap = new Heap(5);
            heap.insert(5);
            heap.insert(2);
            heap.insert(4);
            heap.insert(1);
            heap.insert(3);
            heap.insert(6);

            System.out.println(heap.poll());
            System.out.println(heap.poll());
            System.out.println(heap.poll());
        }

    }


}
