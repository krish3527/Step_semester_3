package oops.assignment_problem;

import java.util.regex.Pattern;

interface LoanReceiptType {
    String[] getBookIds();
}

final class LoanReceipt implements LoanReceiptType {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {

        if (bookIds == null) {
            throw new IllegalArgumentException("Invalid bookIds");
        }

        for (String id : bookIds) {
            if (id == null || !Pattern.matches("BK-\\d{3}", id)) {
                throw new IllegalArgumentException("Invalid book ID");
            }
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (newId == null ||
            !Pattern.matches("BK-\\d{3}", newId)) {
            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] correctedIds = bookIds.clone();
        correctedIds[index] = newId;

        return new LoanReceipt(memberId, correctedIds);
    }
}

class ReferenceOnlyLoanReceipt implements LoanReceiptType {

    private final String memberId;
    private final String[] bookIds;
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        if (bookIds == null) {
            throw new IllegalArgumentException("Invalid bookIds");
        }

        for (String id : bookIds) {
            if (id == null || !Pattern.matches("BK-\\d{3}", id)) {
                throw new IllegalArgumentException("Invalid book ID");
            }
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
        this.roomNumber = roomNumber;
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }
}

public class Ques5 {

    static {
        System.out.println("Nightly Circulation System Initialized");
    }

    static String processNightlyCirculation(
            LoanReceiptType[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceiptType receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else if (receipt instanceof LoanReceipt) {
                regular++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }

    public static void main(String[] args) {

        try {

            new LoanReceipt(
                    "LIB-8841",
                    new String[]{"BK-100", "bad"}
            );

            System.out.println("construction succeeded");

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceiptType[] receipts = {
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),
                null,
                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}