package oops.practice_problem;

abstract class Vehicle {
    private String name;
    private boolean available;

    public Vehicle(String name) {
        this.name = name;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {

    public StandardCar(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {

    public LuxuryCar(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100;
    }
}

class SUV extends Vehicle {

    public SUV(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 80;
    }
}

class RentalCustomer {
    private String name;

    public RentalCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private RentalCustomer customer;
    private Vehicle vehicle;
    private int days;
    private double totalCharge;
    private boolean active;

    public Rental(RentalCustomer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalCharge = vehicle.calculateCharge(days);
        this.active = true;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public boolean isActive() {
        return active;
    }

    public void closeRental() {
        active = false;
    }
}

class RentalService {

    public Rental rentVehicle(
            RentalCustomer customer,
            Vehicle vehicle,
            int days) {

        if (!vehicle.isAvailable()) {
            System.out.println(
                    vehicle.getName() +
                    " is already rented."
            );
            return null;
        }

        Rental rental =
                new Rental(customer, vehicle, days);

        vehicle.setAvailable(false);

        System.out.printf(
                "%s rented for %d days. Total charge: $%.2f%n",
                vehicle.getName(),
                days,
                rental.getTotalCharge()
        );

        return rental;
    }

    public void returnVehicle(Rental rental) {

        if (rental == null || !rental.isActive()) {
            return;
        }

        Vehicle vehicle = rental.getVehicle();

        rental.closeRental();
        vehicle.setAvailable(true);

        System.out.println(
                vehicle.getName() +
                " returned. Now available."
        );
    }
}

public class Ques2 {

    public static void main(String[] args) {

        RentalCustomer customer =
                new RentalCustomer("Customer");

        Vehicle luxuryCar =
                new LuxuryCar("Luxury Car A");

        Vehicle standardCar =
                new StandardCar("Standard Car B");

        RentalService service =
                new RentalService();

        Rental luxuryRental =
                service.rentVehicle(
                        customer,
                        luxuryCar,
                        3
                );

        Rental standardRental =
                service.rentVehicle(
                        customer,
                        standardCar,
                        5
                );

        service.returnVehicle(luxuryRental);
    }
}