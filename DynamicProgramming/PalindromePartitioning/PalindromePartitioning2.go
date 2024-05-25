//go:build PalindromePartitioning2

package PalindromePartitioning

// 131. Palindrome Partitioning
// https://leetcode.com/problems/palindrome-partitioning/description/?envType=daily-question&envId=2024-05-22

var palindromeMap map[string][][]string

func partition(s string) [][]string {
	palindromeMap = make(map[string][][]string, 0)
	return dfs(s)
}

func dfs(s string) [][]string {
	if result, ok := palindromeMap[s]; ok {
		return result
	}

	if len(s) == 1 {
		return [][]string{{s}}
	}

	result := make([][]string, 0)
	if isPalindrome(s) {
		result = append(result, []string{s})
	}

	for i := 1; i < len(s); i++ {
		sLeft := s[:i]
		// fmt.Println(sLeft)
		if isPalindrome(sLeft) {
			subResult := dfs(s[i:])
			// fmt.Println(subResult)
			for _, subAns := range subResult {
				tempResult := []string{sLeft}
				// fmt.Println(subAns)
				tempResult = append(tempResult, subAns...)
				result = append(result, tempResult)
			}
		}
	}

	palindromeMap[s] = result
	return result
}

func isPalindrome(s string) bool {
	l := 0
	r := len(s) - 1
	result := true
	for l <= r {
		if s[l] != s[r] {
			result = false
			break
		}
		l++
		r--
	}

	return result
}
