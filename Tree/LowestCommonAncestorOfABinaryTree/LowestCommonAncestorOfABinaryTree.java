package Tree.LowestCommonAncestorOfABinaryTree;
// 236. Lowest Common Ancestor of a Binary Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/


// O(N) N : number of node 완전탐색 DFS
import Tree.TreeNode;

class Solution {
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
