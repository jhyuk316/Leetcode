package CodeTEST.programers.RankSearch;
// 순위 검색
// https://programmers.co.kr/learn/courses/30/lessons/72412

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// 효율성 0점 젠장
class Solution {
    Map<String, Set<Integer>> language;
    Map<String, Set<Integer>> jobs;
    Map<String, Set<Integer>> career;
    Map<String, Set<Integer>> foods;
    int[] scores;

    public int[] solution(String[] info, String[] query) {
        language = new HashMap<>();
        jobs = new HashMap<>();
        career = new HashMap<>();
        foods = new HashMap<>();
        scores = new int[info.length];

        language.put("cpp", new HashSet<>());
        language.put("java", new HashSet<>());
        language.put("python", new HashSet<>());

        jobs.put("backend", new HashSet<>());
        jobs.put("frontend", new HashSet<>());

        career.put("junior", new HashSet<>());
        career.put("senior", new HashSet<>());

        foods.put("chicken", new HashSet<>());
        foods.put("pizza", new HashSet<>());

        for (int i = 0; i < info.length; ++i) {
            String[] infos = info[i].split(" ");
            language.get(infos[0]).add(i);
            jobs.get(infos[1]).add(i);
            career.get(infos[2]).add(i);
            foods.get(infos[3]).add(i);
            scores[i] = Integer.parseInt(infos[4]);
        }

        // System.out.println(language);
        // System.out.println(jobs);
        // System.out.println(career);
        // System.out.println(foods);
        // System.out.println(Arrays.toString(scores));

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; ++i) {
            answer[i] = countInfo(query[i]);
        }
        return answer;
    }

    private int countInfo(String query) {
        String[] curQuery = query.split(" and ");
        String[] temp = curQuery[3].split(" ");
        String food = temp[0];
        int score = Integer.parseInt(temp[1]);

        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < scores.length; ++i) {
            result.add(i);
        }

        if (!curQuery[0].equals("-")) {
            result.retainAll(language.get(curQuery[0]));
            // System.out.println(result);
            if (result.isEmpty()) {
                return 0;
            }
        }
        if (!curQuery[1].equals("-")) {
            result.retainAll(jobs.get(curQuery[1]));
            // System.out.println(result);
            if (result.isEmpty()) {
                return 0;
            }
        }
        if (!curQuery[2].equals("-")) {
            result.retainAll(career.get(curQuery[2]));
            // System.out.println(result);
            if (result.isEmpty()) {
                return 0;
            }
        }
        if (!food.equals("-")) {
            result.retainAll(foods.get(food));
            // System.out.println(result);
            if (result.isEmpty()) {
                return 0;
            }
        }

        int count = 0;
        for (int id : result) {
            if (scores[id] >= score) {
                count++;
            }
        }
        return count;
    }
}


public class RankSearch {
    public static void main(String[] args) {
        testSol(new String[] {"java backend junior pizza 150", "python frontend senior chicken 210",
                "python frontend senior chicken 150", "cpp backend senior pizza 260",
                "java backend junior chicken 80", "python backend senior chicken 50"},
                new String[] {"java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250", "- and backend and senior and - 150",
                        "- and - and - and chicken 100", "- and - and - and - 150"},
                new int[] {1, 1, 1, 1, 2, 4});

    }

    static void testSol(String[] input1, String[] input2, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.solution(input1, input2);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }
}
