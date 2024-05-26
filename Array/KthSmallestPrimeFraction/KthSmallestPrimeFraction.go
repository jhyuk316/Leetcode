//go:build KthSmallestPrimeFraction1

package KthSmallestPrimeFraction

// 786. K-th Smallest Prime Fraction
// https://leetcode.com/problems/k-th-smallest-prime-fraction/description/

type Primes struct {
	x, y int
}

func kthSmallestPrimeFraction(arr []int, k int) []int {
	primes := []Primes{}

	for i := 0; i < len(arr)-1; i++ {
		for j := i + 1; j < len(arr); j++ {
			primes = append(primes, Primes{arr[i], arr[j]})
		}
	}

	slices.SortFunc(primes, func(i, j Primes) int {
		return cmp.Compare(float64(primes[i].x)/float64(primes[i].y), float64(primes[j].x)/float64(primes[j].y))
	})

	return []int{primes[k-1].x, primes[k-1].y}
}
