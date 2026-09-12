package oops.assignment_problem;

import java.util.Arrays;

class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory;
    private int feeCount;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().isEmpty()
                || attendeeId.length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID");
        }

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Base price must be positive");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        feeCount = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    protected void applyLateFee(double amount) {
        basePrice += amount;

        lateFeeHistory[feeCount] = amount;
        feeCount++;
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(String attendeeId, double basePrice) {
        super(attendeeId, basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Ques3 {

    public static void main(String[] args) {

        WorkshopTicket w =
            new WorkshopTicket("STU2", 1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();

        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(
            Arrays.toString(w.getLateFeeHistory())
        );
    }
}
