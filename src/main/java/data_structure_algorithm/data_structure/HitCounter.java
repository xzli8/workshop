package data_structure_algorithm.data_structure;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * Hit Counter：统计过去 5 分钟（300 秒）内的点击数
 *
 * ref: https://leetcode.cn/problems/design-hit-counter/ (LeetCode 362)
 *
 * 接口
 * - hit(timestamp)：timestamp 单位是秒，调用时单调不减
 * - getHits(timestamp)：返回 (timestamp - 300, timestamp] 内的点击数
 *
 * 五种写法
 * - QueueHitCounter：队列存每次点击，查询时弹出过期的；内存随点击量增长
 * - MergedQueueHitCounter：队列存 (timestamp, count)，同一秒合并；最多 300 个元素
 * - BucketHitCounter：300 个环形桶，每秒一个；内存固定，追问"每秒上百万次点击"时用这个
 * - SortedMapHitCounter：时间戳乱序时用，TreeMap 按时间排序
 * - ConcurrentHitCounter：多线程版本
 *
 * 扩展追问
 * - 时间戳乱序：桶方案天然支持（只要还在窗口内）；队列方案要改成按时间排序的结构（SortedMapHitCounter）
 * - 窗口改成 1 小时、精度 1 秒：3600 个桶；精度可以降到 10 秒一个桶，用精度换内存
 * - 分布式：每台机器本地计数，定期汇总；或 Redis 按秒 INCR key（hits:{second}）+ TTL，查询时 MGET 300 个 key
 */
public class HitCounter {

    private static final int WINDOW = 300;

    /**
     * 队列：存每次点击的时间戳
     * - hit O(1)；getHits 均摊 O(1)（每个元素只入队出队一次）
     * - 问题：每秒点击很多时，队列里存了大量重复时间戳
     *     - 优化：队列元素存 (timestamp, count)，同一秒合并成一个
     */
    public static class QueueHitCounter {

        private final Deque<Integer> q = new ArrayDeque<>();

        public void hit(int timestamp) {
            q.offerLast(timestamp);
        }

        public int getHits(int timestamp) {
            // 弹出窗口外的点击
            while (!q.isEmpty() && q.peekFirst() <= timestamp - WINDOW) {
                q.pollFirst();
            }
            return q.size();
        }
    }

    /**
     * 队列存 (timestamp, count)，同一秒的点击合并成一个元素
     * - 时间戳单调不减，所以同一秒的点击一定连续到达，只需要看队尾
     * - total 维护窗口内的总数：hit 时加，弹出过期元素时减；getHits 不用遍历
     * - 队列最多 300 个元素，和点击量无关
     */
    public static class MergedQueueHitCounter {

        // 元素为 {timestamp, count}
        private final Deque<int[]> q = new ArrayDeque<>();

        private int total = 0;

        public void hit(int timestamp) {
            int[] last = q.peekLast();
            if (last != null && last[0] == timestamp) {
                last[1]++;
            } else {
                q.offerLast(new int[]{timestamp, 1});
            }
            total++;
        }

        public int getHits(int timestamp) {
            while (!q.isEmpty() && q.peekFirst()[0] <= timestamp - WINDOW) {
                total -= q.pollFirst()[1];
            }
            return total;
        }
    }

    /**
     * 时间戳乱序：TreeMap<timestamp, count> 按时间排序
     * - 假设：点击可能迟到（时间戳比之前的小），查询时间单调不减
     * - hit：按时间戳累加，O(log n)；已经比最新时间早了一个窗口以上的点击直接丢弃
     * - getHits：取 (timestamp - 300, timestamp] 的子区间求和，n 最多 300，O(300)
     * - 清理：用见过的最大时间戳把窗口外的旧 key 删掉，保证最多 300 个 key
     * - 对比队列：队列只能在队尾追加，迟到的点击插不到中间去
     */
    public static class SortedMapHitCounter {

        private final TreeMap<Integer, Integer> counts = new TreeMap<>();

        private int latest = Integer.MIN_VALUE;

        public void hit(int timestamp) {
            latest = Math.max(latest, timestamp);
            // 迟到太久，已经不在任何未来查询的窗口里
            if (timestamp <= latest - WINDOW) return;
            counts.merge(timestamp, 1, Integer::sum);
            evict();
        }

        public int getHits(int timestamp) {
            latest = Math.max(latest, timestamp);
            evict();
            int total = 0;
            // (timestamp - 300, timestamp]
            for (int c : counts.subMap(timestamp - WINDOW, false, timestamp, true).values()) {
                total += c;
            }
            return total;
        }

