package data_structure_algorithm.leetcode;

import java.util.PriorityQueue;

public class _295MedianFinder {

    public static class Solution1 {

        class MedianFinder {

            // 大顶堆
            private PriorityQueue<Integer> maxHeap;

            // 小顶堆
            private PriorityQueue<Integer> minHeap;

            public MedianFinder() {
                maxHeap = new PriorityQueue<>((a, b) -> b - a);
                minHeap = new PriorityQueue<>((a, b) -> a - b);
            }

            /**
             时间复杂度：O(logN)
             NOTE：大顶堆元素数量 <= 小顶堆元素数量 <= 大顶堆元素数量 + 1
             */
            public void addNum(int num) {
                if (minHeap.isEmpty() || num > minHeap.peek()) {
                    minHeap.offer(num);
                    if (minHeap.size() - maxHeap.size() > 1) {
                        maxHeap.offer(minHeap.poll());
                    }
                } else {
                    maxHeap.offer(num);
                    if (maxHeap.size() > minHeap.size()) {
                        minHeap.offer(maxHeap.poll());
                    }
                }
            }

            /**
             时间复杂度：O(1)
             */
            public double findMedian() {
                if (maxHeap.size() == minHeap.size()) {
                    return (maxHeap.peek() + minHeap.peek()) / 2.0;
                }
                return minHeap.peek();
            }

        }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

    }

}
