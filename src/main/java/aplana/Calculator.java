package aplana;

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
     * Input values
     */
    private void inputNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number");
        first = scanner.nextDouble();
        System.out.println("Enter the second number");
        second = scanner.nextDouble();
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