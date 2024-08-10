//go:build PalindromePartitioning_dp

package PalindromePartitioning

// 131. Palindrome Partitioning
// https://leetcode.com/problems/palindrome-partitioning/description/?envType=daily-question&envId=2024-05-22

func partition(s string) [][]string {

	return nil
}

func isPalindrome(s string) bool {
	l := 0
	r := len(s) - 1
	for l <= r {
		if s[l] != s[r] {
			return false
		}
		l++
		r--
	}
	return true
}
