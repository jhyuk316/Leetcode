# 33. Search in Rotated Sorted Array
# https://leetcode.com/problems/search-in-rotated-sorted-array/

from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        minPos = self.findMin(nums)

        leftSide = self._search(nums[:minPos], target)
        rightSide = self._search(nums[minPos:], target)

        if leftSide != -1:
            return leftSide
        if rightSide != -1:
            return rightSide + minPos

        return -1

    def findMin(self, nums) -> int:
        left = 0
        right = len(nums) - 1

        while left < right:
            mid = (left + right) // 2
            if nums[mid] < nums[right]:
                right = mid
            else:
                left = mid + 1

        return left

    def _search(self, nums: List[int], target: int) -> int:
        left = 0
        right = len(nums) - 1

        while left <= right:
            mid = (left + right) // 2
            if target < nums[mid]:
                right = mid - 1
            elif nums[mid] < target:
                left = mid + 1
            else:
                return mid

        return -1


def testSol(func, input, output):
    res = func(*input)
    if res == output:
        print(f"O : {res}")
    else:
        print(f"X : {res}	excpet : {output}")


if __name__ == "__main__":
    sol = Solution()

    testSol(sol.search, ([4, 5, 6, 7, 0, 1, 2], 0), 4)
    testSol(sol.search, ([4, 5, 6, 7, 0, 1, 2], 3), -1)
    testSol(sol.search, ([1], 0), -1)
    testSol(sol.search, ([1], 1), 0)

