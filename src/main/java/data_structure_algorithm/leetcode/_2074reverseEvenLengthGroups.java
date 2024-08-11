package data_structure_algorithm.leetcode;


import data_structure_algorithm.domain.ListNode;
import org.junit.Test;

public class _2074reverseEvenLengthGroups {

    public static class Solution1 {

        @Test
        public void test() {
//            System.out.println(reverseEvenLengthGroups(ListNode.buildWithArray(new int[] {5,2,6,3,9,1,7,3,8,4})));
//            System.out.println(reverseEvenLengthGroups(ListNode.buildWithArray(new int[] {1,1,0,6})));
//            System.out.println(reverseEvenLengthGroups(ListNode.buildWithArray(new int[] {1,1,0,6,5})));
//            System.out.println(reverseEvenLengthGroups(ListNode.buildWithArray(new int[] {1,5,0,2,4,7,3,6})));
            System.out.println(reverseEvenLengthGroups(ListNode.buildWithArray(new int[] {4,3,0,5,1,2,7,8,6})));
        }

        public ListNode reverseEvenLengthGroups(ListNode head) {
            int groupLen = 1;
            ListNode prev = null, cur = head, lastTail = null, groupHead = null;
            while (cur != null) {
                // 分组
                int len = 0;
                ListNode lastTailForLastGroup = prev;
                while (len < groupLen && cur != null) {
                    prev = cur;
                    cur = cur.next;
                    len++;
                }

                // 最后一组len小于groupLen时单独处理(当len为偶数时反转)
                if (len < groupLen && len % 2 == 0) {
                    if (groupLen % 2 != 0) {
                        for (int i = 0; i < groupLen - 2; i++) {
                            lastTailForLastGroup = lastTailForLastGroup.next;
                        }
                    }
                    lastTailForLastGroup.next = reverse(lastTailForLastGroup.next);
                    break;
                }

                // 根据实际组长度判断是否反转
                if (len % 2 != 0) {
                    lastTail = prev;
                    groupHead = cur;
                } else {
                    prev.next = null;
                    lastTail.next = reverse(groupHead);
                    groupHead.next = cur;
                }
                groupLen++;
            }
            return head;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null, cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }

    }

}
