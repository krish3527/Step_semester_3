package oops.assignment_problem;

public class BookIssue {

    private String title;
    private String borrowerName;
    private int daysOverdue;

    // Constructor to initialize book details
    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // Calculates fine for one book
    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        } else {
            return 0;
        }
    }

    // Checks whether the book is severely overdue
    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // Static method to calculate total fine for all books
    // This is static because it works on multiple BookIssue objects,
    // while fineAmount() belongs to one particular BookIssue object.
    public static double totalFineCollected(BookIssue[] issues) {

        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }

    public static void main(String[] args) {

        // Creating an array of five BookIssue objects
        BookIssue[] issues = {
                new BookIssue("Clean Code", "Aditi", 18),
                new BookIssue("Effective Java", "Rohan", 5),
                new BookIssue("Refactoring", "Karan", 0),
                new BookIssue("DSA Handbook", "Meera", 21),
                new BookIssue("Design Patterns", "Arjun", 9)
        };

        // Display overdue status of each book
        for (BookIssue issue : issues) {

            if (issue.isSeverelyOverdue()) {
                System.out.println(issue.title + " - "
                        + issue.daysOverdue
                        + " days - Severely overdue");
            } else {
                System.out.println(issue.title + " - "
                        + issue.daysOverdue
                        + " days - OK");
            }
        }

        // Display total fine
        System.out.println("Total fine collected: Rs "
                + BookIssue.totalFineCollected(issues));
    }
}