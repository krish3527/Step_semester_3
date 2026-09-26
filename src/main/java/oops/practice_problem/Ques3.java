package oops.practice_problem;

import java.time.LocalDate;
import java.util.ArrayList;

abstract class HotelRoom {
    private String roomNumber;
    private boolean available;

    public HotelRoom(String roomNumber) {
        this.roomNumber = roomNumber;
        this.available = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends HotelRoom {

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 150;
    }
}

class DeluxeRoom extends HotelRoom {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 200;
    }
}

class SuiteRoom extends HotelRoom {

    public SuiteRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 300;
    }
}

class HotelCustomer {
    private String name;

    public HotelCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private HotelCustomer customer;
    private HotelRoom room;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private String status;

    public Reservation(
            HotelCustomer customer,
            HotelRoom room,
            LocalDate startDate,
            LocalDate endDate) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;

        int days =
                (int) (endDate.toEpochDay()
                - startDate.toEpochDay());

        this.totalPrice = room.calculatePrice(days);
        this.status = "Active";
    }

    public HotelRoom getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void cancel() {
        status = "Cancelled";
        room.setAvailable(true);
    }
}

class BookingManager {
    private ArrayList<HotelRoom> rooms;
    private ArrayList<Reservation> reservations;

    public BookingManager() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(HotelRoom room) {
        rooms.add(room);
    }

    private boolean hasOverlap(
            HotelRoom room,
            LocalDate startDate,
            LocalDate endDate) {

        for (Reservation reservation : reservations) {

            if (reservation.getRoom() == room
                    && reservation.getStatus().equals("Active")) {

                boolean overlap =
                        startDate.isBefore(
                                reservation.getEndDate())
                        &&
                        endDate.isAfter(
                                reservation.getStartDate());

                if (overlap) {
                    return true;
                }
            }
        }

        return false;
    }

    public Reservation bookRoom(
            HotelCustomer customer,
            HotelRoom room,
            LocalDate startDate,
            LocalDate endDate) {

        if (hasOverlap(room, startDate, endDate)) {

            System.out.println(
                    "Booking failed: " +
                    room.getRoomNumber() +
                    " is not available for " +
                    startDate +
                    " to " +
                    endDate +
                    "."
            );

            return null;
        }

        Reservation reservation =
                new Reservation(
                        customer,
                        room,
                        startDate,
                        endDate
                );

        reservations.add(reservation);

        room.setAvailable(false);

        System.out.println(
                room.getRoomNumber() +
                " booked from " +
                startDate +
                " to " +
                endDate +
                ". Total price: $" +
                String.format("%.2f",
                        reservation.getTotalPrice())
        );

        return reservation;
    }

    public void cancelReservation(
            Reservation reservation,
            LocalDate cancellationDate) {

        if (reservation == null) {
            return;
        }

        if (!reservation.getStatus().equals("Active")) {
            System.out.println(
                    "Reservation is already cancelled."
            );
            return;
        }

        if (cancellationDate.isBefore(
                reservation.getStartDate())) {

            reservation.cancel();

            System.out.println(
                    "Reservation for " +
                    reservation.getRoom().getRoomNumber() +
                    " cancelled successfully."
            );

        } else {

            System.out.println(
                    "Cancellation failed: " +
                    "Cancellation deadline has passed."
            );
        }
    }
}

public class Ques3 {

    public static void main(String[] args) {

        HotelCustomer customer =
                new HotelCustomer("Customer");

        HotelRoom deluxeRoom =
                new DeluxeRoom("Deluxe Room 101");

        HotelRoom standardRoom =
                new StandardRoom("Standard Room 205");

        BookingManager manager =
                new BookingManager();

        manager.addRoom(deluxeRoom);
        manager.addRoom(standardRoom);

        Reservation reservation1 =
                manager.bookRoom(
                        customer,
                        deluxeRoom,
                        LocalDate.of(2024, 12, 1),
                        LocalDate.of(2024, 12, 5)
                );

        Reservation reservation2 =
                manager.bookRoom(
                        customer,
                        standardRoom,
                        LocalDate.of(2024, 12, 3),
                        LocalDate.of(2024, 12, 7)
                );

        manager.bookRoom(
                customer,
                deluxeRoom,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        manager.cancelReservation(
                reservation1,
                LocalDate.of(2024, 11, 25)
        );
    }
}