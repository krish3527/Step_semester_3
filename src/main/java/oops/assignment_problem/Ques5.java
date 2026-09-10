package oops.assignment_problem;

public class Ques5 {

    static class DeliveryAccount {

        protected String studentId;
        protected double orderValue;

        static {
            System.out.println("Delivery reconciliation system initialized.");
        }

        public DeliveryAccount(String studentId, double orderValue) {
            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, 0.0);
        }

        public final double calculateSurgeFee(int delayMinutes) {

            if (delayMinutes < 0) {
                throw new IllegalArgumentException(
                    "Delay minutes cannot be negative."
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

            return orderValue * surgePercent / 100.0;
        }
    }

    static class Premium extends DeliveryAccount {

        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }
    }

    static double grandTotal = 0.0;
    static int processed = 0;
    static int nullSkipped = 0;
    static int premiumCount = 0;
    static int regularCount = 0;

    static void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            nullSkipped++;
            return;
        }

        account.orderValue = amount;

        double surgeFee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof Premium) {
            surgeFee = surgeFee * 0.80;
            premiumCount++;
        } else {
            regularCount++;
        }

        grandTotal += surgeFee;
        processed++;

        System.out.println(
            account.studentId + " -> Surge Fee: Rs " + surgeFee
        );
    }

    static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        int count = Math.min(
            accounts.length,
            Math.min(amounts.length, delayMinutesArray.length)
        );

        for (int i = 0; i < count; i++) {

            processAccount(
                accounts[i],
                amounts[i],
                delayMinutesArray[i]
            );
        }

        System.out.println();
        System.out.println(processed + " processed");
        System.out.println(nullSkipped + " null skipped");
        System.out.println(premiumCount + " premium");
        System.out.println(regularCount + " regular");
        System.out.println(
            "grand total surge fees = Rs " + grandTotal
        );
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500,
            400,
            300
        };

        int[] delayMinutesArray = {
            10,
            5,
            0
        };

        processBatch(
            accounts,
            amounts,
            delayMinutesArray
        );
    }
}