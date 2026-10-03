package oops.assignment_problem;

public class Ques4 {

    public static int countInBand(int[] scores, int low, int high) {

        int first = firstGreaterOrEqual(scores, low);
        int last = firstGreater(scores, high);

        return last - first;
    }

    public static int firstGreaterOrEqual(int[] scores, int target) {

        int left = 0;
        int right = scores.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int firstGreater(int[] scores, int target) {

        int left = 0;
        int right = scores.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {

        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        int low = 42;
        int high = 58;

        int result = countInBand(scores, low, high);

        System.out.println(result);
    }
}