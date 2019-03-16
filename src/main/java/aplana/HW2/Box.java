package aplana.HW2;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Box interface
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 16.03.2019
 */
public interface Box {
    void add(Sweet sweet);
    void delete(int index);
    void showInfo();
    void reduceWeight();
    void reducePrice();
    boolean checkEmptyBox();
    void setPolicy(Predicate<Sweet> predicate);
    void convertToDollar(Function<Double, Double> function);
    void convertToEuro(Function<Double, Double> function);
}