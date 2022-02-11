# 153. Find Minimum in Rotated Sorted Array
# https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

from typing import List


class Solution:
    def findMin(self, nums: List[int]) -> int:
        left = 0
        right = len(nums) - 1

        while left < right:
            mid = (left + right) // 2
            if nums[mid] < nums[right]:
                right = mid
            else:
                left = mid + 1

        return nums[left]


def testSol(func, input, output):
    res = func(input)
    if res == output:
        print(f"O : {res}")
    else:
        print(f"X : {res}	excpet : {output}")


if __name__ == "__main__":
    sol = Solution()

    testSol(sol.findMin, [3, 4, 5, 1, 2], 1)
    testSol(sol.findMin, [4, 5, 6, 7, 0, 1, 2], 0)
    testSol(sol.findMin, [11, 13, 15, 17], 11)
    testSol(sol.findMin, [3, 4, 5, 0], 0)

