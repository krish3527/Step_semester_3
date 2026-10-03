package oops.assignment_problem;

import java.util.HashMap;

public class Ques3 {

    public static int countPeriods(int[] transactions, long k) {

        HashMap<Long, Integer> prefixCount = new HashMap<>();

        prefixCount.put(0L, 1);

        long prefixSum = 0;
        int count = 0;

        for (int value : transactions) {

            prefixSum += value;

            long required = prefixSum - k;

            if (prefixCount.containsKey(required)) {
                count += prefixCount.get(required);
            }

            prefixCount.put(prefixSum,
                    prefixCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {

        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        long k = 7;

        int result = countPeriods(transactions, k);

        System.out.println(result);
    }
}