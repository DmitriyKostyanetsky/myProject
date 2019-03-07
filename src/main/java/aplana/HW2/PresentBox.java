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
        for (int i = 0; i < sweets.length; i++) {
            if (sweets[index] == sweets[i]) {
                sweets[index] = null;
                break;
            }
        }
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
            if (sweet != null) {
                sweet.getInfo();
            }
        }
    }

    /**
     * Reduce sweet if weight of all sweets more then 100
     */
    @Override
    public void reduceWeight() {
        double min = sweets[keepMin()].getWeight();
        for (Sweet sweet : sweets) {
            if (sweet != null && sweet.getWeight() < min) {
                min = sweet.getWeight();
            }
        }

        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i] != null && sweets[i].getWeight() == min) {
                delete(i);
                break;
            }
        }
    }

    /**
     * Reduce sweet if price for all sweets more then 500
     */
    @Override
    public void reducePrice() {
        double min = sweets[keepMin()].getPrice();
        for (Sweet sweet : sweets) {
            if (sweet != null && sweet.getPrice() < min) {
                min = sweet.getPrice();
            }
        }

        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i] != null && sweets[i].getPrice() == min) {
                delete(i);
                break;
            }
        }
    }

    /**
     * Selecting the first non-zero element to assign a minimum
     * @return index element
     */
    private int keepMin() {
        for (int i = 0; i < sweets.length; i++) {
            if (sweets[i] != null) {
                return i;
            }
        }
        return 0;
    }
}