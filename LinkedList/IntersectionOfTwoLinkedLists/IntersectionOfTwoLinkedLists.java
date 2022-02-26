package LinkedList.IntersectionOfTwoLinkedLists;
// 160. Intersection of Two Linked Lists
// https://leetcode.com/problems/intersection-of-two-linked-lists/

import java.util.HashSet;
import java.util.Set;
import LinkedList.ListNode;


// O(M+N) Space: O(1) 해답, 두개의 리스트 노드를 한번씩 돌면 똑같은 곳에서 만날 수 있음.
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;

        while (tempA != tempB) {
            tempA = tempA != null ? tempA.next : headB;
            tempB = tempB != null ? tempB.next : headA;
        }
        return tempA;
    }
}


// O(M+N) Space: O(N) 셋에 노드를 저장하고 비교.
class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<Object> nodeSet = new HashSet<>();

        while (headA != null) {
            nodeSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (nodeSet.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}


// O(M*N) brute-force
class Solution1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode tempB = headB;
            while (tempB != null) {
                if (headA == tempB) {
                    return tempB;
                }
                tempB = tempB.next;
            }
            headA = headA.next;
        }
        return null;
    }
}


public class IntersectionOfTwoLinkedLists {

}