        private void evict() {
            // 删掉 <= latest - 300 的 key
            counts.headMap(latest - WINDOW, true).clear();
        }
    }

    /**
     * 环形桶：每秒一个桶，桶号 = timestamp % 300
     * - times[i] 记录这个桶当前属于哪一秒，counts[i] 是这一秒的点击数
     * - hit：桶里存的是旧的一秒，就重置；是同一秒，就累加
     * - getHits：累加还在窗口内的桶，O(300)
     * - 内存固定 O(300)，和点击量无关
     */
    public static class BucketHitCounter {

        private final int[] times = new int[WINDOW];

        private final int[] counts = new int[WINDOW];

        public void hit(int timestamp) {
            int i = timestamp % WINDOW;
            if (times[i] != timestamp) {
                // 桶里是 300 秒以前的旧数据，复用这个桶
                times[i] = timestamp;
                counts[i] = 1;
            } else {
                counts[i]++;
            }
        }

        public int getHits(int timestamp) {
            int total = 0;
            for (int i = 0; i < WINDOW; i++) {
                if (timestamp - times[i] < WINDOW) {
                    total += counts[i];
                }
            }
            return total;
        }
    }

    /**
     * 多线程版本：在环形桶上加锁
     * - hit 的"判断是否同一秒 + 重置或累加"必须是原子的，否则两个线程同时重置同一个桶会丢计数
     * - 最简单：两个方法都 synchronized
     * - 竞争激烈时可以按桶加锁，或者把 (time, count) 打包成一个 long，用 AtomicLongArray + CAS 无锁更新
     */
    public static class ConcurrentHitCounter {

        private final BucketHitCounter delegate = new BucketHitCounter();

        public synchronized void hit(int timestamp) {
            delegate.hit(timestamp);
        }

        public synchronized int getHits(int timestamp) {
            return delegate.getHits(timestamp);
        }
    }

    public static class TestHitCounter {

        // LeetCode 362 示例
        @Test
        public void example() {
            QueueHitCounter q = new QueueHitCounter();
            BucketHitCounter b = new BucketHitCounter();
            int[] hits = {1, 2, 3};
            for (int t : hits) {
                q.hit(t);
                b.hit(t);
            }
            Assert.assertEquals(3, q.getHits(4));
            Assert.assertEquals(3, b.getHits(4));
            q.hit(300);
            b.hit(300);
            Assert.assertEquals(4, q.getHits(300));
            Assert.assertEquals(4, b.getHits(300));
            // 第 1 秒的点击过期
            Assert.assertEquals(3, q.getHits(301));
            Assert.assertEquals(3, b.getHits(301));
        }

        @Test
        public void mergedQueue() {
            MergedQueueHitCounter m = new MergedQueueHitCounter();
            for (int i = 0; i < 1000; i++) m.hit(1);
            m.hit(2);
            m.hit(300);
            Assert.assertEquals(1002, m.getHits(300));
            // 第 1 秒的 1000 次一起过期
            Assert.assertEquals(2, m.getHits(301));
            Assert.assertEquals(1, m.getHits(301 + 1));
            Assert.assertEquals(0, m.getHits(600));
        }

        @Test
        public void outOfOrderHits() {
            SortedMapHitCounter s = new SortedMapHitCounter();
            s.hit(100);
            s.hit(50);   // 迟到
            s.hit(80);   // 迟到
            s.hit(100);
            Assert.assertEquals(4, s.getHits(100));
            // 窗口 (49, 349]，第 50 秒还在
            Assert.assertEquals(4, s.getHits(349));
            // 窗口 (50, 350]，第 50 秒过期
            Assert.assertEquals(3, s.getHits(350));
            // 迟到超过一个窗口，丢弃
            s.hit(10);
            Assert.assertEquals(3, s.getHits(350));
        }

        @Test
        public void manyHitsInSameSecondAndBucketReuse() {
            BucketHitCounter b = new BucketHitCounter();
            for (int i = 0; i < 1000; i++) b.hit(10);
            Assert.assertEquals(1000, b.getHits(10));
            // 310 和 10 落在同一个桶，旧数据被覆盖
            b.hit(310);
            Assert.assertEquals(1, b.getHits(310));
        }

        @Test
        public void concurrentHitsAreNotLost() throws InterruptedException {
            ConcurrentHitCounter c = new ConcurrentHitCounter();
            Thread[] threads = new Thread[8];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < 10_000; j++) c.hit(100);
                });
                threads[i].start();
            }
            for (Thread t : threads) t.join();
            Assert.assertEquals(80_000, c.getHits(100));
        }
    }
}
