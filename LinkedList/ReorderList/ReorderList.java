package LinkedList.ReorderList;
// 143. Reorder List
// https://leetcode.com/problems/reorder-list/


// Definition for singly-linked list.
import LinkedList.ListNode;
import java.util.Arrays;


// 해답 무슨 코드지?, 아래거 재귀?
class Solution2 {
    public void reorderList(ListNode head) {
        if (head != null)
            helper(head, head.next);
    }

    private ListNode helper(ListNode slow, ListNode fast) {
        if (fast == null)
            return slow;
        else if (fast.next == null)
            return slow.next;

        ListNode ans = helper(slow.next, fast.next.next);
        ListNode tail = ans.next.next;
        ans.next.next = slow.next;
        slow.next = ans.next;
        ans.next = tail;
        return ans;
    }
}


// 해답 1. 중간 찾기, 2. 중간부터 뒤집기, 3. 합치기
class Solution {
    public void reorderList(ListNode head) {
        // find middle
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // System.out.println(slow.val);

        // reverse mid to end, split
        ListNode cur = slow.next;
        ListNode prev = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        slow.next = null;

        // System.out.println(Arrays.toString(head.toIntArrays()));
        // System.out.println(Arrays.toString(prev.toIntArrays()));

        // merge & reorder
        slow = head;
        fast = prev;
        while (fast != null && slow != null) {
            ListNode nextSlow = slow.next;
            slow.next = fast;
            ListNode nextFast = fast.next;
            fast.next = nextSlow;
            slow = nextSlow;
            fast = nextFast;
        }
    }
}


// 공간 잡고 반복
class Solution1 {
    public void reorderList(ListNode head) {
        ListNode[] nodeList = new ListNode[5 * 10001];

        ListNode cur = head;
        int i = 0;
        while (cur != null) {
            nodeList[i] = cur;
            cur = cur.next;
            i++;
        }

        int left = 0;
        int right = i - 1;

        while (left < right) {
            nodeList[left].next = nodeList[right];
            left++;

            if (left >= right) {
                break;
            }

            nodeList[right].next = nodeList[left];
            right--;
        }
        nodeList[right].next = null;

        return;
    }
}


public class ReorderList {
    public static void main(String[] args) {
        testSol(new ListNode(new int[] {1, 2, 3, 4}), new int[] {1, 4, 2, 3});
        testSol(new ListNode(new int[] {1, 2, 3, 4, 5}), new int[] {1, 5, 2, 4, 3});
        testSol(new ListNode(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}),
                new int[] {1, 9, 2, 8, 3, 7, 4, 6, 5});
    }

    static void testSol(ListNode input, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        sol.reorderList(input);
        int[] res = input.toIntArrays();
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }

}
