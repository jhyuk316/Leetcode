package Tree.ConvertSortedArrayToBinarySearchTree;
// 108. Convert Sorted Array to Binary Search Tree
// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

import Tree.TreeNode;

// O(N) DFS
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return makeBST(nums, 0, nums.length - 1);
    }

    private TreeNode makeBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = makeBST(nums, start, mid - 1);
        root.right = makeBST(nums, mid + 1, end);
        return root;
    }
}


public class ConvertSortedArrayToBinarySearchTree {

}
