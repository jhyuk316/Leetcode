package WordBreakII

// 140. Word Break II
// https://leetcode.com/problems/word-break-ii/description/?envType=daily-question&envId=2024-05-25

import "strings"

var memo map[string][]string

func wordBreak(s string, wordDict []string) []string {
	memo = make(map[string][]string)
	return dfs(s, wordDict)
}

func dfs(s string, wordDict []string) []string {
	if res, ok := memo[s]; ok {
		return res
	}

	var result []string
	for _, word := range wordDict {
		if strings.HasPrefix(s, word) {
			wordLength := len(word)
			if len(s) == wordLength {
				result = append(result, word)
			} else {
				postStrings := dfs(s[wordLength:], wordDict)
				for _, post := range postStrings {
					result = append(result, word+" "+post)
				}
			}
		}
	}

	return result
}
