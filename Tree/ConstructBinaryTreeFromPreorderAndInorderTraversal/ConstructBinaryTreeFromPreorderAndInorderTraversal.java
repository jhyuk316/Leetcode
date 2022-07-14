package Tree.ConstructBinaryTreeFromPreorderAndInorderTraversal;
// 105. Construct Binary Tree from Preorder and Inorder Traversal
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

import Tree.TreeNode;

// O(N) solution 신기하네.
class Solution {
    private int inIdx = 0;
    private int preIdx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (preIdx >= preorder.length) {
            return null;
        }

        if (inorder[inIdx] == stop) {
            inIdx++;
            return null;
        }

        TreeNode node = new TreeNode(preorder[preIdx++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }
}


// O(N)
// Preorder - Root -> Left -> Right
// Inorder - Left -> Root -> Right
// Postorder - Left -> Right -> Root
class Solution1 {
    int N;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        N = preorder.length;
        return makeGraph(preorder, inorder, 0, 0, N - 1);
    }

    private TreeNode makeGraph(int[] preorder, int[] inorder, int preorderIndex, int inorderStart,
            int inorderEnd) {

        if (inorderStart > inorderEnd || preorderIndex >= N) {
            return null;
        }

        TreeNode head = new TreeNode(preorder[preorderIndex]);

        int idx = inorderStart;
        for (int i = inorderStart; i <= inorderEnd; ++i) {
            if (preorder[preorderIndex] == inorder[i]) {
                idx = i;
                break;
            }
        }

        head.left = makeGraph(preorder, inorder, preorderIndex + 1, inorderStart, idx - 1);
        head.right = makeGraph(preorder, inorder, preorderIndex + idx - inorderStart + 1, idx + 1,
                inorderEnd);
        return head;
    }
}


public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        testSol(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7}, null);
        testSol(new int[] {7, -10, -4, 3, -1, 2, -8, 11}, new int[] {-4, -10, 3, -1, 7, 11, -8, 2},
                null);
    }

    static void testSol(int[] input1, int[] input2, TreeNode output) {
        Solution sol = new Solution();
        // todo : solution match
        TreeNode res = sol.buildTree(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
