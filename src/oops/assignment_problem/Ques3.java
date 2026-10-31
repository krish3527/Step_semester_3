
package oops.assignment_problem.session_11;

import java.util.Stack;

public class Ques3 {

    public static int precedence(char op) {

        if (op == '+' || op == '-') {
            return 1;
        }

        if (op == '*' || op == '/') {
            return 2;
        }

        return 0;
    }

    public static int calculate(int a, int b, char op) {

        switch (op) {

            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                return a / b;
        }

        return 0;
    }

    public static void applyOperation(Stack<Integer> values,
                                      Stack<Character> operators) {

        int b = values.pop();
        int a = values.pop();

        char op = operators.pop();

        int result = calculate(a, b, op);

        values.push(result);
    }

    public static int evaluate(String expr) {

        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;

        while (i < expr.length()) {

            char ch = expr.charAt(i);

            if (ch == ' ') {
                i++;
                continue;
            }

            if (Character.isDigit(ch)) {

                int num = 0;

                while (i < expr.length()
                        && Character.isDigit(expr.charAt(i))) {

                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                values.push(num);
                continue;
            }

            else if (ch == '(') {

                operators.push(ch);
            }

            else if (ch == ')') {

                while (!operators.isEmpty()
                        && operators.peek() != '(') {

                    applyOperation(values, operators);
                }

                operators.pop();
            }

            else {

                while (!operators.isEmpty()
                        && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {

                    applyOperation(values, operators);
                }

                operators.push(ch);
            }

            i++;
        }

        while (!operators.isEmpty()) {

            applyOperation(values, operators);
        }

        return values.pop();
    }

    public static void main(String[] args) {

        String expr1 = "2 * (3 + 4) - 10 / 5";

        String expr2 = "(8 - 2) * (1 + 2) / 4";

        System.out.println("Expression 1: " + expr1);
        System.out.println("Result: " + evaluate(expr1));

        System.out.println();

        System.out.println("Expression 2: " + expr2);
        System.out.println("Result: " + evaluate(expr2));
    }
}
