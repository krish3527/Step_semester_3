package oops.practice_problem.session_11;
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Ques4 {

    public static int length(Node head) {
        int count = 0;

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    public static Node findIntersection(Node a, Node b) {
        int lenA = length(a);
        int lenB = length(b);

        while (lenA > lenB) {
            a = a.next;
            lenA--;
        }

        while (lenB > lenA) {
            b = b.next;
            lenB--;
        }

        while (a != null && b != null) {
            if (a == b) {
                return a;
            }

            a = a.next;
            b = b.next;
        }

        return null;
    }

    public static void main(String[] args) {
        Node shared = new Node(8);
        shared.next = new Node(4);
        shared.next.next = new Node(5);

        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = shared;

        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = shared;

        Node intersection = findIntersection(a, b);

        if (intersection != null) {
            System.out.println("Intersection Node: "
                    + intersection.data);
        } else {
            System.out.println("No intersection");
        }

        Node c = new Node(2);
        c.next = new Node(6);
        c.next.next = new Node(4);

        Node d = new Node(1);
        d.next = new Node(5);

        Node intersection2 = findIntersection(c, d);

        if (intersection2 != null) {
            System.out.println("Intersection Node: "
                    + intersection2.data);
        } else {
            System.out.println("No intersection");
        }
    }
}