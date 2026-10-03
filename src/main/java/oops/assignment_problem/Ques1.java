package oops.assignment_problem;

import java.util.ArrayList;
import java.util.List;

public class Ques1 {

    public static List<Integer> footfallReport(int[] visitors, int[][] queries) {

        int[] prefix = new int[visitors.length];

        prefix[0] = visitors[0];

        for (int i = 1; i < visitors.length; i++) {
            prefix[i] = prefix[i - 1] + visitors[i];
        }

        List<Integer> result = new ArrayList<>();

        for (int[] query : queries) {

            int start = query[0];
            int end = query[1];

            if (start == 0) {
                result.add(prefix[end]);
            } else {
                result.add(prefix[end] - prefix[start - 1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] visitors = {12, 7, 3, 9, 15, 4, 8};

        int[][] queries = {
            {0, 2},
            {2, 5},
            {4, 6},
            {3, 3}
        };

        List<Integer> result = footfallReport(visitors, queries);

        System.out.println(result);
    }
}