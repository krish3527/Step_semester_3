
package oops.assignment_problem.session_12;

import java.util.Scanner;

public class Ques5 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverse(Node head) {
        Node prev = null, curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;

        Node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = reverse(slow.next);
        Node temp = second;
        Node first = head;
        boolean result = true;

        while (temp != null) {
            if (first.data != temp.data) {
                result = false;
                break;
            }
            first = first.next;
            temp = temp.next;
        }

        slow.next = reverse(second);

        return result;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Node head = null, tail = null;

        while (sc.hasNextInt()) {
            Node newNode = new Node(sc.nextInt());

            if (head == null)
                head = tail = newNode;
            else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        System.out.println("Palindrome: " + isPalindrome(head));
        System.out.print("List after check: ");
        display(head);

        sc.close();
    }
}

