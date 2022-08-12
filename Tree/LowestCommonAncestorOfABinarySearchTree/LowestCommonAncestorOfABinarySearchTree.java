package Tree.LowestCommonAncestorOfABinarySearchTree;
// 235. Lowest Common Ancestor of a Binary Search Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// p and q will exist in the BST.

import Tree.TreeNode;

// O(logN) 있음이 보장되는 문제니까
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
}


// O(N) 완전탐색
class Solution1 {
    TreeNode find;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find = null;
        findLCA(root, p, q);
        return find;
    }

    private boolean findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || find != null) {
            return false;
        }

        boolean res = false;
        if (root == p || root == q) {
            res = true;
        }

        boolean left = findLCA(root.left, p, q);
        boolean right = findLCA(root.right, p, q);

        if (left && right || res && left || res && right) {
            find = root;
        }

        return left || right || res;
    }
}


public class LowestCommonAncestorOfABinarySearchTree {

}
