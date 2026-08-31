package oops.assignment_problem;
class Employee {

    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}


class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(int empId, String empName,
                    double salary, double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(int empId, String empName,
                   double salary, double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {

        if (getSalary() < stipendCap) {
            return getSalary();
        } else {
            return stipendCap;
        }
    }
}


public class Ques2{

    public static void main(String[] args) {

        Employee e1 =
            new Employee(1, "Arun", 40000);

        Employee e2 =
            new ManagerEmployee(2, "Rahul", 70000, 8000);

        Employee e3 =
            new InternEmployee(3, "Priya", 12000, 10000);

        if (e1 instanceof ManagerEmployee) {
            System.out.println(
                "Manager effective pay: Rs " + ((ManagerEmployee)e1).effectiveSalary());

        } else if (e1 instanceof InternEmployee) {

            System.out.println(
                "Intern effective pay: Rs " +
                ((InternEmployee)e1).effectiveSalary()
            );

        } else {

            System.out.println(
                "Plain employee pay: Rs " +
                e1.getSalary()
            );
        }


        
        if (e2 instanceof ManagerEmployee) {

            System.out.println(
                "Manager effective pay: Rs " +
                ((ManagerEmployee)e2).effectiveSalary()
            );
        }


        // Intern
        if (e3 instanceof InternEmployee) {

            System.out.println(
                "Intern effective pay: Rs " +
                ((InternEmployee)e3).effectiveSalary()
            );
        }
    }
}

