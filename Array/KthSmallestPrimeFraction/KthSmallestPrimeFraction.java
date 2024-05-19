package Array.KthSmallestPrimeFraction;
// 786. K-th Smallest Prime Fraction
// https://leetcode.com/problems/k-th-smallest-prime-fraction/description/

// 실패한 솔루션
import java.util.*;

class Solution {

    public static class Point {
        final int i;
        final int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {

        List<Point> sortList = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                sortList.add(new Point(arr[i], arr[j]));
            }
        }

        sortList.sort(Comparator.comparingDouble(p -> (double) p.i / p.j));

        Point point = sortList.get(k - 1);
        return new int[]{point.i, point.j};
    }
}

class Solution1 {

    public static class Point {
        final int i;
        final int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            Point point = (Point) object;
            return i == point.i && j == point.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int[] answer = new int[2];
        Set<Point> visit = new HashSet<>();

        Deque<Point> queue = new LinkedList<>();

        Point start = new Point(0, arr.length - 1);
        visit.add(start);
        queue.add(start);

        // System.out.println("queue = " + queue);

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            System.out.println("depth = " + depth);

            List<Point> temp = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                Point peek = queue.pollFirst();
                temp.add(peek);

                System.out.println("peek = " + peek);
                Point e = new Point(peek.i + 1, peek.j);
                if (peek.i + 1 < arr.length - 1 && !visit.contains(e)) {
                    visit.add(e);
                    queue.add(e);
                }
                Point e1 = new Point(peek.i, peek.j - 1);
                if (peek.j - 1 >= 0 && !visit.contains(e1)) {
                    visit.add(e1);
                    queue.add(e1);
                }
            }

            System.out.println("k = " + k);
            System.out.println("temp.size() = " + temp.size());

            if (k > temp.size()) {
                k -= temp.size();
            } else {
                temp.sort(Comparator.comparingDouble(p -> (double) arr[p.i] / arr[p.j]));
                System.out.println("temp = " + temp);

                Point point = temp.get(k - 1);
                return new int[]{arr[point.i], arr[point.j]};
            }
        }

        return answer;
    }

}

public class KthSmallestPrimeFraction {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("=== ints = " + Arrays.toString(solution.kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 3)));
        System.out.println("=== ints = " + Arrays.toString(solution.kthSmallestPrimeFraction(new int[]{1, 7}, 1)));
        System.out.println("=== ints = " + Arrays.toString(solution.kthSmallestPrimeFraction(new int[]{1, 7, 23, 29, 47}, 8)));

        int[] ints = {1, 7, 23, 29, 47};
        List<Map.Entry<Integer, Integer>> p = new ArrayList<>();
        for (int i = 0; i < ints.length; ++i) {
            for (int j = i + 1; j < ints.length; ++j) {
                p.add(Map.entry(ints[i], ints[j]));
            }
        }

        p.sort(Comparator.comparingDouble(e -> (double) e.getKey() / e.getValue()));

        p.forEach(System.out::println);

    }

}
