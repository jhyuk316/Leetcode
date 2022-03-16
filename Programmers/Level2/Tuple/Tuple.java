package Programmers.Level2.Tuple;
// 튜플
// https://programmers.co.kr/learn/courses/30/lessons/64065

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String s) {
        s = s.replace("{{", "");
        s = s.replace("}}", "");

        List<Integer> answer = new ArrayList<>();
        String[] setArr = s.split("[}],[{]");
        List<Set<Integer>> setList = new ArrayList<>();
        for (String set : setArr) {
            if (!set.equals("")) {
                String[] numArr = set.split(",");
                // System.out.println(Arrays.toString(numArr));

                Set<Integer> tempSet = Arrays.stream(numArr).map(i -> Integer.parseInt(i))
                        .collect(Collectors.toSet());
                setList.add(tempSet);
            }
        }

        setList.sort(Comparator.comparingInt(set -> set.size()));

        for (Set<Integer> set : setList) {
            set.removeAll(answer);
            answer.addAll(set);
        }

        // System.out.println(Arrays.toString(setArr));

        return answer.stream().mapToInt(i -> i).toArray();
    }
}


public class Tuple {
    public static void main(String[] args) {
        testSol("{{2},{2,1},{2,1,3},{2,1,3,4}}", new int[] {2, 1, 3, 4});
        testSol("{{1,2,3},{2,1},{1,2,4,3},{2}}", new int[] {2, 1, 3, 4});
        testSol("{{20,111},{111}}", new int[] {111, 20});
        testSol("{{123}}", new int[] {123});
        testSol("{{4,2,3},{3},{2,3,4,1},{2,3}}", new int[] {3, 2, 4, 1});
    }

    private static void testSol(String input, int[] output) {
        Solution sol = new Solution();
        int[] res = sol.solution(input);
        if (Arrays.equals(res, output)) {
            System.out.println("O: " + Arrays.toString(res));
        } else {
            System.out.println(
                    "X: " + Arrays.toString(res) + "\t expect: " + Arrays.toString(output));
        }
    }
}
