package KthSmallestPrimeFraction

// 786. K-th Smallest Prime Fraction
// https://leetcode.com/problems/k-th-smallest-prime-fraction/description/

import (
	"sort"
)

type position struct {
	x, y int
}

func kthSmallestPrimeFraction(arr []int, k int) []int {
	fractions := []position{}

	for i := 0; i < len(arr)-1; i++ {
		for j := i + 1; j < len(arr); j++ {
			fractions = append(fractions, position{i, j})
		}
	}

	sort.Slice(fractions, func(i, j int) bool {
		return float64(arr[fractions[i].x])/float64(arr[fractions[i].y]) < float64(arr[fractions[j].x])/float64(arr[fractions[j].y])
	})

	return []int{arr[fractions[k-1].x], arr[fractions[k-1].y]}
}
