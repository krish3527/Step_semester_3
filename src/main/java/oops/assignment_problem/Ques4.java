package oops.assignment_problem;
public class Ques4 {
    static class BrokenLibraryMember {

        /*
         * These fields are wrongly declared static.
         *
         * name:
         * It should belong to each member individually.
         * Aditi and Rohan need different names, so name must
         * be an instance field.
         *
         * memberId:
         * Each member needs a different ID.
         * A static memberId would be shared by everyone.
         *
         * booksIssued:
         * Each member can have a different number of books.
         * Therefore it must also belong to each individual object.
         */

        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId, int booksIssued) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }


    // =========================================================
    // FIXED VERSION
    // =========================================================

    static class LibraryMember {

        // Instance fields - every member gets its own copy
        String name;
        String memberId;
        int booksIssued;

        // Static fields - shared by all members
        static String libraryName = "SRM Library";
        static int memberCount = 0;

        // Constructor
        LibraryMember(String name, int booksIssued) {

            this.name = name;
            this.booksIssued = booksIssued;

            memberCount++;

            this.memberId = "LM-" + (1000 + memberCount);
        }

        // Instance method
        void printMemberCard() {

            System.out.println(name + " | " + memberId);
        }

        // Static method
        static void printTotalMembers() {

            System.out.println("Total members: " + memberCount);
        }
    }


    public static void main(String[] args) {

        // =====================================================
        // BROKEN VERSION
        // =====================================================

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println(member1.name);
        System.out.println(member2.name);


        // =====================================================
        // FIXED VERSION
        // =====================================================

        System.out.println("\nFixed version:");

        LibraryMember m1 =
                new LibraryMember("Aditi", 2);

        LibraryMember m2 =
                new LibraryMember("Rohan", 3);

        m1.printMemberCard();
        m2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}
