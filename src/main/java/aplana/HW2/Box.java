package aplana.HW2;

/**
 * Box interface
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 07.03.2019
 */
public interface Box {
    void add(Sweet sweet);
    void delete(int index);
    double showWeight();
    double showPrice();
    void showInfo();
    void reduceWeight();
    void reducePrice();
}