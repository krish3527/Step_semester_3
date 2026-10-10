
package oops.assignment_problem;

import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Ques5 {

    static Node removeKthFromEnd(Node head, int k) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node lead = dummy;
        Node trail = dummy;

        // Move lead k + 1 steps ahead
        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        // Move both pointers until lead reaches null
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        // Remove the k-th node from the end
        trail.next = trail.next.next;

        return dummy.next;
    }

    static void display(Node head) {

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

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        System.out.println("Enter order values:");

        for (int i = 0; i < n; i++) {

            int value = sc.nextInt();
            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        System.out.print("Original orders: ");
        display(head);

        head = removeKthFromEnd(head, k);

        System.out.print("Orders after cancellation: ");
        display(head);

        sc.close();
    }
}

