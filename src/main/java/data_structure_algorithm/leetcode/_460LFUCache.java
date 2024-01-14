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
         HashMap + TreeSet
         */
        class LFUCache {

            // 节点定义
            class Node {
                int key, val, cnt, time;
                public Node(int key, int val, int cnt, int time) {
                    this.key = key;
                    this.val = val;
                    this.cnt = cnt;
                    this.time = time;
                }
            }

            private int capacity, time = 0;
            private Map<Integer, Node> key2Node = new HashMap<>();
            private TreeSet<Node> set = new TreeSet<>((node1, node2) -> node1.cnt == node2.cnt ? node1.time - node2.time : node1.cnt - node2.cnt);

            public LFUCache(int capacity) {
                this.capacity = capacity;
            }

            public int get(int key) {
                Node node = key2Node.get(key);
                if (node == null) return -1;
                set.remove(node);
                node.cnt += 1;
                node.time = time++;
                set.add(node);
                key2Node.put(key, node);    // 这里可以更新也可以不更新(因为val无变化)
                return node.val;
            }

            public void put(int key, int value) {
                Node node = key2Node.get(key);
                if (node == null) {
                    if (set.size() == capacity) {
                        // 这里要注意一下remove的顺序，一定是先remove Map中的再remove Set中的
                        key2Node.remove(set.first().key);
                        set.remove(set.first());
                    }
                    node = new Node(key, value, 0, time++);
                    set.add(node);
                    key2Node.put(key, node);
                }
                else {
                    set.remove(node);
                    node.val = value;
                    node.cnt += 1;
                    node.time = time++;
                    set.add(node);
                    key2Node.put(key, node);    // 这里一定要更新(因为val有变化)
                }
            }
        }

    }


}
