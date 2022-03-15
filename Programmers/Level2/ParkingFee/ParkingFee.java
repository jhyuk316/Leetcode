package Programmers.Level2.ParkingFee;
// 주차 요금 계산
// https://programmers.co.kr/learn/courses/30/lessons/92341

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> carMap = new HashMap<>();
        Map<String, Integer> carTotalTimeMap = new HashMap<>();

        for (int i = 0; i < records.length; ++i) {
            String[] record = records[i].split(" ");
            String timeStr = record[0];
            String carNumber = record[1];
            String inOut = record[2];

            if (inOut.equals("IN")) {
                carMap.put(carNumber, calculateTime(timeStr));
            } else if (inOut.equals("OUT")) {
                int startTime = carMap.get(carNumber);
                int endTime = calculateTime(timeStr);
                int duration = endTime - startTime;
                carTotalTimeMap.put(carNumber,
                        carTotalTimeMap.getOrDefault(carNumber, 0) + duration);
                carMap.remove(carNumber);
            }
        }

        // 남은차
        int lastTime = 23 * 60 + 59;
        for (Map.Entry<String, Integer> car : carMap.entrySet()) {
            int startTime = car.getValue();
            int duration = lastTime - startTime;
            carTotalTimeMap.put(car.getKey(),
                    carTotalTimeMap.getOrDefault(car.getKey(), 0) + duration);
        }

        // 요금 계산
        List<Map.Entry<String, Integer>> carList = new ArrayList<>(carTotalTimeMap.entrySet());
        carList.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));

        System.out.println(carList);

        int[] answer = new int[carList.size()];
        for (int i = 0; i < carList.size(); ++i) {
            answer[i] = calculateFee(fees, carList.get(i).getValue());
        }
        return answer;
    }

    private int calculateTime(String time) {
        String[] timeStr = time.split(":");
        return 60 * Integer.parseInt(timeStr[0]) + Integer.parseInt(timeStr[1]);
    }

    private int calculateFee(int[] fees, int duration) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int extraTime = fees[2];
        int extraFee = fees[3];

        if (duration >= basicTime) {
            return basicFee
                    + (int) Math.ceil((double) (duration - basicTime) / extraTime) * extraFee;
        } else {
            return basicFee;
        }
    }
}


public class ParkingFee {
    public static void main(String[] args) {
        testSol(new int[] {180, 5000, 10, 600},
                new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
                        "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN",
                        "23:00 5961 OUT"},
                new int[] {14600, 34400, 5000});
        testSol(new int[] {120, 0, 60, 591}, new String[] {"16:00 3961 IN", "16:00 0202 IN",
                "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"}, new int[] {0, 591});
        testSol(new int[] {1, 461, 1, 10}, new String[] {"00:00 1234 IN"}, new int[] {14841});

    }

    static void testSol(int[] input1, String[] input2, int[] output) {
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
