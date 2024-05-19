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
	return dfs(root)
}

func absInt(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func dfs(node *TreeNode) int {
	left := 1
	right := 1
	result := node.Val - 1
	if node.Left != nil {
		left = dfs(node.Left)
		total += absInt(left)
		result += left
	}
	if node.Right != nil {
		right = dfs(node.Right)
		total += absInt(right)
		result += right
	}

	//fmt.Println(node.Val, left, right, result, total)
	return result
}
