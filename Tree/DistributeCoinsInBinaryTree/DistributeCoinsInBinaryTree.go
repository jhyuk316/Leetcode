//go:build DistributeCoinsInBinaryTree

package DistributeCoinsInBinaryTree

// 979. Distribute Coins in Binary Tree
// https://leetcode.com/problems/distribute-coins-in-binary-tree/

// Definition for a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var total = 0

func distributeCoins(root *TreeNode) int {
	total = 0
	dfs(root)
	return total
}

func absInt(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func dfs(node *TreeNode) int {
	result := node.Val - 1
	if node.Left != nil {
		left := dfs(node.Left)
		total += absInt(left)
		result += left
	}
	if node.Right != nil {
		right := dfs(node.Right)
		total += absInt(right)
		result += right
	}

	return result
}
