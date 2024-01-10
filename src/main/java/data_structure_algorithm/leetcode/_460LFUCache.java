package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class _460LFUCache {

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static class Solution1 {

        /**
         哈希表+平衡二叉树
         */
        class LFUCache {

            class Node implements Comparable<Node> {
                int cnt, time, key, value;

                Node(int cnt, int time, int key, int value) {
                    this.cnt = cnt;
                    this.time = time;
                    this.key = key;
                    this.value = value;
                }

                public int compareTo(Node rhs) {
                    return this.cnt == rhs.cnt ? this.time - rhs.time : this.cnt - rhs.cnt;
                }

                // // 不重写hashCode和equals也可以，因为TreeMap底层是红黑树，其查询元素只通过compareTo方法
                // public int hashCode() {
                //     return cnt * 1000000007 + time;
                // }

                // public boolean equals(Object obj) {
                //     if (this == obj) {
                //         return true;
                //     }
                //     if (obj instanceof Node) {
                //         Node rhs = (Node) obj;
                //         return this.cnt == rhs.cnt && this.time == rhs.time;
                //     }
                //     return false;
                // }
            }

            int capacity, time;
            Map<Integer, Node> cache;
            TreeSet<Node> set;

            public LFUCache(int capacity) {
                this.capacity = capacity;
                this.time = 0;
                cache = new HashMap<>();
                set = new TreeSet<>();
            }

            public int get(int key) {
                Node node = cache.get(key);
                if (node == null) {
                    return -1;
                }

                // 更新集合
                set.remove(node);
                node.cnt += 1;
                node.time = ++time;
                set.add(node);
                // 更新缓存(这一步必须得有？因为val, time的变化会引起hashCode的变化？)
                // cache.put(key, node);
                return node.value;
            }

            public void put(int key, int value) {
                Node node = cache.get(key);
                if (node == null) {
                    // 如果缓存达到上限，从集合和哈希表中删除最近最久未使用的节点
                    // 这里用cache.size()代替set.size()也可以
                    if (set.size() == capacity) {
                        cache.remove(set.first().key);
                        set.remove(set.first());
                    }
                    node = new Node(1, ++time, key, value);
                    cache.put(key, node);
                    set.add(node);
                } else {
                    // 这里与get()类似
                    set.remove(node);
                    node.cnt += 1;
                    node.time = ++time;
                    node.value = value;
                    set.add(node);
                    cache.put(key, node);
                }
            }
        }

    }

}
