package Tree.ValidateBinarySearchTree;
// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/

import Tree.TreeNode;

// O(N) solution
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null)
            return true;

        if (root.val >= max || root.val <= min) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}


// O(N) DFS
class Solution1 {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long min, long max) {
        boolean result = true;

        if (root.left != null) {
            if (min < root.left.val && root.left.val < root.val) {
                result &= dfs(root.left, min, root.val);
            } else {
                return false;
            }
        }

        if (root.right != null) {
            if (root.val < root.right.val && root.right.val < max) {
                result &= dfs(root.right, root.val, max);
            } else {
                return false;
            }
        }
        return result;
    }
}


public class ValidateBinarySearchTree {

}
