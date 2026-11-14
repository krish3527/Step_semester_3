
package oops.assignment_problem.session_12;

import java.util.*;

class Node {
    int key;
    String page;
    Node prev, next;

    Node(int key, String page) {
        this.key = key;
        this.page = page;
    }
}

class RecentCache {
    int capacity;
    HashMap<Integer, Node> map = new HashMap<>();

    Node head, tail;
    String putStatus;

    RecentCache(int capacity) {
        this.capacity = capacity;

        head = new Node(0, "");
        tail = new Node(0, "");

        head.next = tail;
        tail.prev = head;
    }

    void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    void addFirst(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    void moveToFront(Node node) {
        remove(node);
        addFirst(node);
    }

    String get(int key) {
        if (!map.containsKey(key))
            return "-1";

        Node node = map.get(key);
        moveToFront(node);

        return node.page;
    }

    void put(int key, String page) {
        putStatus = "";

        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.page = page;
            moveToFront(node);
            putStatus = "updated";
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
                putStatus = "evicted " + lru.key;
            }

            Node node = new Node(key, page);
            addFirst(node);
            map.put(key, node);
        }
    }

    void display() {
        Node temp = head.next;

        System.out.print("Cache (most recent first): ");

        while (temp != tail) {
            System.out.print(temp.key + "=" + temp.page);

            temp = temp.next;

            if (temp != tail)
                System.out.print(", ");
        }

        System.out.println();
    }
}

public class Ques3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int capacity = sc.nextInt();
        int n = sc.nextInt();

        RecentCache cache = new RecentCache(capacity);

        for (int i = 0; i < n; i++) {

            String operation = sc.next();

            if (operation.equalsIgnoreCase("put")) {
                int key = sc.nextInt();
                String page = sc.next();

                cache.put(key, page);

                System.out.print("put(" + key + "," + page + ")");

                if (!cache.putStatus.isEmpty())
                    System.out.print(" " + cache.putStatus);

                System.out.println();

            } else if (operation.equalsIgnoreCase("get")) {
                int key = sc.nextInt();

                String result = cache.get(key);

                System.out.println("get(" + key + ") -> " + result);
            }
        }

        cache.display();
        sc.close();
    }
}
