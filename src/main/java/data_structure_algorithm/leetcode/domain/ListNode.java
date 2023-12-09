package data_structure_algorithm.leetcode.domain;


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

    public ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }

}
