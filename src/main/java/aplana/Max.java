package aplana;

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
        System.out.println("Enter array dimension : ");
        dimension = scanner.nextInt();
        array = new String[dimension];
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