package Math.NumberOfBurgersWithNoWasteOfIngredients;

// 1276. Number of Burgers with No Waste of Ingredients
// https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/description/


import java.util.List;

class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        double jumbo = (double) (tomatoSlices - 2 * cheeseSlices) / 2;
        double small = (double) (4 * cheeseSlices - tomatoSlices) / 2;

        System.out.println("jumbo = " + jumbo);
        System.out.println("small = " + small);

        if (jumbo < 0 || jumbo != (int) jumbo || small != (int) small || small < 0) {
            return List.of();
        }
        return List.of((int) jumbo, (int) small);
    }
}

// binary search
class Solution1 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int left = 0;
        int right = cheeseSlices;

        while (left <= right) {
            int mid = (left + right) / 2;
            int jumbo = mid;
            int small = cheeseSlices - mid;

            int tomato = 4 * jumbo + 2 * small;
            if (tomato > tomatoSlices) {
                right = mid - 1;
            } else if (tomato == tomatoSlices) {
                return List.of(jumbo, small);
            } else {
                left = mid + 1;
            }
        }
        return List.of();
    }
}

public class NumberOfBurgersWithNoWasteOfIngredients {
    public static void main(String[] args) {

    }
}
