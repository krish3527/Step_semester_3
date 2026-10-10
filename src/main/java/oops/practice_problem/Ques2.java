package oops.practice_problem;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Customer {

    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Customer other = (Customer) obj;

        return id == other.id && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class Ques2 {

    public static void main(String[] args) {

        Set<Customer> customers = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println(customers.add(c1));

        boolean added = customers.add(c2);

        if (!added) {
            System.out.println("false (duplicate rejected)");
        } else {
            System.out.println("true");
        }

        System.out.println(customers.add(c3));

        System.out.println("unique count " + customers.size());

        System.out.println(
            "contains: " + customers.contains(new Customer(101, "Asha"))
        );
    }
}