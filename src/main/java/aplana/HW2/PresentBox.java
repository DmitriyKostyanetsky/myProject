package aplana.HW2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Present box
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 13.03.2019
 */
public class PresentBox implements Box, Policy, RubleToDollar, RubleToEuro{

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
     * @param list лист со сладостями
     */
    @Override
    public void convertToDollar(List<Sweet> list) {
        if (!checkEmptyBox()) {
            double result = totalSum();
            Function<Double, Double> function1 = aDouble -> aDouble / 70;
            result = function1.apply(result);
            System.out.println("Сумма в долларах : " + result);
        }
    }

    /**
     * Конвертация рублей в евро
     * @param list лист со сладостями
     */
    @Override
    public void convertToEuro(List<Sweet> list) {
        if (!checkEmptyBox()) {
            double result = totalSum();
            Function<Double, Double> function2 = aDouble -> aDouble / 85;
            result = function2.apply(result);
            System.out.println("Сумма в евро : " + result);
        }
    }

    public List<Sweet> getSweets() {
        return sweets;
    }

    /**
     * Название класса каждой сладости
     */
    public void everyClassName() {
        sweets.stream()
                .sorted((o1, o2) -> Double.compare(o2.getWeight(), o1.getWeight()))
                .forEach(sweet -> System.out.println("В коробке лежит : " + sweet.getClass().getSimpleName()));
    }

    /**
     * Подсчет кол-ва нутеллы в коробке
     */
    public void nutCount() {
        Predicate<Sweet> predicate = s -> s.getClass().getSimpleName().startsWith("Nut");
        long i = sweets.stream()
                .filter(predicate)
                .count();
        System.out.print("Количество банок нутеллы : " + i);
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