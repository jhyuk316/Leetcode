package Tree.BinaryTreeRightSideView;
// 199. Binary Tree Right Side View
// https://leetcode.com/problems/binary-tree-right-side-view/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import Tree.TreeNode;



// O(N) solution, DFS
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        dfs(root, res, 0);

        return res;
    }

    public void dfs(TreeNode root, List<Integer> list, int depth) {
        if (root == null) {
            return;
        }

        if (list.size() == depth) {
            list.add(root.val);
        }

        dfs(root.right, list, depth + 1);
        dfs(root.left, list, depth + 1);
    }
}


// O(N) BFS
class Solution1 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                if (i == size - 1) {
                    res.add(temp.val);
                }
            }
        }
        return res;
    }
}


public class BinaryTreeRightSideView {

}
