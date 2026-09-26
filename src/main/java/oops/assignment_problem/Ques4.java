package oops.assignment_problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

interface CreditRule {
    int getCreditLimit();
    String getType();
}

class RegularCreditRule implements CreditRule {

    @Override
    public int getCreditLimit() {
        return 24;
    }

    @Override
    public String getType() {
        return "Regular";
    }
}

class HonorsCreditRule implements CreditRule {

    @Override
    public int getCreditLimit() {
        return 28;
    }

    @Override
    public String getType() {
        return "Honors";
    }
}

class ExchangeCreditRule implements CreditRule {

    @Override
    public int getCreditLimit() {
        return 20;
    }

    @Override
    public String getType() {
        return "Exchange";
    }
}

class ElectiveStudent {

    private String name;
    private int currentCredits;
    private CreditRule creditRule;

    public ElectiveStudent(
            String name,
            int currentCredits,
            CreditRule creditRule) {

        this.name = name;
        this.currentCredits = currentCredits;
        this.creditRule = creditRule;
    }

    public String getName() {
        return name;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCreditLimit() {
        return creditRule.getCreditLimit();
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public void removeCredits(int credits) {
        currentCredits -= credits;
    }

    public boolean canTake(int credits) {
        return currentCredits + credits <= getCreditLimit();
    }
}

class Elective {

    private String name;
    private int credits;
    private int capacity;

    private ArrayList<ElectiveStudent> enrolledStudents;
    private Queue<ElectiveStudent> waitlist;

    public Elective(
            String name,
            int credits,
            int capacity) {

        this.name = name;
        this.credits = credits;
        this.capacity = capacity;

        enrolledStudents = new ArrayList<>();
        waitlist = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public boolean isEnrolled(ElectiveStudent student) {

        return enrolledStudents.contains(student);
    }

    public boolean isWaiting(ElectiveStudent student) {

        return waitlist.contains(student);
    }

    public void enroll(ElectiveStudent student) {

        enrolledStudents.add(student);
        student.addCredits(credits);

        System.out.println(
                student.getName() +
                " enrolled in " +
                name +
                ". Credits: " +
                student.getCurrentCredits() +
                "/" +
                student.getCreditLimit()
        );
    }

    public void addToWaitlist(ElectiveStudent student) {

        waitlist.add(student);

        System.out.println(
                student.getName() +
                " added to waitlist for " +
                name +
                ". Position: " +
                waitlist.size()
        );
    }

    public ElectiveStudent removeFromWaitlist() {
        return waitlist.poll();
    }

    public void drop(ElectiveStudent student) {

        if (!enrolledStudents.remove(student)) {
            return;
        }

        student.removeCredits(credits);

        System.out.println(
                student.getName() +
                " dropped " +
                name +
                ". Credits: " +
                student.getCurrentCredits() +
                "/" +
                student.getCreditLimit()
        );
    }
}

class Enrollment {

    private ElectiveStudent student;
    private Elective elective;
    private String status;

    public Enrollment(
            ElectiveStudent student,
            Elective elective,
            String status) {

        this.student = student;
        this.elective = elective;
        this.status = status;
    }

    public ElectiveStudent getStudent() {
        return student;
    }

    public Elective getElective() {
        return elective;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class EnrollmentService {

    public void enroll(
            ElectiveStudent student,
            Elective elective) {

        if (elective.isEnrolled(student)
                || elective.isWaiting(student)) {

            System.out.println(
                    "Enrollment failed: " +
                    student.getName() +
                    " is already enrolled or waitlisted for " +
                    elective.getName() +
                    "."
            );

            return;
        }

        // Credit limit is checked BEFORE seat availability
        if (!student.canTake(elective.getCredits())) {

            System.out.println(
                    "Enrollment failed: " +
                    student.getName() +
                    " would exceed the credit limit. " +
                    "Current: " +
                    student.getCurrentCredits() +
                    "/" +
                    student.getCreditLimit()
            );

            return;
        }

        if (!elective.isFull()) {

            elective.enroll(student);

        } else {

            elective.addToWaitlist(student);
        }
    }

    public void drop(
            ElectiveStudent student,
            Elective elective) {

        if (!elective.isEnrolled(student)) {

            System.out.println(
                    "Drop failed: " +
                    student.getName() +
                    " is not enrolled in " +
                    elective.getName() +
                    "."
            );

            return;
        }

        elective.drop(student);

        promoteNext(elective);
    }

    private void promoteNext(Elective elective) {

        while (!elective.isFull()) {

            ElectiveStudent next =
                    elective.removeFromWaitlist();

            if (next == null) {
                return;
            }

            // Credit limit checked again during promotion
            if (next.canTake(elective.getCredits())) {

                elective.enroll(next);

                System.out.println(
                        next.getName() +
                        " promoted from waitlist."
                );

                return;

            } else {

                System.out.println(
                        next.getName() +
                        " cannot be promoted because " +
                        "the credit limit would be exceeded."
                );
            }
        }
    }
}

public class Ques4 {

    public static void main(String[] args) {

        EnrollmentService service =
                new EnrollmentService();

        Elective cloudComputing =
                new Elective(
                        "Cloud Computing",
                        4,
                        2
                );

        ElectiveStudent asha =
                new ElectiveStudent(
                        "Asha",
                        20,
                        new RegularCreditRule()
                );

        ElectiveStudent ravi =
                new ElectiveStudent(
                        "Ravi",
                        22,
                        new HonorsCreditRule()
                );

        ElectiveStudent neha =
                new ElectiveStudent(
                        "Neha",
                        12,
                        new ExchangeCreditRule()
                );

        ElectiveStudent kiran =
                new ElectiveStudent(
                        "Kiran",
                        22,
                        new RegularCreditRule()
                );

        service.enroll(
                asha,
                cloudComputing
        );

        service.enroll(
                ravi,
                cloudComputing
        );

        service.enroll(
                neha,
                cloudComputing
        );

        service.enroll(
                kiran,
                cloudComputing
        );

        service.drop(
                asha,
                cloudComputing
        );
    }
}