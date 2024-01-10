package data_structure_algorithm.leetcode;

public class _705MyHashSet {


    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */

    public static class Solution1 {


        /**
         拉链法(相似题："706.设计哈希集合")：数组 + 链表
         Note：没有考虑loadFactor的扩容逻辑
         时间复杂度：平均O(1)，最坏O(N)
         空间复杂度：O(N)
         */
        class MyHashSet {

             class Node {
                int key;
                Node next;

                Node(int key) {
                    this.key = key;
                }
            }

            private final Node[] nodes;

            private final int CAPACITY = 1 << 10;

            private int hash(int key) {
                // x & (2^n - 1) = x % 2^n：任意正整数对2^n求余，等于该数与(2^n-1)按位与与
                return key & (CAPACITY - 1);
            }

            public MyHashSet() {
                nodes = new Node[CAPACITY];
            }

            public void add(int key) {
                // 根据key求hash值，找到所属链表
                int idx = hash(key);
                Node node = nodes[idx];

                // 从头开始遍历链表，如果key已存在，直接返回；如果key不存在，添加相应节点
                Node prev = null;
                while (node != null) {
                    if (node.key == key) {
                        return;
                    }
                    prev = node;
                    node = node.next;
                }

                Node newNode = new Node(key);
                if (prev == null) {
                    nodes[idx] = newNode;
                } else {
                    prev.next = newNode;
                }
            }

            public void remove(int key) {
                // 根据key求hash值，找到所属链表
                int idx = hash(key);
                Node node = nodes[idx];

                // 从头开始遍历链表，如果key存在，删除节点；否则什么也不做
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

            public boolean contains(int key) {
                // 根据key求hash值，找到所属链表
                int idx = hash(key);
                Node node = nodes[idx];

                // 从头开始遍历链表，如果key存在，返回true；遍历到最后仍然没找到相等的key，返回false
                while (node != null) {
                    if (node.key == key) {
                        return true;
                    }
                    node = node.next;
                }
                return false;
            }

        }


    }



    public static class Solution2 {

        /**
         数组(由于key的类型和范围都已知，所以可以考虑用一个boolean类型的数组来存储)
         时间复杂度：O(1)
         空间复杂度：O(数据范围)
         */
        class MyHashSet {

            boolean[] nodes;

            public MyHashSet() {
                nodes = new boolean[(int) 1e6 + 1];
            }

            public void add(int key) {
                nodes[key] = true;
            }

            public void remove(int key) {
                nodes[key] = false;
            }

            public boolean contains(int key) {
                return nodes[key];
            }

        }


    }

}
