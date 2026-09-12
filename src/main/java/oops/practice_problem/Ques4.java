package oops.practice_problem;

class EventTicket {
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }

    public String printTicket() {
        return "Standard | Balance: " + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + track
                + " | Balance: " + getBalanceDue();
    }
}

public class Ques4 {

    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder report = new StringBuilder();

        for (EventTicket ticket : tickets) {
            report.append(ticket.printTicket());

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshop = (WorkshopTicket) ticket;

                report.append(" [Track via downcast: ")
                        .append(workshop.getTrack())
                        .append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {
        EventTicket standard = new EventTicket(500);

        WorkshopTicket workshop =
                new WorkshopTicket(1200, "AI/ML");

        EventTicket[] tickets = {standard, workshop};

        System.out.println(batchPrint(tickets));

        // Example of an invalid downcast:
        // EventTicket plain = new EventTicket(500);
        // WorkshopTicket bad = (WorkshopTicket) plain;
        // This causes ClassCastException at runtime.
    }
}