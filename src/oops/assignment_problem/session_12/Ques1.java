
package oops.assignment_problem.session_12;

import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class Ques1 {

    static String detectLoop(Node head) {

        Node slow = head;
        Node fast = head;

        // Detect loop
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return "Loop: no";
        }

        // Find loop starting node
        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        Node start = slow;

        // Find loop length
        int length = 1;
        Node temp = start.next;

        while (temp != start) {
            length++;
            temp = temp.next;
        }

        return "Loop: yes, starts at stop " +
                start.data + ", length " + length;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;
        Node loopNode = null;

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();

            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }

            tail = newNode;
        }

        int pos = sc.nextInt();

        if (pos != -1) {
            Node temp = head;

            for (int i = 0; i < pos; i++) {
                temp = temp.next;
            }

            tail.next = temp;
        }

        System.out.println(detectLoop(head));

        sc.close();
    }
}
