package oops.practice_problem;

public class Ques4 {

    static int lowerBound(int[] prices, int target) {

        int low = 0;
        int high = prices.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (prices[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    static int upperBound(int[] prices, int target) {

        int low = 0;
        int high = prices.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (prices[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {

        int[] prices = {100, 150, 150, 200, 300, 450};

        int lowerPrice = 150;
        int upperPrice = 300;

        int lowerIndex = lowerBound(prices, lowerPrice);
        int upperIndex = upperBound(prices, upperPrice);

        int count = upperIndex - lowerIndex;

        System.out.println("Lower bound index " + lowerIndex);
        System.out.println("Upper bound index " + upperIndex);
        System.out.println("Count " + count);
    }
}