
package oops.assignment_problem.session_11;

public class Ques4 {

    static class BusBay {

        int[] arr;
        int capacity;
        int front;
        int size;

        BusBay(int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
            front = 0;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean insertFront(int bus) {

            if (isFull()) {
                return false;
            }

            front = (front - 1 + capacity) % capacity;

            arr[front] = bus;
            size++;

            return true;
        }

        public boolean insertLast(int bus) {

            if (isFull()) {
                return false;
            }

            int rear = (front + size) % capacity;

            arr[rear] = bus;
            size++;

            return true;
        }

        public boolean deleteFront() {

            if (isEmpty()) {
                return false;
            }

            front = (front + 1) % capacity;
            size--;

            if (size == 0) {
                front = 0;
            }

            return true;
        }

        public boolean deleteLast() {

            if (isEmpty()) {
                return false;
            }

            size--;

            if (size == 0) {
                front = 0;
            }

            return true;
        }

        public int getFront() {

            if (isEmpty()) {
                return -1;
            }

            return arr[front];
        }

        public int getRear() {

            if (isEmpty()) {
                return -1;
            }

            int rear = (front + size - 1) % capacity;

            return arr[rear];
        }
    }

    public static void main(String[] args) {

        BusBay b = new BusBay(3);

        System.out.println(b.insertLast(101));
        System.out.println(b.insertLast(102));
        System.out.println(b.insertFront(100));

        System.out.println(b.insertFront(99));

        System.out.println(b.getRear());

        System.out.println(b.isFull());

        System.out.println(b.deleteLast());

        System.out.println(b.insertFront(98));

        System.out.println(b.getFront());
    }
}

