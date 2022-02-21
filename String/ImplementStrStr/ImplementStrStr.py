# 28. Implement strStr()
# https://leetcode.com/problems/implement-strstr/

"""
C의 strStr() 구현 문제. 문자열에서 특정 문자열 위치 찾기.
일반적 브루트포스 방법 외에 KMP나 Rabin-Karp 방식이 있음.
알고리즘을 외우기 보다 그냥 시간복잡도만 외워도 충분할 듯.
O(m*n)이 아니라 O(m+n)에 해결 가능함.
"""

from typing import List


# O(m+n) Rabin-Karp sliding window로 해쉬값을 갱신하고, 찾는 문자열의 해쉬값으로 찾기.
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        if not needle:
            return 0

        # mod = 1000000009
        mod = 82595483
        base = 26

        def hashStr(s: str) -> int:
            res = 0
            for c in s:
                res *= base
                res += ord(c)
                res %= mod
            return res

        hashHay = hashStr(haystack[: len(needle) - 1])
        hashNeedle = hashStr(needle)

        prefixMod = base ** (len(needle) - 1) % mod

        left = 0
        right = len(needle) - 1
        while right < len(haystack):
            hashHay *= base
            hashHay += ord(haystack[right])
            hashHay %= mod

            if hashHay == hashNeedle and haystack[left : right + 1] == needle:
                return left

            hashHay -= ord(haystack[left]) * prefixMod
            hashHay %= mod
            if hashHay < 0:
                hashHay += mod

            right += 1
            left += 1

        return -1


# O(m+n) KMP 찾는 문자의 위치전이 배열을 만들고, 그에 따라 전진.
class Solution2:
    def strStr(self, haystack: str, needle: str) -> int:
        if not needle:
            return 0

        i = j = 0
        kmpList = self.kmp(needle)

        while i < len(haystack):
            if haystack[i] == needle[j]:
                i += 1
                j += 1
            elif j == 0:  # 매칭이 안될 경우
                i += 1
            else:
                j = kmpList[j - 1]

            if j == len(needle):
                return i - len(needle)

        return -1

    def kmp(self, needle: str) -> List[int]:
        kmpList = [0] * len(needle)
        j = 0
        i = 1

        while i < len(needle):
            if needle[i] == needle[j]:
                j += 1
                kmpList[i] = j
                i += 1
            elif j == 0:  # 매칭이 안될 경우
                i += 1
            else:
                j = kmpList[j - 1]

        return kmpList


# O(m*n) brute-force 모든 위치에서 needle과 같은 단어가 있는지 판단.
class Solution1:
    def strStr(self, haystack: str, needle: str) -> int:
        for i in range(len(haystack) - len(needle) + 1):
            if haystack[i : i + len(needle)] == needle:
                return i
        return -1


def testSol(func, input, output):
    res = func(*input)
    if res == output:
        print(f"O : {res}")
    else:
        print(f"X : {res}	excpet : {output}")


if __name__ == "__main__":
    sol = Solution()

    testSol(sol.strStr, ("hello", "ll"), 2)
    testSol(sol.strStr, ("aaaaa", "bba"), -1)
    testSol(sol.strStr, ("", ""), 0)
    testSol(sol.strStr, ("", "a"), -1)
    testSol(sol.strStr, ("a", "a"), 0)
    testSol(sol.strStr, ("abcabcdabckl", "abcdabckl"), 3)
    testSol(sol.strStr, ("abckkabcdabckk", "abcdabckl"), -1)
    testSol(sol.strStr, ("abcabcabck", "abcabck"), 3)
