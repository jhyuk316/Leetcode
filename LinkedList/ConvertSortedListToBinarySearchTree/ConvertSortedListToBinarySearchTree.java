package LinkedList.ConvertSortedListToBinarySearchTree;
// 109. Convert Sorted List to Binary Search Tree
// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

import LinkedList.ListNode;
import Tree.TreeNode;


// O(N?) solution 앞에만 끊으면 되는거군
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;

        if (head.next == null) {
            return new TreeNode(head.val);
        }

        var slow = head;
        var fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        var mid = slow.next;
        slow.next = null;

        var root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }
}


// O(N?) solution tail 표기
class Solution2 {
    public TreeNode sortedListToBST(ListNode head) {
        return makeBST(head, null);
    }

    private TreeNode makeBST(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        if (head.next == tail) {
            return new TreeNode(head.val);
        }

        ListNode mid = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            mid = mid.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(mid.val);
        root.left = makeBST(head, mid);
        root.right = makeBST(mid.next, tail);

        return root;
    }
}


// O(N?) 지저분하다 왜 이게 통과 됬지?
class Solution1 {
    public TreeNode sortedListToBST(ListNode head) {
        return makeBST(head);
    }

    private TreeNode makeBST(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode left = head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = head;
        while (temp.next != null && temp.next.val != slow.val) {
            temp = temp.next;
        }
        temp.next = null;

        TreeNode root = new TreeNode(slow.val);
        System.out.println(slow.val);

        fast = slow.next;
        slow.next = null;
        if (slow.val != left.val) {
            root.left = makeBST(left);
        }
        root.right = makeBST(fast);

        return root;
    }
}


public class ConvertSortedListToBinarySearchTree {


}
