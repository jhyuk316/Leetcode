package Implement.RangeSumQueryMutable;
// 307. Range Sum Query - Mutable
// https://leetcode.com/problems/range-sum-query-mutable/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Solution 세그먼트 트리
// 세그먼트 트리를 결국 공부해야하는 것인가?
class NumArray {
    int[] tree;
    int n;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; i++, j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else {
                left = pos - 1;
            }
            // parent is updated after child is updated
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    public int sumRange(int l, int r) {
        // get leaf with value 'l'
        l += n;
        // get leaf with value 'r'
        r += n;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
                sum += tree[l];
                l++;
            }
            if ((r % 2) == 0) {
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}



// 어거지 풀이
class NumArray1 {
    int[] arr;
    int[] preSum;
    int N;
    Map<Integer, Integer> diffMap = new HashMap<>();;
    final int maxSize = 15;

    public NumArray1(int[] nums) {
        N = nums.length;
        arr = nums.clone();

        preSum = new int[N + 1];
        calPreSum();
    }

    public void update(int index, int val) {
        diffMap.put(index, val - arr[index]);

        if (diffMap.size() > maxSize) {
            for (var diff : diffMap.entrySet()) {
                arr[diff.getKey()] += diff.getValue();
            }
            calPreSum();
            diffMap.clear();
        }
    }

    public int sumRange(int left, int right) {
        int sum = preSum[right + 1] - preSum[left];
        for (var diff : diffMap.entrySet()) {
            if (left <= diff.getKey() && diff.getKey() <= right) {
                sum += diff.getValue();
            }
        }
        return sum;
    }

    private void calPreSum() {
        for (int i = 0; i < N; ++i) {
            preSum[i + 1] = preSum[i] + arr[i];
        }
    }
}


public class RangeSumQueryMutable {
    public static void main(String[] args) {

    }
}
