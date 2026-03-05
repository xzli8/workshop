package interview.scopely_history;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static com.oracle.util.Checksums.update;

public class nodeUpdate {

    /**
     ## Task

     Implement:
     ```java
     Node update(Node root, String path, Function<String, String> transform)
     ```

     - `path` is dot-separated, e.g. `"user.address.city"` — every segment matches a child by name, the last segment must resolve to a **leaf node**
     - Apply `transform` to that leaf's value and return a new tree with the change propagated back to the root
     - **The original tree must not be mutated**
     - Return the original `root` unchanged if: any path segment is missing, the final node is not a leaf, or `transform` returns `null`

     ## Example

     ```
     root:
     user:
     name: "Alice"        ← leaf
     address:
     city: "nyc"        ← leaf
     zip:  "10001"      ← leaf

     update(root, "user.address.city", s -> s.toUpper

     result:
     user:
     name: "Alice"        ← same object as origin
     address:             ← NEW node
     city: "NYC"        ← NEW leaf (was "nyc")
     zip:  "10001"      ← same object as original
     ```
     **/

    public static class Solution1 {

        static class Node {
            final String name;
            final List<Node> children;  // null for leaf nodes
            final String value;         // non-null only for leaf nodes

            private Node(String name, List<Node> children, String value) {
                this.name = name;
                this.children = children;
                this.value = value;
            }

            static Node of(String name, List<Node> children) {
                return new Node(name, Collections.unmodifiableList(new ArrayList<>(children)), null);
            }

            static Node of(String name, String value) {
                return new Node(name, null, value);
            }

            // Returns a new Node with oldChild replaced by newChild — does NOT mutate this node
            Node withChild(Node oldChild, Node newChild) {
                List<Node> newChildren = new ArrayList<>();
                for (Node child : children) {
                    newChildren.add(child == oldChild ? newChild : child);
                }
                return new Node(name, Collections.unmodifiableList(newChildren), null);
            }

            // Returns a new Node with value replaced — does NOT mutate this node
            Node withValue(String newValue) {
                return new Node(name, null, newValue);
            }

            @Override
            public String toString() {
                if (value != null) return "Leaf(" + name + "=" + value + ")";
                return "Node(" + name + ", " + children + ")";
            }
        }

        // To be implemented
        static Node update(Node root, String path, Function<String, String> transform) {
            Node result = updateHelper(root, path.split("\\."), 0, transform);
            return result == null ? root : result;   // 任何失败 → 原样返回 root (同一个对象)
        }

        // 返回: node 应用更新后的「新版本」; 无法应用时返回 null
        static Node updateHelper(Node node, String[] segs, int i, Function<String, String> transform) {
            if (node.children == null) return null;   // 还要往下走, 但当前是叶子 → 路径不存在

            // 1. 在孩子里找匹配 segs[i] 的那个
            Node matched = null;
            for (Node child : node.children) {
                if (child.name.equals(segs[i])) {
                    matched = child;
                    break;
                }
            }
            if (matched == null) return null;         // segment 缺失

            boolean isLast = (i == segs.length - 1);
            if (isLast) {
                // 2a. 最后一段: 必须是叶子
                if (matched.children != null) return null;          // 不是叶子
                String newValue = transform.apply(matched.value);
                if (newValue == null) return null;                  // transform 返回 null
                return node.withChild(matched, matched.withValue(newValue));
            } else {
                // 2b. 中间段: 递归下去, 再把新孩子接回来
                Node newChild = updateHelper(matched, segs, i + 1, transform);
                if (newChild == null) return null;                  // 下层作废 → 本层也作废
                return node.withChild(matched, newChild);
            }
        }

        public static void main(String[] args) {
            Node root = Node.of("", Arrays.asList(
                    Node.of("user", Arrays.asList(
                            Node.of("name", "Alice"),
                            Node.of("address", Arrays.asList(
                                    Node.of("city", "nyc"),
                                    Node.of("zip", "10001")
                            ))
                    ))
            ));

            Node updated = update(root, "user.address.city", String::toUpperCase);

            System.out.println(updated);
        }
    }

}
