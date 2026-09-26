package oops.practice_problem;
abstract class LeaveEmployee {
    private String name;

    public LeaveEmployee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends LeaveEmployee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends LeaveEmployee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class ContractEmployee extends LeaveEmployee {

    public ContractEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

class LeaveRequest {
    private LeaveEmployee employee;
    private String startDate;
    private String endDate;
    private String status;

    public LeaveRequest(
            LeaveEmployee employee,
            String startDate,
            String endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
    }

    public LeaveEmployee getEmployee() {
        return employee;
    }

    public String getStatus() {
        return status;
    }

    public void approve() {

        if (status.equals("Pending")) {
            status = "Approved";

            System.out.println(
                    "Leave request for " +
                    employee.getName() +
                    " approved. Status: " +
                    status
            );
        }
    }

    public void reject() {

        if (status.equals("Pending")) {
            status = "Rejected";

            System.out.println(
                    "Leave request for " +
                    employee.getName() +
                    " rejected. Status: " +
                    status
            );
        }
    }

    public void changeToPending() {

        if (!status.equals("Pending")) {

            System.out.println(
                    "Cannot change status: " +
                    status +
                    " request cannot revert to Pending."
            );

            return;
        }

        status = "Pending";
    }
}

class LeaveManager {

    public LeaveRequest submitLeave(
            LeaveEmployee employee,
            String startDate,
            String endDate) {

        LeaveRequest request =
                new LeaveRequest(
                        employee,
                        startDate,
                        endDate
                );

        System.out.println(
                "Leave request submitted by " +
                employee.getName() +
                " for " +
                startDate +
                " to " +
                endDate +
                ". Status: Pending."
        );

        return request;
    }

    public void approveRequest(LeaveRequest request) {
        request.approve();
    }

    public void rejectRequest(LeaveRequest request) {
        request.reject();
    }
}

public class Ques4 {

    public static void main(String[] args) {

        LeaveManager manager =
                new LeaveManager();

        LeaveEmployee john =
                new FullTimeEmployee("John Doe");

        LeaveRequest johnRequest =
                manager.submitLeave(
                        john,
                        "2024-10-10",
                        "2024-10-12"
                );

        manager.approveRequest(johnRequest);

        LeaveEmployee jane =
                new PartTimeEmployee("Jane Smith");

        LeaveRequest janeRequest =
                manager.submitLeave(
                        jane,
                        "2024-11-01",
                        "2024-11-05"
                );

        manager.changeToPending(johnRequest);
    }
}