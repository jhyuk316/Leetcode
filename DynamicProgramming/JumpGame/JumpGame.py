# 55. Jump Game
# https://leetcode.com/problems/jump-game/

from typing import List


# O(n) 해답, 도달 가능한 곳 저장
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        reach = 0

        for i, num in enumerate( nums):
            if i > reach:
                return False
            reach = max(reach, i + num)
        
        return True
            

# O(n^2) 도달 가능 여부 저장
class Solution1:
    def canJump(self, nums: List[int]) -> bool:
        isJump = [False] * len(nums)

        for i in reversed(range(len(nums))):
            index = i + nums[i]
            if index >= len(nums) - 1:
                isJump[i] = True
            for j in range(i, index + 1):
                if isJump[j]:
                    isJump[i] = True
                    break

        return isJump[0]


def testSol(func, input, output):
    res = func(input)
    if res == output:
        print(f"O : {res}")
    else:
        print(f"X : {res}	excpet : {output}")


if __name__ == "__main__":
    sol = Solution()

    testSol(sol.canJump, [2, 3, 1, 1, 4], True)
    testSol(sol.canJump, [3, 2, 1, 0, 4], False)
    testSol(sol.canJump, [0], True)
    testSol(sol.canJump, [3, 10, 0, 0, 0, 0, 0], True)

