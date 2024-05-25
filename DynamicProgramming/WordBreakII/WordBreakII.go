package WordBreakII

// 140. Word Break II
// https://leetcode.com/problems/word-break-ii/description/?envType=daily-question&envId=2024-05-25

import "strings"

var dp map[string][]string

func wordBreak(s string, wordDict []string) []string {
	dp = make(map[string][]string)
	return dfs(s, wordDict)
}

func dfs(s string, wordDict []string) []string {
	if res, ok := dp[s]; ok {
		return res
	}

	result := make([]string, 0)
	for _, word := range wordDict {
		if strings.HasPrefix(s, word) {
			length := len(word)
			if len(s) == length {
				result = append(result, word)
				continue
			}

			subString := dfs(s[length:], wordDict)
			for _, sub := range subString {
				result = append(result, word+" "+sub)
			}
		}
	}

	return result
}
