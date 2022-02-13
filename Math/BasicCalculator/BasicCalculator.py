# 224. Basic Calculator
# https://leetcode.com/problems/basic-calculator/


from typing import List


# 사칙연산 가능 계산기, 중위표현식을 후위표현식으로 바꾸는 정석?!
# 숫자는 바로 후위표현식에 넣음.
# 연산자는
#   연산자 스택에서 자기보다 우선순위가 높거나 같은 연산자를 빼서 후위표현식에 넣음,
#   자신을 연산자 스택에 넣음.
# 여는 괄호는 연산자 스택에 바로 넣음.
# 닫는 괄호는 여는 괄호를 만날 때까지 연산자 스택에서 빼서 후위표현식에 넣음.
# 끝나면 연산자 스택이 빌때까지 빼서 후위표현식에 넣음.
class Calculator:
    def calculate(self, s: str) -> int:
        s = s.replace(" ", "")
        self.op = {"(": 0, "*": 1, "/": 1, "+": 2, "-": 2}

        postExp = []
        opStack = []

        i = 0
        while i < len(s):
            if s[i].isdigit():
                temp = s[i]
                while i + 1 < len(s) and s[i + 1].isdigit():
                    i += 1
                    temp += s[i]
                postExp.append(temp)
            elif s[i] == ")":
                while opStack and opStack[-1] != "(":
                    postExp.append(opStack.pop())
                opStack.pop()
            elif s[i] in self.op:
                if (
                    opStack
                    and opStack[-1] != "("
                    and self.op[opStack[-1]] <= self.op[s[i]]
                ):
                    postExp.append(opStack.pop())
                opStack.append(s[i])

            i += 1

        while opStack:
            postExp.append(opStack.pop())

        print(postExp)
        return self.postfixCal(postExp)

    def postfixCal(self, exp: List[str]):
        stack = []
        for c in exp:
            if c.isdigit():
                stack.append(int(c))
            elif c in self.op:
                operand2 = stack.pop()
                operand1 = stack.pop() if stack else 0
                if c == "+":
                    stack.append(operand1 + operand2)
                elif c == "-":
                    stack.append(operand1 - operand2)
                elif c == "*":
                    stack.append(operand1 * operand2)
                elif c == "/":
                    stack.append(operand1 / operand2)

        return stack[0]


# O(n) : 후위표현식으로 변환 후 계산
# 으아 구현 구질구질하다.
class Solution:
    def calculate(self, s: str) -> int:
        s = s.replace(" ", "")
        self.op = set(["+", "-"])

        postExp = []
        opStack = []

        i = 0
        while i < len(s):
            if s[i].isdigit():
                temp = s[i]
                while i + 1 < len(s) and s[i + 1].isdigit():
                    i += 1
                    temp += s[i]
                postExp.append(temp)
                if opStack and opStack[-1] in self.op:
                    postExp.append(opStack.pop())

            elif s[i] in self.op:
                opStack.append(s[i])

            elif s[i] == "(":
                opStack.append(s[i])

            elif s[i] == ")":
                if opStack[-1] == "(":
                    opStack.pop()
                    if opStack:
                        postExp.append(opStack.pop())

            i += 1

        print(postExp)
        return self.postfixCal(postExp)

    def postfixCal(self, exp: List[str]):
        stack = []
        for c in exp:
            if c.isdigit():
                stack.append(int(c))
            elif c in self.op:
                operand2 = stack.pop()
                operand1 = stack.pop() if stack else 0
                if c == "+":
                    stack.append(operand1 + operand2)
                elif c == "-":
                    stack.append(operand1 - operand2)

        return stack[0]


def testSol(func, input, output):
    res = func(input)
    if res == output:
        print(f"O : {res}")
    else:
        print(f"X : {res}	excpet : {output}")


if __name__ == "__main__":
    sol = Solution()
    sol = Calculator()

    testSol(sol.calculate, "3210", 3210)
    testSol(sol.calculate, "(5)", 5)
    testSol(sol.calculate, "(1 + 1)", 2)
    testSol(sol.calculate, "1 + 1", 2)
    testSol(sol.calculate, " 2-1 + 2 ", 3)
    testSol(sol.calculate, "(1+(4+5+2)-3)+(6+8)", 23)
    testSol(sol.calculate, "(1+(4+54+2)-103)+(6+8)", -28)
    testSol(sol.calculate, "  -(  -2+3)", -1)
