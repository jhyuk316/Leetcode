package Tree.FlattenBinaryTreeToLinkedList;
// 114. Flatten Binary Tree to Linked List
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

import Tree.TreeNode;

// O(N) Solution bottom-up
class Solution {
    TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.right);
        flatten(root.left);

        root.left = null;
        root.right = prev;
        prev = root;
    }
}


// O(N) 재귀 탐색 top-down
class Solution1 {
    TreeNode dummy;

    public void flatten(TreeNode root) {
        dummy = new TreeNode();
        TreeNode head = dummy;

        dfs(root);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        // System.out.println(root.val);

        TreeNode left = root.left;
        TreeNode right = root.right;

        dummy.left = null; // 이게 문제
        dummy.right = root;
        dummy = dummy.right;

        dfs(left);
        dfs(right);
        return;
    }
}


public class FlattenBinaryTreeToLinkedList {

}
