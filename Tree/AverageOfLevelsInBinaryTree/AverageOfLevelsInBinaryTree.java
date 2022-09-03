package Tree.AverageOfLevelsInBinaryTree;
// 637. Average of Levels in Binary Tree
// https://leetcode.com/problems/average-of-levels-in-binary-tree/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import Tree.TreeNode;

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> answer = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            double sum = 0;
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                sum += cur.val;

                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }

            answer.add(sum / size);
        }

        return answer;
    }
}


public class AverageOfLevelsInBinaryTree {

}
