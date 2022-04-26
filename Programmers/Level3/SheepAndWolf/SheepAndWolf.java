package Programmers.Level3.SheepAndWolf;
// 양과 늑대
// https://programmers.co.kr/learn/courses/30/lessons/92343


import java.util.*;

class Solution {
    Map<Integer, List<Integer>> adjMap;
    int maxSheep;

    public int solution(int[] info, int[][] edges) {
        maxSheep = 0;
        adjMap = new HashMap<>();

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            if (!adjMap.containsKey(start)) {
                adjMap.put(start, new ArrayList<>());
            }
            adjMap.get(start).add(end);
        }

        System.out.println(adjMap);

        List<Integer> start = new ArrayList<>();
        start.add(0);
        dfs(info, start, 0, 0);

        return maxSheep;
    }

    private void dfs(int[] info, List<Integer> nextList, int numSheep, int numWolf) {
        if (nextList.isEmpty()) {
            return;
        }

        int tempSheep = numSheep;
        int tempWolf = numWolf;
        if (info[nextList.get(0)] == 0) {
            tempSheep++;
        } else {
            tempWolf++;
        }

        if (tempSheep <= tempWolf) {
            return;
        }

        maxSheep = Math.max(maxSheep, tempSheep);

        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < nextList.size(); ++i) {
            int num = nextList.get(i);
            tempList.clear();
            tempList.addAll(nextList);
            tempList.remove(num);

            for (int next : adjMap.get(num)) {
                tempList.add(next);
            }

            System.out.println(num + " " + info[nextList.get(0)] + " " + numSheep + " " + numWolf
                    + " " + tempList);

            dfs(info, tempList, tempSheep, tempWolf);
        }
        return;
    }
}


class TreeNode {
    int num;
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    public String toString() {
        return String.valueOf(num);
    }
}


class Solution1 {
    boolean[] visited;
    int maxSheep;

    public int solution(int[] info, int[][] edges) {
        maxSheep = 0;
        visited = new boolean[info.length];

        TreeNode[] treeNodeArr = new TreeNode[info.length];
        for (int i = 0; i < info.length; ++i) {
            treeNodeArr[i] = new TreeNode(info[i]);
            treeNodeArr[i].num = i;
        }

        for (int i = 0; i < edges.length; ++i) {
            int index1 = edges[i][0];
            int index2 = edges[i][1];
            if (treeNodeArr[index1].left == null) {
                treeNodeArr[index1].left = treeNodeArr[index2];
            } else {
                treeNodeArr[index1].right = treeNodeArr[index2];
            }
        }

        List<TreeNode> start = new ArrayList<>();
        start.add(treeNodeArr[0]);
        dfs(start, 0, 0);
        return maxSheep;
    }

    private void dfs(List<TreeNode> nextList, int numSheep, int numWolf) {
        if (nextList.isEmpty()) {
            return;
        }

        List<TreeNode> tempList = new ArrayList<>();
        for (int i = 0; i < nextList.size(); ++i) {
            TreeNode node = nextList.get(i);
            tempList.clear();
            tempList.addAll(nextList);
            tempList.remove(node);


            if (node.left != null) {
                tempList.add(node.left);
            }
            if (node.right != null) {
                tempList.add(node.right);
            }

            // System.out.println(
            // node.num + " " + node.val + " " + numSheep + " " + numWolf + " " + tempList);
            if (node.val == 0) {
                if (numSheep + 1 > numWolf) {
                    dfs(tempList, numSheep + 1, numWolf);
                    maxSheep = Math.max(maxSheep, numSheep + 1);
                }
            } else {
                if (numSheep > numWolf + 1) {
                    dfs(tempList, numSheep, numWolf + 1);
                }
            }
        }

        return;
    }
}


public class SheepAndWolf {
    public static void main(String[] args) {
        testSol(new int[] {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][] {{0, 1}, {1, 2}, {1, 4},
                {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}, 5);
        testSol(new int[] {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, new int[][] {{0, 1}, {0, 2}, {1, 3},
                {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}, 5);

    }

    static void testSol(int[] input1, int[][] input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.solution(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
