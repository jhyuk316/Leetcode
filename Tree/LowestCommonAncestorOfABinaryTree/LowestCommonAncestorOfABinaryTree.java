package Tree.LowestCommonAncestorOfABinaryTree;
// 236. Lowest Common Ancestor of a Binary Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
// p and q will exist in the BST.


import Tree.TreeNode;

// O(N) solution 왜 되지? 문제가 p,q가 있음을 보장해서?
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }
}


// O(N) N : number of node 완전탐색 DFS
class Solution1 {
    TreeNode result;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return result;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        boolean resRoot = false;
        if (root.val == p.val || root.val == q.val) {
            resRoot = true;
        }

        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);

        if (left && right || resRoot && left || resRoot && right) {
            result = root;
        }

        boolean res = left || right || resRoot;
        return res;
    }
}


public class LowestCommonAncestorOfABinaryTree {

}
