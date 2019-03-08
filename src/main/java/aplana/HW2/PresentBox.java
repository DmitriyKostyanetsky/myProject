package aplana.HW2;

import java.util.Arrays;

/**
 * Present box
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 07.03.2019
 */
public class PresentBox implements Box {

    private Sweet[] sweets = new Sweet[3];

    /**
     * Add sweet in box
     * @param sweet we wont to add
     */
    @Override
    public void add(Sweet sweet) {
        boolean isSweetAdd = false;
        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i] == null) {
                sweets[i] = sweet;
                isSweetAdd = true;
                break;
            }
        }

        if (!isSweetAdd) {
            sweets = Arrays.copyOf(sweets, sweets.length + 1);
            sweets[sweets.length - 1] = sweet;
        }
    }

    /**
     * Delete sweet from box
     * @param index by which we delete
     */
    @Override
    public void delete(int index) {
        if (index > sweets.length - 1) {
            System.out.println("Index not found. Try again");
        } else {
            for (int i = 0; i < sweets.length; i++) {
                if (sweets[index] != sweets[i]) {
                    sweets[index] = null;
                    break;
                }
            }
        }

        Sweet[] temp = new Sweet[sweets.length - 1];
        int count = 0;
        for (Sweet sweet : sweets) {
            if (sweet != null) {
                temp[count++] = sweet;
            }
        }

        System.arraycopy(temp, 0, sweets, 0, sweets.length - 1);
        sweets = Arrays.copyOf(sweets, sweets.length - 1);
    }

    /**
     * Show weight of all sweets
     * @return weight
     */
    @Override
    public double showWeight() {
        double result = 0;
        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i] != null) {
                result += sweets[i].getWeight();
            }
        }
        System.out.println("Weight of all sweets : " + result);
        return result;
    }

    /**
     * Show price for all sweets
     * @return price
     */
    @Override
    public double showPrice() {
        double result = 0;
        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i] != null) {
                result += sweets[i].getPrice();
            }
        }
        System.out.println("Price for all sweets : " + result);
        return result;
    }

    /**
     * Show info about sweets in box
     */
    @Override
    public void showInfo() {
        for (Sweet sweet : sweets) {
            System.out.println(sweet.toString());
        }
    }

    /**
     * Reduce sweet if weight of all sweets more then 100
     */
    @Override
    public void reduceWeight() {
        double min = sweets[0].getWeight();
        int indexOfMin = 0;
        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i].getWeight() < min) {
                min = sweets[i].getWeight();
                indexOfMin = i;
            }
        }
        delete(indexOfMin);
    }

    /**
     * Reduce sweet if price for all sweets more then 500
     */
    @Override
    public void reducePrice() {
        double min = sweets[0].getPrice();
        int indexOfMin = 0;
        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i].getPrice() < min) {
                min = sweets[i].getPrice();
                indexOfMin = i;
            }
        }
        delete(indexOfMin);
    }
}