package oops.assignment_problem;

import java.util.ArrayList;

interface PricingPlan {
    double calculatePrice(double price);
    String getPlanName();
}

class DayScholarPlan implements PricingPlan {

    @Override
    public double calculatePrice(double price) {
        return price;
    }

    @Override
    public String getPlanName() {
        return "Day Scholar";
    }
}

class HostellerPlan implements PricingPlan {

    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }

    @Override
    public String getPlanName() {
        return "Hosteller";
    }
}

class StaffPlan implements PricingPlan {

    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }

    @Override
    public String getPlanName() {
        return "Staff";
    }
}

class FoodItem {

    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class CardTransaction {

    private String type;
    private double amount;
    private String description;

    public CardTransaction(
            String type,
            double amount,
            String description) {

        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getSignedAmount() {

        if (type.equals("TOP_UP")
                || type.equals("REFUND")) {

            return amount;
        }

        return -amount;
    }
}

class SmartCard {

    private String cardId;
    private PricingPlan pricingPlan;

    private double balance;
    private String status;

    private ArrayList<CardTransaction> transactions;

    public SmartCard(
            String cardId,
            PricingPlan pricingPlan) {

        this.cardId = cardId;
        this.pricingPlan = pricingPlan;
        this.balance = 0;
        this.status = "ACTIVE";
        this.transactions = new ArrayList<>();
    }

    public String getCardId() {
        return cardId;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public void topUp(double amount) {

        if (status.equals("BLOCKED")) {

            System.out.println(
                    "Top-up rejected: Card " +
                    cardId +
                    " is blocked."
            );

            return;
        }

        if (amount < 100) {

            System.out.println(
                    "Top-up rejected: Minimum top-up is ₹100."
            );

            return;
        }

        if (balance + amount > 5000) {

            System.out.println(
                    "Top-up rejected: Maximum balance is ₹5000."
            );

            return;
        }

        CardTransaction transaction =
                new CardTransaction(
                        "TOP_UP",
                        amount,
                        "Top-up"
                );

        transactions.add(transaction);

        balance += amount;

        System.out.printf(
                "Top-up successful: ₹%.2f. Balance: ₹%.2f%n",
                amount,
                balance
        );
    }

    public void purchase(FoodItem item) {

        if (status.equals("BLOCKED")) {

            System.out.println(
                    "Purchase rejected: Card " +
                    cardId +
                    " is blocked."
            );

            return;
        }

        double chargedAmount =
                pricingPlan.calculatePrice(
                        item.getPrice()
                );

        if (balance < chargedAmount) {

            System.out.printf(
                    "Purchase failed: Required ₹%.2f, available ₹%.2f.%n",
                    chargedAmount,
                    balance
            );

            return;
        }

        CardTransaction transaction =
                new CardTransaction(
                        "PURCHASE",
                        chargedAmount,
                        item.getName()
                );

        transactions.add(transaction);

        balance -= chargedAmount;

        System.out.printf(
                "%s purchased: ₹%.2f charged. Balance: ₹%.2f%n",
                item.getName(),
                chargedAmount,
                balance
        );
    }

    public void refund(FoodItem item) {

        if (status.equals("BLOCKED")) {

            System.out.println(
                    "Refund rejected: Card " +
                    cardId +
                    " is blocked."
            );

            return;
        }

        double refundAmount =
                pricingPlan.calculatePrice(
                        item.getPrice()
                );

        for (CardTransaction transaction : transactions) {

            if (transaction.getType().equals("REFUND")
                    && transaction.getDescription()
                    .equals(item.getName())) {

                System.out.println(
                        "Refund rejected: " +
                        item.getName() +
                        " has already been refunded."
                );

                return;
            }
        }

        boolean purchaseFound = false;

        for (CardTransaction transaction : transactions) {

            if (transaction.getType().equals("PURCHASE")
                    && transaction.getDescription()
                    .equals(item.getName())) {

                purchaseFound = true;
                break;
            }
        }

        if (!purchaseFound) {

            System.out.println(
                    "Refund rejected: No purchase found for " +
                    item.getName() + "."
            );

            return;
        }

        CardTransaction transaction =
                new CardTransaction(
                        "REFUND",
                        refundAmount,
                        item.getName()
                );

        transactions.add(transaction);

        balance += refundAmount;

        System.out.printf(
                "Refund successful: ₹%.2f. Balance: ₹%.2f%n",
                refundAmount,
                balance
        );
    }

    public void blockCard() {

        status = "BLOCKED";

        System.out.println(
                "Card " +
                cardId +
                " is now BLOCKED."
        );
    }

    public void unblockCard() {

        status = "ACTIVE";

        System.out.println(
                "Card " +
                cardId +
                " is now ACTIVE."
        );
    }

    public void miniStatement() {

        System.out.println();
        System.out.println(
                "Mini Statement - " +
                cardId
        );

        double calculatedBalance = 0;

        for (CardTransaction transaction : transactions) {

            double amount =
                    transaction.getSignedAmount();

            calculatedBalance += amount;

            if (amount >= 0) {

                System.out.printf(
                        "+₹%.2f  %s%n",
                        amount,
                        transaction.getDescription()
                );

            } else {

                System.out.printf(
                        "-₹%.2f  %s%n",
                        -amount,
                        transaction.getDescription()
                );
            }
        }

        System.out.printf(
                "Balance: ₹%.2f%n",
                calculatedBalance
        );
    }
}

public class Ques5 {

    public static void main(String[] args) {

        SmartCard card =
                new SmartCard(
                        "C-2045",
                        new HostellerPlan()
                );

        FoodItem vegThali =
                new FoodItem(
                        "Veg Thali",
                        120
                );

        FoodItem coldCoffee =
                new FoodItem(
                        "Cold Coffee",
                        60
                );

        card.topUp(500);

        card.purchase(vegThali);

        card.purchase(coldCoffee);

        FoodItem expensiveItem =
                new FoodItem(
                        "Large Meal",
                        400
                );

        card.purchase(expensiveItem);

        card.refund(vegThali);

        card.refund(vegThali);

        card.miniStatement();
    }
}