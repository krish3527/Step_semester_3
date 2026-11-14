
package oops.practice_problem.session_12;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Ques1 {

    static class Order {

        static class LineItem {
            String productName;
            int quantity;
            double unitPrice;

            LineItem(String productName, int quantity, double unitPrice) {
                this.productName = productName;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
            }

            double total() {
                return quantity * unitPrice;
            }
        }

        LineItem[] items = new LineItem[100];
        int count = 0;

        void addItem(String name, int quantity, double price) {
            items[count++] = new LineItem(name, quantity, price);
        }

        class Cursor {
            int index = 0;

            boolean hasNext() {
                return index < count;
            }

            LineItem next() {
                return items[index++];
            }

            void reset() {
                index = 0;
            }
        }

        double orderTotal() {
            double sum = 0;
            Cursor c = new Cursor();

            while (c.hasNext()) {
                sum += c.next().total();
            }
            return sum;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.##");

        Order order = new Order();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int quantity = sc.nextInt();
            double price = sc.nextDouble();

            order.addItem(name, quantity, price);
        }

        Order.Cursor cursor = order.new Cursor();

        while (cursor.hasNext()) {
            Order.LineItem item = cursor.next();
            System.out.println(item.productName + " " + df.format(item.total()));
        }

        System.out.println("Total " + df.format(order.orderTotal()));

        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
        
        sc.close();
    }
}
