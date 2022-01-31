package Tree.BinaryTreeMaximumPathSum;
// 124. Binary Tree Maximum Path Sum
// https://leetcode.com/problems/binary-tree-maximum-path-sum/


// Definition for a binary tree node.
import Tree.TreeNode;

// O(n)
class Solution {
    private int maxSum = -10001;

    public int maxPathSum(TreeNode root) {
        _maxPathSum(root);
        return maxSum;
    }

    private int _maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int parent = root.val;
        int left = _maxPathSum(root.left);
        int right = _maxPathSum(root.right);

        parent += Math.max(0, Math.max(left, right));

        this.maxSum = Math.max(this.maxSum, Math.max(parent, root.val + left + right));

        return parent;
    }
}


public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        System.out.println("no Test");
    }
}
