package aplana.HW1;

import java.util.Scanner;

/**
 * Max length word in string array
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 05.03.2019
 */
public class Max {
    private String[] array;
    private Scanner scanner = new Scanner(System.in);
    private int dimension;

    public Max() {
        boolean isCorrectValue = false;
        while (!isCorrectValue) {
            System.out.println("Enter array dimension : ");
            isCorrectValue = checkCorrectValue();
        }
    }

    /**
     * Validation check
     * @return true if input correct value(positive number), false if input incorrect value(other)
     */
    private boolean checkCorrectValue() {
        if (scanner.hasNextInt()) {
            dimension = scanner.nextInt();
            if (dimension > 0) {
                array = new String[dimension];
                return true;
            } else if (dimension == 0) {
                System.out.println("Please input number over then zero");
            } else {
                System.out.println("Please input positive number");
            }
        } else {
            System.out.println("Incorrect value. Please input positive number");
            scanner.next();
        }
        return false;
    }

    /**
     * Search max length word in string array
     */
    public void searchMaxWord() {
        System.out.println("Enter " + dimension + " words");
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.next();
        }

        String result = array[0];

        for (int i = 1; i < array.length; i++) {
            if (result.length() < array[i].length()) {
                result = array[i];
            } else if (result.length() == array[i].length()) {
                result = result + " and " + array[i];
            }
        }
        System.out.println("The longest word is : " + result);
    }
}