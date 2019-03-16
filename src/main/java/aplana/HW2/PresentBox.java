package aplana.HW2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Present box
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 16.03.2019
 */
public class PresentBox implements Box{

    private List<Sweet> sweets = new ArrayList<>();
    private Predicate<Sweet> predicate;

    public PresentBox() {}

    public PresentBox(Predicate<Sweet> predicate) {
        this.predicate = predicate;
    }

    /**
     * Add sweet in box
     * @param sweet we wont to add
     */
    @Override
    public void add(Sweet sweet) {
        if (predicate.test(sweet)) {
            sweets.add(sweet);
        }
    }

    /**
     * Политика предиката
     * @param predicate предикат с условием
     */
    @Override
    public void setPolicy(Predicate<Sweet> predicate) {
        this.predicate = predicate;
    }

    /**
     * Delete sweet from box
     * @param index by which we delete
     */
    @Override
    public void delete(int index) {
        if (!checkEmptyBox()) {
            sweets.remove(index);
        }
    }

    /**
     * Show info about sweets in box
     */
    @Override
    public void showInfo() {
        if (!checkEmptyBox()) {
            for (int i = 0; i < sweets.size(); i++) {
                System.out.println(i + 1 + ") " + sweets.get(i).toString());
            }
        }
    }

    /**
     * Check that box empty or not
     * @return true if box empty, false if box not empty
     */
    @Override
    public boolean checkEmptyBox() {
        if (sweets.isEmpty()) {
            System.out.println("Ничего нет в коробке!");
            return true;
        }
        return false;
    }

    /**
     * Reduce sweet if weight of all sweets more then 100
     */
    @Override
    public void reduceWeight() {
        if (!checkEmptyBox()) {
            double min = sweets.get(0).getWeight();
            int indexOfMin = 0;
            for (int i = 0; i < sweets.size(); i++) {
                if (sweets.get(i).getWeight() < min) {
                    min = sweets.get(i).getWeight();
                    indexOfMin = i;
                }
            }
            delete(indexOfMin);
        }
    }

    /**
     * Reduce sweet if price for all sweets more then 500
     */
    @Override
    public void reducePrice() {
        if (!checkEmptyBox()) {
            double min = sweets.get(0).getPrice();
            int indexOfMin = 0;
            for (int i = 0; i < sweets.size(); i++) {
                if (sweets.get(i).getPrice() < min) {
                    min = sweets.get(i).getPrice();
                    indexOfMin = i;
                }
            }
            delete(indexOfMin);
        }
    }

    /**
     * Конвертация рублей в доллары
     * @param function функция, переводящая сумму в доллары
     */
    @Override
    public void convertToDollar(Function<Double, Double> function) {
        if (!checkEmptyBox()) {
            double result = totalSum();
            result = function.apply(result);
            System.out.println("Сумма в долларах : " + result);
        }
    }

    /**
     * Конвертация рублей в евро
     * @param function функция, переводящая сумму в евро
     */
    @Override
    public void convertToEuro(Function<Double, Double> function) {
        if (!checkEmptyBox()) {
            double result = totalSum();
            result = function.apply(result);
            System.out.println("Сумма в евро : " + result);
        }
    }

    /**
     * Сортировка по условию
     */
    public void sortByComparator(Comparator<Sweet> comparator) {
        sweets.stream()
                .sorted(comparator)
                .forEach(sweet -> System.out.println("В коробке лежит : " + sweet.getClass().getSimpleName()));
    }

    /**
     * Фильтр по условию
     */
    public void filterByPredicate(Predicate<Sweet> predicate) {
        sweets.stream()
                .filter(predicate)
                .forEach(sweet -> System.out.println(sweet.toString()));
    }

    /**
     * Сумма сладостей
     */
    public double totalSum() {
        return priceList().stream()
                .reduce(0.0, Double::sum);
    }

    /**
     * Метод добавляет цену сладости в новый лист
     * @return прайс лист сладостей
     */
    private List<Double> priceList() {
        List<Double> list = new ArrayList<>();
        sweets.forEach(sweet -> list.add(sweet.getPrice()));
        return list;
    }

    /**
     * Вес всех сладостей
     */
    public double totalWeight() {
        return weightList().stream()
                .reduce(0.0, Double::sum);
    }

    /**
     * Метод добавляет цену сладости в новый лист
     * @return прайс лист сладостей
     */
    private List<Double> weightList() {
        List<Double> list = new ArrayList<>();
        sweets.forEach(sweet -> list.add(sweet.getWeight()));
        return list;
    }
}