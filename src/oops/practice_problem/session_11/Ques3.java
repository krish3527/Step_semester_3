package oops.practice_problem.session_11;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Ques3 {

    public static Node addNumbers(Node a, Node b) {
        Node dummy = new Node(0);
        Node current = dummy;

        int carry = 0;

        while (a != null || b != null || carry != 0) {
            int sum = carry;

            if (a != null) {
                sum += a.data;
                a = a.next;
            }

            if (b != null) {
                sum += b.data;
                b = b.next;
            }

            carry = sum / 10;

            current.next = new Node(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }

    public static void display(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node a = new Node(2);
        a.next = new Node(4);
        a.next.next = new Node(3);

        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(4);

        Node result = addNumbers(a, b);

        System.out.print("Sum of 342 and 465: ");
        display(result);

        Node c = new Node(9);
        c.next = new Node(9);

        Node d = new Node(1);

        Node result2 = addNumbers(c, d);

        System.out.print("Sum of 99 and 1: ");
        display(result2);
    }
}