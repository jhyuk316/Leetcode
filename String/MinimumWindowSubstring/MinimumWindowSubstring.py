# 76. Minimum Window Substring
# https://leetcode.com/problems/minimum-window-substring/

"""
투포인트로 접근 하지만 너무 복잡하고 어려움.
당연하게도 실행 속도가 느림. 264ms, 18.48%보다 빠름.
복잡도도 정확히 계산을 못하겠음.
"""

from collections import defaultdict

# two-pointer
# 공간 최적화
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        tDict = defaultdict(int)
        for c in t:
            tDict[c] += 1

        tCount = len(tDict)

        left = 0
        right = 0
        maxLength = 100001
        res = ""
        while right < len(s):
            tDict[s[right]] -= 1
            if tDict[s[right]] == 0:
                tCount -= 1

            while tCount == 0:
                if maxLength > right - left:
                    res = s[left : right + 1]
                    maxLength = len(res)

                if tDict[s[left]] == 0:
                    tCount += 1

                tDict[s[left]] += 1
                left += 1
            right += 1

        return res


# two-pointer
class Solution2:
    def minWindow(self, s: str, t: str) -> str:
        tDict = defaultdict(int)

        for c in t:
            tDict[c] += 1

        print(tDict)

        tCount = len(tDict)
        matchCount = 0

        left = 0
        right = 0
        sDict = defaultdict(int)
        maxLength = 100001
        res = ""
        while right < len(s):
            c1 = s[right]
            sDict[c1] += 1
            if c1 in tDict and tDict[c1] == sDict[c1]:
                matchCount += 1

            if matchCount == tCount:
                while left <= right and matchCount == tCount:
                    c2 = s[left]
                    sDict[c2] -= 1
                    if c2 in tDict and sDict[c2] < tDict[c2]:
                        matchCount -= 1
                    left += 1

                if maxLength > right - left:
                    maxLength = right - left
                    res = s[left - 1 : right + 1]
            right += 1

        return res


# O(n+m)? O(mn)? 체크가 느림.
class Solution1:
    def minWindow(self, s: str, t: str) -> str:
        if len(s) < len(t):
            return ""

        left = 0
        right = 0

        tDict = {}
        answer = []

        for c in t:
            if c in tDict:
                tDict[c] += 1
            else:
                tDict[c] = 1

        while s[left] not in tDict:
            left += 1
            if left >= len(s):
                return ""

        right = left

        while right < len(s):
            # print("lr ", left, right, tDict)
            if s[right] in tDict:
                tDict[s[right]] -= 1

            while self.checkDict(tDict):
                answer.append([left, right])

                tDict[s[left]] += 1

                left += 1
                while left < right and s[left] not in tDict:
                    left += 1

            right += 1

        if not answer:
            return ""

        lenAnswer = list(map(lambda x: x[1] - x[0], answer))
        pos = lenAnswer.index(min(lenAnswer))
        l = answer[pos][0]
        r = answer[pos][1] + 1

        return s[l:r]

    def checkDict(self, d: dict):
        for val in d.values():
            if val > 0:
                return False
        return True


if __name__ == "__main__":
    sol = Solution()

    print(sol.minWindow("ADOBECODEBANC", "ABC"))
    print(sol.minWindow("DDDADOBECODEBANC", "ABC"))
    print(sol.minWindow("DDDABBBBCCA", "ABC"))
    print(sol.minWindow("a", "a"))
    print(sol.minWindow("a", "aa"))
    print(sol.minWindow("aa", "aa"))
    print(sol.minWindow("a", "b"))
