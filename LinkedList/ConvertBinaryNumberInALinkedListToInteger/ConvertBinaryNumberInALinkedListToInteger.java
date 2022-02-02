package LinkedList.ConvertBinaryNumberInALinkedListToInteger;
// 1290. Convert Binary Number in a Linked List to Integer
// https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/

import LinkedList.ListNode;

// O(n)
class Solution {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            // result *= 2;
            result = result << 1;
            result += head.val;
            head = head.next;
        }
        return result;
    }
}


public class ConvertBinaryNumberInALinkedListToInteger {
    public static void main(String[] args) {
        System.out.println("no test");
    }
}
