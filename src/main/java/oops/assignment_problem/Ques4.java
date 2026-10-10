
package oops.assignment_problem;

import java.util.Scanner;

class Node {
    String url;
    Node prev;
    Node next;

    Node(String url) {
        this.url = url;
        this.prev = null;
        this.next = null;
    }
}

class BrowserHistory {

    private Node current;

    BrowserHistory(String homepage) {
        current = new Node(homepage);
    }

    public void visit(String url) {
        Node newNode = new Node(url);

        current.next = null;

        newNode.prev = current;
        current.next = newNode;

        current = newNode;
    }

    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }

        return current.url;
    }

    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }

        return current.url;
    }
}

public class Ques4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter homepage URL: ");
        String homepage = sc.next();

        BrowserHistory browser = new BrowserHistory(homepage);

        System.out.println("Enter number of actions:");
        int actions = sc.nextInt();

        for (int i = 0; i < actions; i++) {

            System.out.println("Choose action: 1.Visit  2.Back  3.Forward");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter URL: ");
                    String url = sc.next();
                    browser.visit(url);
                    System.out.println("Current page: " + url);
                    break;

                case 2:
                    System.out.print("Enter steps to go back: ");
                    int backSteps = sc.nextInt();
                    System.out.println("Current page: " + browser.back(backSteps));
                    break;

                case 3:
                    System.out.print("Enter steps to go forward: ");
                    int forwardSteps = sc.nextInt();
                    System.out.println("Current page: " + browser.forward(forwardSteps));
                    break;

                default:
                    System.out.println("Invalid action");
            }
        }

        sc.close();
    }
}
