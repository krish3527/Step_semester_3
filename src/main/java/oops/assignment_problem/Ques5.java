package oops.assignment_problem;

class EventTicket {

    private static int ticketCounter = 1000;

    private final String ticketId;

    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {

        if (basePrice <= 0) {
            throw new IllegalArgumentException(
                "Base price must be positive"
            );
        }

        ticketCounter++;

        ticketId = "TCK-" + ticketCounter;

        amountPaid = 0;
        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public void pay(double amount, String mode) {

        System.out.println("Paying via " + mode);

        pay(amount);
    }

    public String getTicketId() {
        return ticketId;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public static boolean isValidPromoCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'F') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }

    public static int getTicketsIssued() {
        return ticketCounter - 1000;
    }
}

class GroupTicket extends EventTicket {

    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {

        super(basePrice);

        if (groupSize <= 0) {
            throw new IllegalArgumentException(
                "Group size must be positive"
            );
        }

        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class Ques5 {

    public static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (EventTicket ticket : tickets) {

            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed
            + " processed | "
            + nullSkipped
            + " null skipped | "
            + groupCount
            + " group | "
            + individualCount
            + " individual";
    }

    public static void main(String[] args) {

        EventTicket t1 =
            new EventTicket(500);

        System.out.println(t1.getTicketId());

        System.out.println(
            EventTicket.getTicketsIssued()
        );

        System.out.println(
            EventTicket.isValidPromoCode("F123A")
        );

        System.out.println(
            EventTicket.isValidPromoCode("F12A")
        );

        System.out.println(
            EventTicket.isValidPromoCode("X123A")
        );

        t1.pay(200);

        t1.pay(200, "UPI");

        System.out.println(
            t1.getBalanceDue()
        );

        EventTicket[] tickets = {
            new GroupTicket(2000, 5),
            null,
            new EventTicket(500)
        };

        System.out.println(
            processNightlySettlement(tickets)
        );
    }
}