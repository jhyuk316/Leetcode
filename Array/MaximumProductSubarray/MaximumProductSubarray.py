# 152. Maximum Product Subarray
# https://leetcode.com/problems/maximum-product-subarray/

from typing import List

# O(n)
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        result = nums[0]

        temp = 0
        for num in nums:
            temp = temp * num if temp != 0 else num
            result = max(result, temp)

        temp = 0
        for num in reversed(nums):
            temp = temp * num if temp != 0 else num
            result = max(result, temp)

        return result


# O(n)
class Solution1:
    def maxProduct(self, nums: List[int]) -> int:
        if len(nums) == 1:
            return nums[0]

        result = nums[0]

        plusTemp = 0
        minusTemp = 0
        for num in nums:
            if num < 0:
                temp = minusTemp * num
                minusTemp = plusTemp * num
                plusTemp = temp
            else:
                plusTemp *= num
                minusTemp *= num

            if plusTemp == 0 and num > 0:
                plusTemp = num
            if minusTemp == 0 and num < 0:
                minusTemp = num

            result = max(result, plusTemp, minusTemp)

        return result


def testSol(func, input, output):
    res = func(input)
    if res == output:
        print(f"O : {res}")
    else:
        print(f"X : {res}	excpet : {output}")


if __name__ == "__main__":
    sol = Solution()

    testSol(sol.maxProduct, [2, 3, -2, 4], 6)
    testSol(sol.maxProduct, [-2, 0, -1], 0)
    testSol(sol.maxProduct, [0, 3, -2, 4], 4)
    testSol(sol.maxProduct, [2, 3, -2, 0], 6)
    testSol(sol.maxProduct, [-2], -2)
    testSol(sol.maxProduct, [-2, 4], 4)
    testSol(sol.maxProduct, [-2, -3, -2], 6)

