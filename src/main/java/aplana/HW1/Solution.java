package aplana.HW1;

import java.util.Scanner;

/**
 * Homework 1
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 05.03.2019
 */
public class Solution {

    /**
     * Performance of a choice task
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter number of task: (1 - calculator, 2 - string array, 0 - exit)" );
            Scanner scanner = new Scanner(System.in);

            if (!scanner.hasNextDouble()) {
                System.out.println("Incorrect value. Please input positive number");
            } else {
                int i = scanner.nextInt();

                if (i == 1) {
                    System.out.println("Enter operation: (1 - sum, 2 - subtract, 3 - multiple, 4 - divide)");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Incorrect value. Please input positive number");
                    } else {
                        int choose = scanner.nextInt();
                        Calculator calc = new Calculator();
                        double result;

                        switch (choose) {
                            case 1:
                                result = calc.sum();
                                System.out.printf("Sum is : %.4f", result);
                                break;
                            case 2:
                                result = calc.subtract();
                                System.out.printf("Subtract is : %.4f", result);
                                break;
                            case 3:
                                result = calc.multiply();
                                System.out.printf("Multiply is : %.4f", result);
                                break;
                            case 4:
                                result = calc.divide();
                                System.out.printf("Divide is : %.4f", result);
                                break;
                            default:
                                System.err.println("Incorrect value");
                        }
                    }
                }

                if (i == 2) {
                    Max max = new Max();
                    max.searchMaxWord();
                }

                if (i == 0) {
                    scanner.close();
                    break;
                }
            }
        }
    }
}