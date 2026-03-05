package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1166FileSystem {

    /**
     * https://leetcode.doocs.org/lc/1166/
     */

    public static class Solution1 {

        public class FileSystem {

            private class Node {
                int val;
                Map<String, Node> children = new HashMap<>();
                Node (int val) {
                    this.val = val;
                }
            }

            private final Node root = new Node(-1);

            public boolean createPath(String path, int value) {
                List<String> segs = split(path);
                if (segs.isEmpty()) return false;

                Node node = root;
                for (int i = 0; i < segs.size() - 1; i++) {
                    node = node.children.get(segs.get(i));
                    if (node == null) return false;
                }

                String last = segs.get(segs.size() - 1);
                if (node.children.containsKey(last))  return false;
                node.children.put(last, new Node(value));
                return true;
            }

            public int get(String path) {
                Node node = root;
                for (String seg : split(path)) {
                    node = node.children.get(seg);
                    if (node == null) return -1;
                }
                return node.val;
            }

            private List<String> split(String path) {
                List<String> segs = new ArrayList<>();
                for (String s : path.split("/")) {
                    if (s.isEmpty()) continue;
                    segs.add(s);
                }
                return segs;
            }

        }

    }


}
