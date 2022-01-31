package LinkedList.ReorderList;
// 143. Reorder List
// https://leetcode.com/problems/reorder-list/


// Definition for singly-linked list.
import LinkedList.ListNode;
import java.util.Arrays;


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
