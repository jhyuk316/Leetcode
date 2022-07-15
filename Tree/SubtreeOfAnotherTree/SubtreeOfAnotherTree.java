package Tree.SubtreeOfAnotherTree;
// 572. Subtree of Another Tree
// https://leetcode.com/problems/subtree-of-another-tree/

import Tree.TreeNode;

// O(M*N) DFS
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }

    private boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return isSameTree(root, subRoot) || dfs(root.left, subRoot) || dfs(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        } else if (root == null || subRoot == null) {
            return false;
        }

        return root.val == subRoot.val && isSameTree(root.left, subRoot.left)
                && isSameTree(root.right, subRoot.right);
    }
}


public class SubtreeOfAnotherTree {

}
