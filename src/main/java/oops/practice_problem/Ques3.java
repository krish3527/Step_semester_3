package oops.practice_problem;

import java.util.Arrays;

class EventTicket {
    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory;
    private int feeCount;

    public EventTicket(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Base price must be positive");
        }

        this.basePrice = basePrice;
        this.amountPaid = 0;
        this.lateFeeHistory = new double[10];
        this.feeCount = 0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }

    protected void applyLateFee(double amount) {
        if (amount > 0) {
            basePrice += amount;

            if (feeCount < lateFeeHistory.length) {
                lateFeeHistory[feeCount] = amount;
                feeCount++;
            }
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Ques3 {

    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();

        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}