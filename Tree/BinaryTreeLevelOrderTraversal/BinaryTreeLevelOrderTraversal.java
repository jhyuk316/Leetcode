package Tree.BinaryTreeLevelOrderTraversal;
// 102. Binary Tree Level Order Traversal
// https://leetcode.com/problems/binary-tree-level-order-traversal/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import Tree.TreeNode;

// O(N) DFS
class Solution {
    List<List<Integer>> result;

    public List<List<Integer>> levelOrder(TreeNode root) {
        result = new ArrayList<>();
        dfsTraversal(root, 0);

        return result;
    }

    private void dfsTraversal(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (result.size() < depth + 1) {
            result.add(new ArrayList<>());
        }
        result.get(depth).add(node.val);

        dfsTraversal(node.left, depth + 1);
        dfsTraversal(node.right, depth + 1);
    }
}


// O(N) BFS
class Solution1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);
        while (!bfsQueue.isEmpty()) {
            List<Integer> levelResult = new ArrayList<>();
            int size = bfsQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = bfsQueue.poll();
                levelResult.add(cur.val);
                if (cur.left != null) {
                    bfsQueue.add(cur.left);
                }
                if (cur.right != null) {
                    bfsQueue.add(cur.right);
                }
            }
            result.add(levelResult);
        }

        return result;
    }
}


public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {

    }

    static void testSol(TreeNode input, List<List<Integer>> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<List<Integer>> res = sol.levelOrder(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
