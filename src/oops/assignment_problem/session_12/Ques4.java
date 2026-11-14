
package oops.assignment_problem.session_12;

import java.util.*;

public class Ques4 {

    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
        }
    }

    static Node sortByDueDay(Node head) {
        if (head == null || head.next == null)
            return head;

        Node slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = slow.next;
        slow.next = null;

        Node left = sortByDueDay(head);
        Node right = sortByDueDay(second);

        return merge(left, right);
    }

    static Node merge(Node a, Node b) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.dueDay <= b.dueDay) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        tail.next = (a != null) ? a : b;

        return dummy.next;
    }

    static void display(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        while (head != null) {
            System.out.print(head.title + ":" + head.dueDay + " -> ");
            head = head.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node head = null, tail = null;

        while (sc.hasNext()) {
            String input = sc.next();
            String[] parts = input.split(":");

            Node newNode = new Node(parts[0], Integer.parseInt(parts[1]));

            if (head == null)
                head = tail = newNode;
            else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        head = sortByDueDay(head);
        display(head);

        sc.close();
    }
}

