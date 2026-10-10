```java
package oops.assignment_problem;

import java.util.Scanner;

class Node {
    int coefficient;
    int exponent;
    Node next;

    Node(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}

public class PolymerCalculator {

    static Node addPolynomials(Node p, Node q) {

        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {

            if (p.exponent == q.exponent) {

                int sum = p.coefficient + q.coefficient;

                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                    tail = tail.next;
                }

                p = p.next;
                q = q.next;
            }

            else if (p.exponent > q.exponent) {

                tail.next = new Node(p.coefficient, p.exponent);
                tail = tail.next;
                p = p.next;
            }

            else {

                tail.next = new Node(q.coefficient, q.exponent);
                tail = tail.next;
                q = q.next;
            }
        }

        while (p != null) {
            tail.next = new Node(p.coefficient, p.exponent);
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new Node(q.coefficient, q.exponent);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static void display(Node head) {

        if (head == null) {
            System.out.println("0");
            return;
        }

        Node current = head;
        boolean first = true;

        while (current != null) {

            int coefficient = current.coefficient;
            int exponent = current.exponent;

            if (!first) {
                if (coefficient > 0) {
                    System.out.print(" + ");
                } else {
                    System.out.print(" - ");
                }
            } else if (coefficient < 0) {
                System.out.print("-");
            }

            int absoluteCoefficient = Math.abs(coefficient);

            if (exponent == 0) {
                System.out.print(absoluteCoefficient);
            } else {

                if (absoluteCoefficient != 1) {
                    System.out.print(absoluteCoefficient);
                }

                System.out.print("x");

                if (exponent != 1) {
                    System.out.print("^" + exponent);
                }
            }

            first = false;
            current = current.next;
        }

        System.out.println();
    }

    static Node createPolynomial(Scanner sc, int terms) {

        Node head = null;
        Node tail = null;

        for (int i = 0; i < terms; i++) {

            System.out.print("Enter coefficient and exponent: ");

            int coefficient = sc.nextInt();
            int exponent = sc.nextInt();

            Node newNode = new Node(coefficient, exponent);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        return
```
