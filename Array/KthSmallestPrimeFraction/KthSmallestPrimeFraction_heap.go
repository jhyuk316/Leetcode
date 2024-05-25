//go:build KthSmallestPrimeFraction2

package KthSmallestPrimeFraction

import "container/heap"

// 786. K-th Smallest Prime Fraction
// https://leetcode.com/problems/k-th-smallest-prime-fraction/description/

type PriorityQueue []*Primes

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].Val < pq[j].Val
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *PriorityQueue) Push(x any) {
	item := x.(*Primes)
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() any {
	old := *pq
	n := len(old)
	item := old[n-1]
	old[n-1] = nil // avoid memory leak
	*pq = old[0 : n-1]
	return item
}

type Primes struct {
	Val float64
	Ans []int
}

func kthSmallestPrimeFraction(arr []int, k int) []int {
	primes := make(PriorityQueue, 0)
	heap.Init(&primes)

	for i := 0; i < len(arr)-1; i++ {
		for j := i + 1; j < len(arr); j++ {
			heap.Push(&primes, &Primes{float64(arr[i]) / float64(arr[j]), []int{arr[i], arr[j]}})
		}
	}

	for i := 0; i < k-1; i++ {
		heap.Pop(&primes)
	}
	kPrime := heap.Pop(&primes).(*Primes)
	return kPrime.Ans
}
