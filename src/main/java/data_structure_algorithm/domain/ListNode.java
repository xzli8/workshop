package data_structure_algorithm.domain;


/**
 * Definition for singly-linked list.
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode() {}

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val).append("->");
            cur = cur.next;
        }
        return sb.substring(0, sb.length() - 2).toString();
    }

    public static ListNode buildWithArray(int[] vals) {
        ListNode dummy = new ListNode(), cur = dummy;
        for (int val : vals) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return dummy.next;
    }

}
