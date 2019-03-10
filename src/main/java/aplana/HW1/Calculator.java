package aplana.HW1;

import java.util.Scanner;

/**
 * Calculator
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 05.03.2019
 */
public class Calculator {
    private double first;
    private double second;

    /**
     * Write to value
     */
    private void inputNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number");
        first = checkValidate(scanner);
        System.out.println("Enter the second number");
        second = checkValidate(scanner);
    }

    /**
     * Validation check
     * @param scanner input
     * @return correct value entered
     */
    private double checkValidate(Scanner scanner) {
        while (true) {
            if (!scanner.hasNextDouble()) {
                System.out.println("Please input positive or negative number");
                scanner.next();
            } else if (scanner.hasNextDouble() || scanner.nextDouble() < 0) {
                return scanner.nextDouble();
            }
        }
    }

    /**
     * Sum values
     * @return sum
     */
    public double sum () {
        inputNumbers();
        return first + second;
    }

    /**
     * Subtract values
     * @return subtract
     */
    public double subtract () {
        inputNumbers();
        return first - second;
    }

    /**
     * Multiply values
     * @return multiply
     */
    public double multiply () {
        inputNumbers();
        return first * second;
    }

    /**
     * Divide values
     * @return divide
     */
    public double divide () {
        inputNumbers();
        return first / second;
    }
}