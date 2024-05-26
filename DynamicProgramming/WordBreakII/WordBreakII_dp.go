//go:build WordBreakII_dp

package WordBreakII

// 140. Word Break II
// https://leetcode.com/problems/word-break-ii/description/?envType=daily-question&envId=2024-05-25

import (
	"strings"
)

func wordBreak(s string, wordDict []string) []string {
	dp := make([][]string, len(s)+1)

	for index, _ := range s {
		index++
		for _, word := range wordDict {
			length := len(word)
			preIndex := index - length

			if preIndex >= 0 && strings.HasPrefix(s[preIndex:], word) {
				if len(dp[preIndex]) > 0 {
					for _, sub := range dp[preIndex] {
						dp[index] = append(dp[index], sub+" "+word)
					}
				} else if preIndex == 0 {
					dp[index] = append(dp[index], word)
				}
			}
		}
	}

	//fmt.Println(dp)
	return dp[len(s)]
}
