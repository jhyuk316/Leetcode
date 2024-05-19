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
	var fractions = make(map[float64]position, 0)

	for i, num := range arr {
		for j := i + 1; j < len(arr); j++ {
			fractions[float64(num)/float64(arr[j])] = position{i, j}
		}
	}
	//fmt.Println(fractions)

	sortedKey := make([]float64, 0)
	for key := range fractions {
		sortedKey = append(sortedKey, key)
	}

	sort.Float64s(sortedKey)
	//fmt.Println(sortedKey)

	return []int{arr[fractions[sortedKey[k-1]].x], arr[fractions[sortedKey[k-1]].y]}
}
