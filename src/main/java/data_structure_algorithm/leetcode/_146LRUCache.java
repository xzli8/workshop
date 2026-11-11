package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _146LRUCache {


    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static class Solution1 {

        /**
         双链表 + 哈希表
         */
        class LRUCache {

            // 双链表节点定义
            class Node {
                int key, val;
                Node prev, next;

                public Node() {}

                public Node(int key, int val) {
                    this.key = key;
                    this.val = val;
                }
            }

            private int capacity;
            private Node head, tail;
            private Map<Integer, Node> key2Node = new HashMap<>();

            public LRUCache(int capacity) {
                this.capacity = capacity;
                this.head = new Node();
                this.tail = new Node();
                head.next = tail;
                tail.prev = head;
            }

            public int get(int key) {
                Node node = key2Node.get(key);
                if (node == null) return -1;
                moveToHead(node);
                return node.val;
            }

            public void put(int key, int value) {
                Node node = key2Node.get(key);
                if (node != null) {
                    node.val = value;
                    moveToHead(node);
                } else {
                    node = new Node(key, value);
                    key2Node.put(key, node);
                    addToHead(node);
                    if (key2Node.size() > capacity) {
                        Node eldest = removeNode(tail.prev);
                        key2Node.remove(eldest.key);
                    }
                }
            }

            private void addToHead(Node node) {
                head.next.prev = node;
                node.next = head.next;
                head.next = node;
                node.prev = head;
            }

            private Node removeNode(Node node) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                node.prev = null;
                node.next = null;
                return node;
            }

            private void moveToHead(Node node) {
                removeNode(node);
                addToHead(node);
            }
        }

    }


    public static class Solution2 {

        /**
         LinkedHashMap(accessOrder = true) + removeEldestEntry
         - accessOrder = true：get/put 都会把节点移到链表尾部（尾部最新，头部最旧）
         - removeEldestEntry：每次 put 后回调，返回 true 就删掉头部最旧的节点
         - 面试时先写 Solution1（考察手写双链表），再提这个作为工程写法
         */
        class LRUCache extends java.util.LinkedHashMap<Integer, Integer> {
            private final int capacity;

            public LRUCache(int capacity) {
                super(capacity, 0.75f, true);
                this.capacity = capacity;
            }

            public int get(int key) {
                return super.getOrDefault(key, -1);
            }

            public void put(int key, int value) {
                super.put(key, value);
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        }

    }


    /**
     追问1：线程安全
     - 问题：HashMap 和双链表都不是线程安全的
         - 并发 put 会破坏链表指针（节点丢失、成环）
         - size 判断和淘汰不是原子的（多淘汰或少淘汰）
     - 读写锁(ReentrantReadWriteLock)为什么不行
         - get 也要 moveToHead（修改链表），本质是写操作，只能拿写锁，退化成互斥锁
     - 方案对比
         - Solution3 整体加锁：一把锁保护 map + 链表，正确、简单；高并发下所有操作串行
         - Solution4 分段锁：按 key 的 hash 分成 N 段，每段一个独立 LRU 和一把锁；并发度约 N 倍；代价是淘汰只在段内近似 LRU（不是全局严格 LRU）
         - ConcurrentHashMap + ConcurrentLinkedDeque：不推荐（deque.remove(node) 是 O(N)，且 map 和 deque 两步操作之间不原子）
         - 工程实践：Caffeine
             - ConcurrentHashMap 存数据，读操作不加锁
             - 访问记录先写入环形缓冲区(ring buffer)，再批量在锁内重放、调整淘汰顺序（读操作不争锁）
             - 淘汰算法是 W-TinyLFU（LRU + 频率过滤），命中率比纯 LRU 高
     */
    public static class Solution3 {

        /**
         整体加锁：Solution1 + ReentrantLock
         - get 和 put 都在同一把锁内完成（读也改链表）
         - 用 ReentrantLock 而不是 synchronized：可以 tryLock 超时、可中断；两者都可以，说清楚即可
         */
        class LRUCache {
            class Node {
                int key, val;
                Node prev, next;
                Node() {}
                Node(int key, int val) { this.key = key; this.val = val; }
            }

            private final int capacity;
            private final Node head = new Node(), tail = new Node();
            private final Map<Integer, Node> key2Node = new HashMap<>();
            private final java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();

            public LRUCache(int capacity) {
                this.capacity = capacity;
                head.next = tail;
                tail.prev = head;
            }

            public int get(int key) {
                lock.lock();
                try {
                    Node node = key2Node.get(key);
                    if (node == null) return -1;
                    moveToHead(node);
                    return node.val;
                } finally {
                    lock.unlock();
                }
            }

            public void put(int key, int value) {
                lock.lock();
                try {
                    Node node = key2Node.get(key);
                    if (node != null) {
                        node.val = value;
                        moveToHead(node);
                        return;
                    }
                    node = new Node(key, value);
                    key2Node.put(key, node);
                    addToHead(node);
                    if (key2Node.size() > capacity) {
                        Node eldest = tail.prev;
                        removeNode(eldest);
                        key2Node.remove(eldest.key);
                    }
                } finally {
                    lock.unlock();
                }
            }

            public int size() {
                lock.lock();
                try {
                    return key2Node.size();
                } finally {
                    lock.unlock();
                }
            }

            private void addToHead(Node node) {
                node.prev = head;
                node.next = head.next;
                head.next.prev = node;
                head.next = node;
            }

            private void removeNode(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = null;
                node.next = null;
            }

            private void moveToHead(Node node) {
                removeNode(node);
                addToHead(node);
            }
        }

    }


    public static class Solution4 {

        /**
         分段锁：N 个 Solution3 的 LRUCache，按 key 的 hash 路由
         - 段数取 2 的幂，用 hash & (N - 1) 取段（比取模快）
         - hash 先做扰动 h ^ (h >>> 16)，避免低位分布不均（同 HashMap）
         - 每段容量 = ceil(capacity / N)，总容量可能略大于 capacity
         - 取舍：并发度约 N 倍；淘汰只看本段，热点 key 集中的段会过早淘汰（近似 LRU）
         */
        class LRUCache {
            private final Solution3.LRUCache[] segments;
            private final int mask;

            public LRUCache(int capacity, int concurrencyLevel) {
                int n = 1;
                while (n < concurrencyLevel) n <<= 1;
                this.mask = n - 1;
                this.segments = new Solution3.LRUCache[n];
                int perSegment = (capacity + n - 1) / n;
                Solution3 outer = new Solution3();
                for (int i = 0; i < n; i++) {
                    segments[i] = outer.new LRUCache(perSegment);
                }
            }

            public int get(int key) {
                return segmentFor(key).get(key);
            }

            public void put(int key, int value) {
                segmentFor(key).put(key, value);
            }

            public int size() {
                int total = 0;
                for (Solution3.LRUCache s : segments) total += s.size();
                return total;
            }

            private Solution3.LRUCache segmentFor(int key) {
                int h = Integer.hashCode(key);
                h ^= (h >>> 16);
                return segments[h & mask];
            }
        }

    }


    /**
     追问2：分布式 LRU（缓存数据量超过单机内存，或多个服务实例要共享缓存）
     - 分片
         - 一致性哈希：key 映射到哈希环，顺时针找到第一个节点；每个物理节点对应多个虚拟节点（分布均匀）
         - 增删节点只迁移相邻区间的 key（取模分片会导致几乎全部 key 重新映射）
         - 每个节点内部是本地 LRU（即 Solution3/4），全局只是近似 LRU（和分段锁同理）
     - 直接用 Redis
         - 配置 maxmemory + maxmemory-policy allkeys-lru（只淘汰带过期时间的用 volatile-lru）
         - Redis 是近似 LRU：每个 key 记录 24 bit 的最后访问时钟；内存超限时随机采样 maxmemory-samples（默认 5）个 key，淘汰其中最久未访问的
         - 为什么不维护全局链表：每次访问都改链表，额外内存和 CPU 开销大；采样在样本数 10 时已接近真实 LRU
         - Redis Cluster：16384 个哈希槽(slot)分到各主节点，CRC16(key) % 16384 定位槽
     - 高可用
         - 每个分片主从复制，主节点故障由哨兵(Sentinel)或 Cluster 自动切换到从节点
         - 异步复制：切换时可能丢失少量最近写入（缓存场景通常可以接受）
     - 一致性（缓存和数据库）
         - Cache-Aside：读先查缓存，miss 读库再回填；写先更新数据库，再删除缓存（不是更新缓存）
         - 先删缓存再更新库的问题：并发读会在两步之间把旧值回填
         - 先更新库再删缓存仍有极小窗口；可补延迟双删，或订阅 binlog 异步删除缓存（删除失败重试）
     - 两级缓存（本地 LRU + Redis）
         - 热点 key 先查进程内 LRU（减少网络往返，避免单个 Redis 分片被打爆）
         - 失效通知：数据变更时通过 Redis Pub/Sub 或 MQ 广播，各实例删除本地副本；本地缓存同时设短 TTL 兜底
     - 常见三问
         - 穿透（查不存在的 key）：缓存空值，或布隆过滤器
         - 击穿（热点 key 过期瞬间大量请求打到库）：互斥锁只让一个请求回源，或逻辑过期 + 异步刷新
         - 雪崩（大量 key 同时过期，或 Redis 宕机）：TTL 加随机值；多级缓存；限流降级
     */


    public static void main(String[] args) throws InterruptedException {
        // 并发测试：多线程随机 get/put，检查最终 size 不超过容量、链表未损坏
        final int capacity = 100, threads = 8, opsPerThread = 100_000;

        Solution3.LRUCache locked = new Solution3().new LRUCache(capacity);
        Solution4.LRUCache segmented = new Solution4().new LRUCache(capacity, 4);

        Thread[] ts = new Thread[threads];
        for (int t = 0; t < threads; t++) {
            ts[t] = new Thread(() -> {
                java.util.concurrent.ThreadLocalRandom r = java.util.concurrent.ThreadLocalRandom.current();
                for (int i = 0; i < opsPerThread; i++) {
                    int key = r.nextInt(1000);
                    if (r.nextBoolean()) {
                        locked.put(key, key);
                        segmented.put(key, key);
                    } else {
                        int v1 = locked.get(key), v2 = segmented.get(key);
                        if ((v1 != -1 && v1 != key) || (v2 != -1 && v2 != key)) {
                            throw new IllegalStateException("wrong value for key " + key);
                        }
                    }
                }
            });
            ts[t].start();
        }
        for (Thread t : ts) t.join();

        System.out.println("locked size = " + locked.size() + " (<= " + capacity + ")");
        System.out.println("segmented size = " + segmented.size() + " (<= " + capacity + ")");
    }

}
