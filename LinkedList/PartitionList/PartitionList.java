package LinkedList.PartitionList;
// 86. Partition List
// https://leetcode.com/problems/partition-list/

import LinkedList.ListNode;

// O(N)
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode();
        ListNode dummy = left;
        ListNode right = new ListNode();
        ListNode dummy2 = right;

        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = left.next;
            } else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        right.next = null;

        left.next = dummy2.next;

        return dummy.next;
    }
}


public class PartitionList {
    public static void main(String[] args) {

    }
}
