package oops.practice_problem;

interface Reservable {
    void reserve(String memberId);
}

interface Downloadable {
    void download();
}

abstract class Resource {

    String id;

    Resource(String id) {
        this.id = id;
    }

    abstract String getType();
}

class Book extends Resource implements Reservable {

    Book(String id) {
        super(id);
    }

    @Override
    String getType() {
        return "Book";
    }

    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }
}

class EBook extends Resource implements Reservable, Downloadable {

    EBook(String id) {
        super(id);
    }

    @Override
    String getType() {
        return "EBook";
    }

    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class Ques5 {

    static Resource[] resources = new Resource[100];

    static int count = 0;

    static void addResource(Resource resource) {

        if (findIndex(resource.id) != -1) {
            System.out.println("duplicate rejected");
            return;
        }

        if (count == resources.length) {
            System.out.println("library is full");
            return;
        }

        int i = count - 1;

        while (i >= 0 && resources[i].id.compareTo(resource.id) > 0) {

            resources[i + 1] = resources[i];
            i--;
        }

        resources[i + 1] = resource;
        count++;

        System.out.println(resource.id + " added");
    }

    static int findIndex(String id) {

        int low = 0;
        int high = count - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int result = resources[mid].id.compareTo(id);

            if (result == 0) {
                return mid;
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    static void reserve(String id, String memberId) {

        int index = findIndex(id);

        if (index == -1) {
            System.out.println(id + " not found");
            return;
        }

        Resource resource = resources[index];

        if (resource instanceof Reservable) {
            ((Reservable) resource).reserve(memberId);
        } else {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    static void download(String id) {

        int index = findIndex(id);

        if (index == -1) {
            System.out.println(id + " not found");
            return;
        }

        Resource resource = resources[index];

        if (resource instanceof Downloadable) {
            ((Downloadable) resource).download();
        } else {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    static void findResource(String id) {

        int index = findIndex(id);

        if (index == -1) {
            System.out.println(id + " not found");
        } else {
            System.out.println(id + " found at index " + index);
        }
    }

    public static void main(String[] args) {

        addResource(new Book("B1"));

        addResource(new Book("B1"));

        addResource(new EBook("E1"));

        reserve("B1", "M1");

        download("B1");

        download("E1");

        findResource("E1");
    }
}