package Tree.BinaryTreePruning;
// 814. Binary Tree Pruning
// https://leetcode.com/problems/binary-tree-pruning/

import Tree.TreeNode;

// Solution, 깔끔하네.
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }
}


// O(N) 완전탐색
class Solution1 {
    public TreeNode pruneTree(TreeNode root) {
        TreeNode dummy = new TreeNode();
        dummy.left = root;
        dfs(dummy);
        return dummy.left;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return false;
        }

        boolean left = dfs(root.left);
        boolean right = dfs(root.right);

        if (root.left != null && left == false) {
            root.left = null;
        }
        if (root.right != null && right == false) {
            root.right = null;
        }

        if (root.val == 1) {
            return true;
        }
        return left || right;
    }
}


public class BinaryTreePruning {
    public static void main(String[] args) {

    }
}
