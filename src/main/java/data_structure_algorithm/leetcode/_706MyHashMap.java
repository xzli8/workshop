package data_structure_algorithm.leetcode;

public class _706MyHashMap {


    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */

    public static class Solution1 {

        /**
         拉链法(相似题："705.设计哈希集合")：数组 + 链表
         NOTE：没有考虑loadFactor的扩容逻辑
         时间复杂度：平均O(1)，最坏O(N)
         空间复杂度：O(N)
         */
        class MyHashMap {

            class Node {
                int key;
                int value;
                Node next;

                Node (int key, int value) {
                    this.key = key;
                    this.value = value;
                }
            }

            private final Node[] nodes;

            private final int CAPACITY = 1 << 10;

            private int hash(int key) {
                // x & (2^n - 1) = x % 2^n：任意正整数对2^n求余，等于该数与(2^n-1)按位与
                return key & (CAPACITY - 1);
            }

            public MyHashMap() {
                nodes = new Node[CAPACITY];
            }

            public void put(int key, int value) {
                // 根据key计算数组下标，找到相应链表
                int idx = hash(key);
                Node node = nodes[idx];

                // 遍历链表，如果有key相等的，更新；如果没有key相等的，插入
                Node prev = null;
                while (node != null) {
                    if (node.key == key) {
                        node.value = value;
                        return;
                    }
                    prev = node;
                    node = node.next;
                }

                // 新增node
                Node newNode = new Node(key, value);
                if (prev == null) {
                    nodes[idx] = newNode;
                } else {
                    prev.next = newNode;
                }
            }

            public int get(int key) {
                // 根据key计算数组下标，找到相应链表
                int idx = hash(key);
                Node node = nodes[idx];

                // 遍历链表，如果有key相等的，返回node.value；如果没有key相等的，返回-1
                while (node != null) {
                    if (node.key == key) {
                        return node.value;
                    }
                    node = node.next;
                }
                return -1;
            }

            public void remove(int key) {
                // 根据key计算数组下标，找到相应链表
                int idx = hash(key);
                Node node = nodes[idx];

                // 遍历链表，如果有key相等的，删除；如果没有key相等的，忽略
                Node prev = null;
                while (node != null) {
                    if (node.key == key) {
                        if (prev == null) {
                            nodes[idx] = node.next;
                        } else {
                            prev.next = node.next;
                        }
                        return;
                    }
                    prev = node;
                    node = node.next;
                }
            }

        }


    }

}
