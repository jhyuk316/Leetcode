package TwoSum

func twoSum(nums []int, target int) []int {
	pos := make(map[int]int)

	for index, num := range nums {
		if p, contain := pos[target-num]; contain {
			return []int{p, index}
		}
		pos[num] = index
	}
	return nil
}
