package oops.assignment_problem;

public class Ques4 {

    static final class SurgeFeeCalculator {

        private final double minimumSurgePercent;

        public SurgeFeeCalculator(double minimumSurgePercent) {
            this.minimumSurgePercent = minimumSurgePercent;
        }

        public final double calculateSurgeFee(double orderValue, int delayMinutes) {

            if (orderValue < 0 || delayMinutes < 0) {
                throw new IllegalArgumentException(
                    "Order value and delay minutes cannot be negative."
                );
            }

            if (delayMinutes == 0) {
                return 0.0;
            }

            double surgePercent = 0.0;

            int firstTier = Math.min(delayMinutes, 5);
            surgePercent += firstTier * 0.5;

            if (delayMinutes > 5) {
                int secondTier = Math.min(delayMinutes - 5, 10);
                surgePercent += secondTier * 1.0;
            }

            if (delayMinutes > 15) {
                int thirdTier = delayMinutes - 15;
                surgePercent += thirdTier * 2.0;
            }

            surgePercent = Math.max(surgePercent, minimumSurgePercent);

            return orderValue * surgePercent / 100.0;
        }
    }

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println(calculator.calculateSurgeFee(500, 0));
        System.out.println(calculator.calculateSurgeFee(500, 1));
        System.out.println(calculator.calculateSurgeFee(500, 16));
    }
}