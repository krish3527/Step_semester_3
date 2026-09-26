package oops.assignment_problem;

import java.util.ArrayList;

interface ShippingType {
    double calculateCharge(double weight);
    String getName();
}

class StandardShipping implements ShippingType {

    @Override
    public double calculateCharge(double weight) {
        return 40 + (10 * weight);
    }

    @Override
    public String getName() {
        return "Standard";
    }
}

class ExpressShipping implements ShippingType {

    @Override
    public double calculateCharge(double weight) {
        return 80 + (15 * weight);
    }

    @Override
    public String getName() {
        return "Express";
    }
}

class FragileShipping implements ShippingType {

    @Override
    public double calculateCharge(double weight) {
        return 40 + (10 * weight) + 50;
    }

    @Override
    public String getName() {
        return "Fragile";
    }
}

interface NotificationChannel {
    void notify(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {

    @Override
    public void notify(String parcelId, String status) {
        System.out.println(
                "[SMS] " +
                parcelId +
                " is now " +
                status +
                "."
        );
    }
}

class EmailChannel implements NotificationChannel {

    @Override
    public void notify(String parcelId, String status) {
        System.out.println(
                "[Email] " +
                parcelId +
                " is now " +
                status +
                "."
        );
    }
}

class ParcelCustomer {
    private String name;

    public ParcelCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Parcel {

    private String parcelId;
    private double weight;
    private ShippingType shippingType;
    private String status;
    private ArrayList<NotificationChannel> channels;

    public Parcel(
            String parcelId,
            double weight,
            ShippingType shippingType) {

        this.parcelId = parcelId;
        this.weight = weight;
        this.shippingType = shippingType;
        this.status = "BOOKED";
        this.channels = new ArrayList<>();
    }

    public String getParcelId() {
        return parcelId;
    }

    public String getStatus() {
        return status;
    }

    public double calculateCharge() {
        return shippingType.calculateCharge(weight);
    }

    public void addNotificationChannel(
            NotificationChannel channel) {

        channels.add(channel);
    }

    private void notifyChannels() {

        for (NotificationChannel channel : channels) {
            channel.notify(parcelId, status);
        }
    }

    public void changeStatus(String newStatus) {

        if (status.equals("BOOKED")
                && newStatus.equals("PICKED_UP")) {

            status = newStatus;
            notifyChannels();
            return;
        }

        if (status.equals("PICKED_UP")
                && newStatus.equals("IN_TRANSIT")) {

            status = newStatus;
            notifyChannels();
            return;
        }

        if (status.equals("IN_TRANSIT")
                && newStatus.equals("OUT_FOR_DELIVERY")) {

            status = newStatus;
            notifyChannels();
            return;
        }

        if (status.equals("OUT_FOR_DELIVERY")
                && newStatus.equals("DELIVERED")) {

            status = newStatus;
            notifyChannels();
            return;
        }

        System.out.println(
                "Invalid transition: " +
                status +
                " → " +
                newStatus +
                " is not allowed."
        );
    }

    public void cancel() {

        if (status.equals("BOOKED")) {

            status = "CANCELLED";

            notifyChannels();

        } else {

            System.out.println(
                    "Cancellation failed: " +
                    parcelId +
                    " can be cancelled only while BOOKED."
            );
        }
    }
}

class ParcelService {

    public Parcel bookParcel(
            ParcelCustomer customer,
            String parcelId,
            double weight,
            ShippingType shippingType) {

        Parcel parcel =
                new Parcel(
                        parcelId,
                        weight,
                        shippingType
                );

        System.out.printf(
                "Parcel %s booked (%s, %.0f kg). Charge: ₹%.2f%n",
                parcelId,
                shippingType.getName(),
                weight,
                parcel.calculateCharge()
        );

        return parcel;
    }

    public void markPickedUp(Parcel parcel) {
        parcel.changeStatus("PICKED_UP");
    }

    public void markInTransit(Parcel parcel) {
        parcel.changeStatus("IN_TRANSIT");
    }

    public void markOutForDelivery(Parcel parcel) {
        parcel.changeStatus("OUT_FOR_DELIVERY");
    }

    public void markDelivered(Parcel parcel) {
        parcel.changeStatus("DELIVERED");
    }

    public void cancelParcel(Parcel parcel) {
        parcel.cancel();
    }
}

public class Ques2 {

    public static void main(String[] args) {

        ParcelCustomer customer =
                new ParcelCustomer("Customer");

        ParcelService service =
                new ParcelService();

        Parcel parcel =
                service.bookParcel(
                        customer,
                        "P101",
                        2,
                        new ExpressShipping()
                );

        parcel.addNotificationChannel(
                new SmsChannel()
        );

        parcel.addNotificationChannel(
                new EmailChannel()
        );

        // Notify the subscribed channels about BOOKED status
        parcel.changeStatus("BOOKED");

        service.markPickedUp(parcel);

        service.cancelParcel(parcel);

        service.markInTransit(parcel);

        service.markDelivered(parcel);
    }
}