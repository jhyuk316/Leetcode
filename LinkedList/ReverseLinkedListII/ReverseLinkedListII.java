package LinkedList.ReverseLinkedListII;
// 92. Reverse Linked List II
// https://leetcode.com/problems/reverse-linked-list-ii/
// 비슷하게는 하는데 통과할 만큼의 디테일은 못하겠다. ㅠ.ㅠ

import LinkedList.ListNode;

// O(N) solution
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = null;
        ListNode cur = head;

        for (int i = 0; i < left - 1; ++i) {
            prev = cur;
            cur = cur.next;
        }

        ListNode leftTail = prev;
        ListNode rightHead = cur;

        System.out.println(leftTail.val);
        System.out.println(rightHead.val);

        ListNode temp;
        for (int i = left; i < right; ++i) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        if (leftTail != null) {
            leftTail.next = prev;
        } else {
            dummy.next = prev;
        }
        rightHead.next = cur;

        return dummy.next;
    }
}


public class ReverseLinkedListII {

}
