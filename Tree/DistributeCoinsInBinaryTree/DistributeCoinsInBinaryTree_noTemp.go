//go:build DistributeCoinsInBinaryTree_noTemp

package DistributeCoinsInBinaryTree

// 979. Distribute Coins in Binary Tree
// https://leetcode.com/problems/distribute-coins-in-binary-tree/

// Definition for a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func distributeCoins(root *TreeNode) int {
	_, moves := dfs(root)
	return moves
}

func absInt(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func dfs(node *TreeNode) (int, int) {
	if node == nil {
		return 0, 0
	}

	leftCoins, leftMoves := dfs(node.Left)
	rightCoins, rightMoves := dfs(node.Right)
	coins := node.Val - 1 + leftCoins + rightCoins
	moves := absInt(coins) + leftMoves + rightMoves
	return coins, moves
}
