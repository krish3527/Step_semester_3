
package oops.practice_problem.session_12;

import java.util.Scanner;

abstract class Ride {
    double distance;

    Ride(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();
}

class Auto extends Ride {
    Auto(double d) {
        super(d);
    }

    double calculateFare() {
        return 30 + 15 * distance;
    }
}

class Cab extends Ride {
    double surge;

    Cab(double d, double surge) {
        super(d);
        this.surge = surge;
    }

    double calculateFare() {
        return (50 + 20 * distance) * surge;
    }
}

class Bike extends Ride {
    int waiting;

    Bike(double d, int waiting) {
        super(d);
        this.waiting = waiting;
    }

    double calculateFare() {
        return 20 + 10 * distance + 2 * waiting;
    }
}

public class Ques2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Ride[] rides = new Ride[n];

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double distance = sc.nextDouble();

            if (type.equalsIgnoreCase("Auto")) {
                rides[i] = new Auto(distance);
            } else if (type.equalsIgnoreCase("Cab")) {
                double surge = sc.nextDouble();
                rides[i] = new Cab(distance, surge);
            } else if (type.equalsIgnoreCase("Bike")) {
                int waiting = sc.nextInt();
                rides[i] = new Bike(distance, waiting);
            }
        }

        double total = 0;

        for (int i = 0; i < n; i++) {
            double fare = rides[i].calculateFare();
            System.out.println(
                rides[i].getClass().getSimpleName() + ": " +
                (long) fare
            );
            total += fare;
        }

        System.out.println("Total: " + (long) total);
        sc.close();
    }
}
