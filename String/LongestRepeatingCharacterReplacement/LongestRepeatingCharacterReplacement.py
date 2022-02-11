# 424. Longest Repeating Character Replacement
# https://leetcode.com/problems/longest-repeating-character-replacement/

from collections import defaultdict
from email.policy import default
from typing import Dict


# O(n) two-pointer
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        maxLength = 0
        cMap = defaultdict(int)

        left = 0
        maxCount = 0
        for right, c in enumerate(s):
            cMap[c] += 1
            maxCount = max(maxCount, cMap[c])

            while right - left + 1 - maxCount > k:
                cMap[s[left]] -= 1
                left += 1

            maxLength = max(maxLength, right - left + 1)

        return maxLength


# O(n) two-pointer 매번 최댓값 찾고 검사. 느림.
class Solution1:
    def characterReplacement(self, s: str, k: int) -> int:
        maxLength = 0
        cMap = {}

        left = 0
        length = 0
        for c in s:
            length += 1
            if c in cMap:
                cMap[c] += 1
            else:
                cMap[c] = 1

            # check
            maxChar = max(cMap, key=cMap.get)

            while length - cMap[maxChar] > k:
                if cMap[s[left]] == 1:
                    cMap.pop(s[left])
                else:
                    cMap[s[left]] -= 1
                length -= 1
                left += 1
                maxChar = max(cMap, key=cMap.get)
                print(cMap)

            print(maxChar)

            maxLength = max(maxLength, length)

        return maxLength


def testSol(func, input, output):
    res = func(*input)
    if res == output:
        print(f"O : {res}")
    else:
        print(f"X : {res}	excpet : {output}")


if __name__ == "__main__":
    sol = Solution()

    testSol(sol.characterReplacement, ("ABAB", 2), 4)
    testSol(sol.characterReplacement, ("AABABBA", 1), 4)
    testSol(sol.characterReplacement, ("ABBBABAAA", 2), 6)
    testSol(sol.characterReplacement, ("ABBBABAAA", 2), 5)

