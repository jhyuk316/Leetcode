package Array.MyCalendarI;
// 729. My Calendar I
// https://leetcode.com/problems/my-calendar-i/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

// O(N*LogN) soution ???
class MyCalendar {
    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> e = map.floorEntry(end - 1);
        if (e != null && e.getValue() > start)
            return false;
        map.put(start, end);
        return true;
    }
}


// O(N*LogN) soution TreeMap
class MyCalendar2 {
    TreeMap<Integer, Integer> calendars;

    public MyCalendar2() {
        calendars = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer floorKey = calendars.floorKey(start);
        if (floorKey != null && calendars.get(floorKey) > start) {
            return false;
        }

        Integer higherKey = calendars.higherKey(start);
        if (higherKey != null && higherKey < end) {
            return false;
        }

        calendars.put(start, end);
        return true;
    }
}


// O(N*LogN) binarySertch
class MyCalendar1 {
    List<int[]> calendar;

    public MyCalendar1() {
        calendar = new LinkedList<>();
    }

    public boolean book(int start, int end) {
        int prePositon = binarySearch(start) - 1;
        if (prePositon >= 0) {
            int[] left = calendar.get(prePositon);
            // System.out.println("left " + left[0] + " " + left[1]);
            if (start < left[1]) {
                return false;
            }
        }

        int insertPositon = prePositon + 1;
        if (insertPositon < calendar.size()) {
            int[] right = calendar.get(insertPositon);
            // System.out.println("right " + right[0] + " " + right[1]);
            if (right[0] < end) {
                return false;
            }
        }

        calendar.add(insertPositon, new int[] {start, end});

        // System.out.print("calendar: ");
        // calendar.forEach((i) -> System.out.print("[" + i[0] + ", " + i[1] + "]"));
        // System.out.println();
        return true;
    }

    private int binarySearch(int start) {
        int left = 0;
        int right = calendar.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (calendar.get(mid)[0] == start) {
                return mid;
            } else if (calendar.get(mid)[0] < start) {
                left = mid + 1;
            } else if (calendar.get(mid)[0] > start) {
                right = mid - 1;
            }
        }
        return left;
    }
}


public class MyCalendarI {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));
        System.out.println("\n");

        MyCalendar myCalendar1 = new MyCalendar();
        System.out.println(myCalendar1.book(47, 50));
        System.out.println(myCalendar1.book(33, 41));
        System.out.println(myCalendar1.book(39, 45));
        System.out.println("\n");

        MyCalendar myCalendar2 = new MyCalendar();
        System.out.println(myCalendar2.book(33, 51));
        System.out.println(myCalendar2.book(19, 30));
        System.out.println(myCalendar2.book(33, 51));
        System.out.println(myCalendar2.book(13, 32));
        System.out.println("\n");
    }
}
