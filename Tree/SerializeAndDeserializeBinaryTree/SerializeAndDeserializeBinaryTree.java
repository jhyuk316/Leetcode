package Tree.SerializeAndDeserializeBinaryTree;
// 297. Serialize and Deserialize Binary Tree
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import Tree.TreeNode;

// 으아 복잡하다
class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder("[");
        Queue<TreeNode> bfsQueue = new LinkedList<>();

        if (root == null) {
            result.append("]");
            return result.toString();
        }

        bfsQueue.add(root);
        while (!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();

            boolean allNull = true;
            StringBuilder nextResult = new StringBuilder();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = bfsQueue.poll();
                if (cur == null) {
                    nextResult.append("null");
                } else {
                    allNull = false;
                    nextResult.append(cur.val);
                    bfsQueue.add(cur.left);
                    bfsQueue.add(cur.right);
                }

                nextResult.append(",");
            }
            if (!allNull) {
                result.append(nextResult);
            }
        }
        result.setCharAt(result.length() - 1, ']');

        System.out.println(result.toString());
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // data.replaceAll("[", "");
        // data.replaceAll("]", "");
        if (data.length() <= 2) {
            return null;
        }

        data = data.substring(1, data.length() - 1);
        String[] dataArr = data.split(",");

        TreeNode head = new TreeNode(Integer.parseInt(dataArr[0]));

        int j = 2;
        int i = 1;
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(head);

        while (i < dataArr.length) {
            List<TreeNode> nextNodes = new ArrayList<>();
            for (int k = 0; k < j; ++k) {
                if (dataArr[i].equals("null")) {
                    i++;
                    if (i >= dataArr.length) {
                        break;
                    }
                    continue;
                }
                TreeNode node = new TreeNode(Integer.parseInt(dataArr[i]));
                nextNodes.add(node);
                if (k % 2 == 0) {
                    nodes.get(k / 2).left = node;
                } else {
                    nodes.get(k / 2).right = node;
                }

                i++;
                if (i >= dataArr.length) {
                    break;
                }
            }
            j = 2 * nextNodes.size();
            nodes = nextNodes;
        }

        return head;
    }
}


public class SerializeAndDeserializeBinaryTree {

}
