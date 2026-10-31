
package oops.assignment_problem.session_11;

import java.util.Stack;
import java.util.Arrays;

public class Ques1 {

    public static int[] priceStreak(int[] price) {

        int n = price.length;
        int[] result = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && price[st.peek()] <= price[i]) {
                st.pop();
            }

            if (st.isEmpty()) {
                result[i] = i + 1;
            } else {
                result[i] = i - st.peek();
            }

            st.push(i);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] price = {100, 80, 60, 70, 60, 75, 85};

        int[] result = priceStreak(price);

        System.out.println("Price: " + Arrays.toString(price));

        System.out.println("Price Streak: "
                + Arrays.toString(result));

        int[] price2 = {10, 20, 30};

        System.out.println("Price: " + Arrays.toString(price2));

        System.out.println("Price Streak: "
                + Arrays.toString(priceStreak(price2)));
    }
}
