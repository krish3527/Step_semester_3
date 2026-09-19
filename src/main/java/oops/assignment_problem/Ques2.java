package oops.assignment_problem;

interface Exportable {
    String exportData();
}

class ExportCounter {
    private static int totalExports = 0;

    public static void increment() {
        totalExports++;
    }

    public static int getTotalExports() {
        return totalExports;
    }
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportCounter.increment();
        String message = "Exported report: " + reportName;
        System.out.println(message);
        return message;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportCounter.increment();
        String message = "Exported profile: " + username;
        System.out.println(message);
        return message;
    }
}

public class Ques2 {

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            item.exportData();
        }
    }

    static int getTotalExports() {
        return ExportCounter.getTotalExports();
    }

    public static void main(String[] args) {

        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        r.exportData();
        u.exportData();

        Exportable ref = r;

        exportAll(new Exportable[] { ref, u });

        System.out.println(getTotalExports());
    }
}