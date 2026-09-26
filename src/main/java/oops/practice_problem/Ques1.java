package oops.practice_problem;

import java.util.ArrayList;

abstract class Question {
    private int questionNumber;
    private String questionText;
    private String correctAnswer;

    public Question(int questionNumber, String questionText, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {

    public MultipleChoiceQuestion(int questionNumber, String questionText, String correctAnswer) {
        super(questionNumber, questionText, correctAnswer);
    }

    @Override
    public boolean evaluate(String answer) {
        return getCorrectAnswer().equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(int questionNumber, String questionText, String correctAnswer) {
        super(questionNumber, questionText, correctAnswer);
    }

    @Override
    public boolean evaluate(String answer) {
        return getCorrectAnswer().equalsIgnoreCase(answer);
    }
}

class ExamStudent {
    private String name;

    public ExamStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String name;
    private ArrayList<Question> questions;
    private ArrayList<Attempt> attempts;

    public Examination(String name) {
        this.name = name;
        questions = new ArrayList<>();
        attempts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Attempt start(ExamStudent student) {

        for (Attempt attempt : attempts) {
            if (attempt.getStudent() == student && attempt.isSubmitted()) {
                System.out.println(
                        "Student already has a submitted attempt for '" +
                        name + "'."
                );
                return null;
            }
        }

        Attempt attempt = new Attempt(student, this);
        attempts.add(attempt);

        System.out.println(
                "Examination '" +
                name +
                "' started by " +
                student.getName() +
                "."
        );

        return attempt;
    }
}

class Attempt {
    private ExamStudent student;
    private Examination examination;
    private ArrayList<String> answers;
    private boolean submitted;

    public Attempt(ExamStudent student, Examination examination) {
        this.student = student;
        this.examination = examination;
        answers = new ArrayList<>();
        submitted = false;
    }

    public ExamStudent getStudent() {
        return student;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void answerQuestion(int questionNumber, String answer) {

        if (submitted) {
            System.out.println(
                    "Cannot change answers after submission."
            );
            return;
        }

        answers.add(questionNumber + ":" + answer);

        System.out.println(
                "Question " +
                questionNumber +
                " answered with '" +
                answer +
                "'."
        );
    }

    public void submit() {

        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }

        submitted = true;

        System.out.println(
                "Examination '" +
                examination.getName() +
                "' submitted successfully."
        );

        evaluate();
    }

    private void evaluate() {

        int correct = 0;

        for (String answer : answers) {

            String[] parts = answer.split(":");

            int questionNumber =
                    Integer.parseInt(parts[0]);

            String studentAnswer = parts[1];

            for (Question question : examination.getQuestions()) {

                if (question.getQuestionNumber() == questionNumber) {

                    if (question.evaluate(studentAnswer)) {
                        correct++;
                    }
                }
            }
        }

        System.out.println(
                "Result for '" +
                examination.getName() +
                "' attempt: " +
                correct +
                "/" +
                examination.getQuestions().size() +
                " correct"
        );
    }
}

public class Ques1 {

    public static void main(String[] args) {

        ExamStudent student =
                new ExamStudent("Student");

        Examination examination =
                new Examination("Math Quiz");

        Question question1 =
                new MultipleChoiceQuestion(
                        1,
                        "What is 2 + 2?",
                        "A"
                );

        Question question2 =
                new MultipleChoiceQuestion(
                        2,
                        "What is 5 + 5?",
                        "B"
                );

        examination.addQuestion(question1);
        examination.addQuestion(question2);

        Attempt attempt =
                examination.start(student);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();

        attempt.answerQuestion(1, "B");
    }
}