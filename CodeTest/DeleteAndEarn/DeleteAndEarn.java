package CodeTest.DeleteAndEarn;
// 740. Delete and Earn
// https://leetcode.com/problems/delete-and-earn/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// O(N*logN)
// dp[i] = max(dp[i-2] + sum[i], dp[i-1]) if num[i-1] == num[i]-1
// dp[i] = dp[i-1] + sum[i] if num[i-1] != num[i]-1
class Solution3 {
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        int[] indexArr = new int[nums.length]; // 숫자 set
        int[] sumArr = new int[nums.length]; // 숫자 합

        // 숫자의 set과 각 숫자의 합을 구함.
        int index = 0;
        indexArr[0] = nums[0];
        int temp = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] != nums[i]) {
                sumArr[index] = temp;
                indexArr[++index] = nums[i];
                temp = 0;
            }
            temp += nums[i];
        }
        sumArr[index] = temp;

        System.out.println(Arrays.toString(indexArr));
        System.out.println(Arrays.toString(sumArr));

        for (int i = 1; i < sumArr.length; ++i) {
            temp = sumArr[i];
            if (indexArr[i - 1] == indexArr[i] - 1) {
                temp = Math.max(sumArr[i - 1], sumArr[i]);
                if (i > 1) {
                    temp = Math.max(temp, sumArr[i] + sumArr[i - 2]);
                }
            } else {
                temp = Math.max(temp, sumArr[i] + sumArr[i - 1]);
            }
            sumArr[i] = temp;
        }

        return sumArr[sumArr.length - 1];
    }
}


// dp[i] = dp[i-2] + sum[i] if num[i-1] == num[i]-1
// dp[i] = dp[i-1] + sum[i] if num[i-1] != num[i]-1
class Solution {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int num : nums) {
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> sums = new ArrayList<>(numMap.entrySet());

        sums.sort(Comparator.comparing(Map.Entry::getKey));

        int maxEarn = sums.get(0).getValue();
        for (int i = 1; i < sums.size(); ++i) {
            int temp = sums.get(i).getValue();
            if (sums.get(i - 1).getKey() == sums.get(i).getKey() - 1) {
                temp = Math.max(sums.get(i - 1).getValue(), sums.get(i).getValue());
                if (i > 1) {
                    temp = Math.max(temp, sums.get(i).getValue() + sums.get(i - 2).getValue());
                }
                sums.get(i).setValue(temp);
            } else {
                temp = sums.get(i - 1).getValue() + sums.get(i).getValue();
                sums.get(i).setValue(temp);
            }
            maxEarn = Math.max(maxEarn, temp);
        }

        System.out.println(sums);


        return maxEarn;
    }
}


// dp[i] = dp[i-2] + sum[i] if num[i-1] == num[i]-1
// dp[i] = dp[i-1] + sum[i] if num[i-1] != num[i]-1
class Solution2 {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int num : nums) {
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> sums = new ArrayList<>(numMap.entrySet());

        System.out.println(sums);

        sums.stream().forEach(o1 -> o1.setValue(o1.getKey() * o1.getValue()));
        sums.sort(Comparator.comparing(Map.Entry::getKey));

        System.out.println(sums);

        int maxEarn = sums.get(0).getValue();
        for (int i = 1; i < sums.size(); ++i) {
            int temp = sums.get(i).getValue();
            if (sums.get(i - 1).getKey() == sums.get(i).getKey() - 1) {
                temp = Math.max(sums.get(i - 1).getValue(), sums.get(i).getValue());
                if (i > 1) {
                    temp = Math.max(temp, sums.get(i).getValue() + sums.get(i - 2).getValue());
                }
                sums.get(i).setValue(temp);
            } else {
                temp = sums.get(i - 1).getValue() + sums.get(i).getValue();
                sums.get(i).setValue(temp);
            }
            maxEarn = Math.max(maxEarn, temp);
        }

        System.out.println(sums);


        return maxEarn;
    }
}


// 완전탐색 타임리밋
class Solution1 {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int num : nums) {
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }

        System.out.println(numMap);

        return deleteAndEarn(0, numMap);
    }

    private int deleteAndEarn(int earn, Map<Integer, Integer> numMap) {
        if (numMap.isEmpty()) {
            return earn;
        }

        Map<Integer, Integer> tempMap;

        int maxEarn = 0;
        for (int num : numMap.keySet()) {
            // System.out.println(num + " " + numMap);
            tempMap = new HashMap<>(numMap);
            tempMap.remove(num - 1);
            tempMap.remove(num + 1);
            if (tempMap.get(num) == 1) {
                tempMap.remove(num);
            } else {
                tempMap.put(num, tempMap.get(num) - 1);
            }

            maxEarn = Math.max(maxEarn, deleteAndEarn(earn + num, tempMap));
        }

        return maxEarn;
    }
}


public class DeleteAndEarn {
    public static void main(String[] args) {
        testSol(new int[] {3, 1}, 4);
        testSol(new int[] {3, 4, 2}, 6);
        testSol(new int[] {2, 2, 3, 3, 3, 4}, 9);
        testSol(new int[] {2, 2, 3, 3, 3, 4, 4}, 12);
        testSol(new int[] {3, 5, 6}, 9);
        testSol(new int[] {1, 1, 1, 2, 4, 5, 5, 5, 6}, 18);
        testSol(new int[] {1, 2, 3, 15, 16, 17, 18}, 38);

    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.deleteAndEarn(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }


}
