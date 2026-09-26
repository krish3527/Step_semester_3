package oops.practice_problem;

import java.util.ArrayList;

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

class LineItem {
    private FoodItem foodItem;
    private int quantity;

    public LineItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return foodItem.getPrice() * quantity;
    }

    public String getItemName() {
        return foodItem.getName();
    }

    public int getQuantity() {
        return quantity;
    }
}

interface IPaymentMethod {
    boolean pay(double amount);
    String getPaymentName();
}

class CreditCardPayment implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println(
                "Payment via Credit Card successful."
        );
        return true;
    }

    @Override
    public String getPaymentName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println(
                "Payment via Digital Wallet failed."
        );
        return false;
    }

    @Override
    public String getPaymentName() {
        return "Digital Wallet";
    }
}

class CashOnDeliveryPayment implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println(
                "Payment via Cash on Delivery successful."
        );
        return true;
    }

    @Override
    public String getPaymentName() {
        return "Cash on Delivery";
    }
}

class FoodCustomer {
    private String name;

    public FoodCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void notifyCustomer(String message) {
        System.out.println(
                "Notification: " + message
        );
    }
}

class Restaurant {
    private String name;
    private ArrayList<FoodItem> foodItems;

    public Restaurant(String name) {
        this.name = name;
        foodItems = new ArrayList<>();
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
    }

    public String getName() {
        return name;
    }
}

class FoodOrder {
    private static int nextOrderId = 123;

    private int orderId;
    private FoodCustomer customer;
    private Restaurant restaurant;
    private ArrayList<LineItem> items;
    private String status;

    public FoodOrder(
            FoodCustomer customer,
            Restaurant restaurant) {

        this.orderId = nextOrderId++;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>();
        this.status = "Created";

        System.out.println("Order created.");
    }

    public int getOrderId() {
        return orderId;
    }

    public void addItem(
            FoodItem foodItem,
            int quantity) {

        LineItem item =
                new LineItem(foodItem, quantity);

        items.add(item);

        System.out.println(
                "Added " +
                foodItem.getName() +
                " (Qty " +
                quantity +
                ")"
        );
    }

    private double calculateTotal() {

        double total = 0;

        for (LineItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public void placeOrder(
            IPaymentMethod paymentMethod) {

        if (items.isEmpty()) {

            System.out.println(
                    "Cannot place order: " +
                    "Order must contain at least one item."
            );

            return;
        }

        status = "Placed";

        System.out.println(
                "Order placed successfully."
        );

        double total = calculateTotal();

        boolean paymentSuccessful =
                paymentMethod.pay(total);

        if (paymentSuccessful) {

            status = "Paid";

            customer.notifyCustomer(
                    "Order #" +
                    orderId +
                    " placed and paid."
            );

        } else {

            status = "Pending Payment";

            customer.notifyCustomer(
                    "Order #" +
                    orderId +
                    " placed, awaiting payment."
            );
        }

        System.out.println(
                "Order status: " +
                status
        );
    }
}

public class Ques5 {

    public static void main(String[] args) {

        FoodCustomer customer =
                new FoodCustomer("Customer");

        Restaurant restaurant =
                new Restaurant("Food Restaurant");

        FoodItem pizza =
                new FoodItem("Pizza", 200);

        FoodItem soda =
                new FoodItem("Soda", 50);

        FoodItem burger =
                new FoodItem("Burger", 150);

        restaurant.addFoodItem(pizza);
        restaurant.addFoodItem(soda);
        restaurant.addFoodItem(burger);

        FoodOrder order1 =
                new FoodOrder(
                        customer,
                        restaurant
                );

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        IPaymentMethod creditCard =
                new CreditCardPayment();

        order1.placeOrder(creditCard);

        FoodOrder order2 =
                new FoodOrder(
                        customer,
                        restaurant
                );

        order2.addItem(burger, 1);

        IPaymentMethod digitalWallet =
                new DigitalWalletPayment();

        order2.placeOrder(digitalWallet);
    }
}