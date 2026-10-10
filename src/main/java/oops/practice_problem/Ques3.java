package oops.practice_problem;

public class Ques3 {

    public static void main(String[] args) {

        String[] students = {"Anu", "Ravi", "Meena"};

        String[] subjects = {"Math", "Sci", "Eng"};

        int[][] marks = {
            {80, 90, 70},
            {60, 85, -1},
            {95, 75, 88}
        };

        int studentCount = students.length;
        int subjectCount = subjects.length;

        System.out.println("Student Totals:");

        for (int i = 0; i < studentCount; i++) {

            int total = 0;

            for (int j = 0; j < subjectCount; j++) {

                if (marks[i][j] != -1) {
                    total += marks[i][j];
                }
            }

            System.out.println(students[i] + " " + total);
        }

        System.out.println("\nSubject Toppers:");

        for (int j = 0; j < subjectCount; j++) {

            int maxMarks = -1;
            String topper = "None";

            for (int i = 0; i < studentCount; i++) {

                if (marks[i][j] != -1 && marks[i][j] > maxMarks) {

                    maxMarks = marks[i][j];
                    topper = students[i];
                }
            }

            System.out.println(
                subjects[j] + " " + topper + " " + maxMarks
            );
        }
    }
}