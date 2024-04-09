package interview.microsoft_M365_2024;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

    // 2024.04.09

    class Node {
        int val;
        Node next, random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            Node newNode = new Node(p.val);
            map.put(p, newNode);
            p = p.next;
        }

        p = head;
        while (p != null) {
            Node newNode = map.get(p);
            newNode.next = p.next == null ? null : map.get(p.next);
            newNode.random = p.random == null ? null : map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

}
