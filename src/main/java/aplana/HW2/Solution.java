package aplana.HW2;

import java.util.function.Predicate;

/**
 * Collecting sweets in box
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 16.03.2019
 */
public class Solution {
    public static void main(String[] args) {
        // Фабрика создает только зефир
        SweetsFactory factoryOne = new SweetsFactory() {
            @Override
            public Sweet createSweet() {
                return new Marshmallow("marsh777", 13.53, 150, "chocolate", true);
            }
            };
        Sweet sweetFirstFactory = factoryOne.createSweet();

        // Фабрика создает или нутеллу или печенье
        Factory factorySecond = new Factory();
        Sweet sweetSecondFactory = factorySecond.createSweet();

        // Фабрика создает разные виды нутеллы
        SweetsFactory factoryThird = () -> {
            if (factoryOne.toString().length() > factorySecond.toString().length()) {
                return new Nutella("Boom", 70.35, 240.99, "vanilla", false);
            } else {
                return new Nutella("Wow", 33.1, 199.99, "strawberry", true);
            }
        };
        Sweet sweetThirdFactory = factoryThird.createSweet();

        System.out.println("Сладости сделанные на первой фабрике : " + sweetFirstFactory.toString());
        System.out.println("Сладости сделанные на второй фабрике : " + sweetSecondFactory.toString());
        System.out.println("Сладости сделанные на третьей фабрике : " + sweetThirdFactory.toString());
        System.out.println("--------------------------------------------------------");

        // Первая коробка
        Box box = new PresentBox();
        box.setPolicy(sweet -> sweet.getPrice() < 200);
        box.add(sweetFirstFactory);
        box.add(sweetSecondFactory);
        box.add(sweetThirdFactory);
        System.out.println("В первой коробке содержится :");
        box.showInfo();

        // Вторая коробка
        Box box2 = new PresentBox(new Predicate<Sweet>() {
            @Override
            public boolean test(Sweet sweet) {
                return sweet.getWeight() > 50;
            }
        });
        box2.add(sweetFirstFactory);
        box2.add(sweetSecondFactory);
        box2.add(sweetThirdFactory);
        System.out.println("Во второй коробке содержится :");
        box2.showInfo();

        // Третья коробка
        Box box3 = new PresentBox();
        box3.setPolicy(sweet -> sweet.getClass().getSimpleName().equals("Oreo"));
        box3.add(sweetFirstFactory);
        box3.add(sweetSecondFactory);
        box3.add(sweetThirdFactory);
        System.out.println("В третьей коробке содержится :");
        box3.showInfo();

        // Четвертая коробка
        Box box4 = new PresentBox(new Predicate<Sweet>() {
            @Override
            public boolean test(Sweet sweet) {
                return sweet.getWeight() > 30 && sweet.getPrice() < 150;
            }
        });
        box4.add(sweetFirstFactory);
        box4.add(sweetSecondFactory);
        box4.add(sweetThirdFactory);
        System.out.println("В четвертой коробке содержится :");
        box4.showInfo();

        System.out.println("--------------------------------------------------------");

        // Перевод суммы в доллары и евро
        System.out.println("Конвертация суммы из первой коробки");
        box.convertToDollar(aDouble -> aDouble * 70);
        box.convertToEuro(aDouble -> aDouble * 80);

        System.out.println("Конвертация суммы из третьей коробки");
        box.convertToDollar(aDouble -> aDouble * 70);
        box.convertToEuro(aDouble -> aDouble * 80);

        System.out.println("--------------------------------------------------------");

        // Общая сумма и общий вес 1ой коробки
        ((PresentBox) box).sortByComparator((o1, o2) -> Double.compare(o2.getWeight(), o1.getWeight()));
        double res = ((PresentBox) box).totalSum();
        System.out.println("Сумма всех сладостей в рублях : " + res);
        res = ((PresentBox) box).totalWeight();
        System.out.println("Общий вес составляет : " + res);

        // Общая сумма и общий вес 3ей коробки
        res = ((PresentBox) box3).totalSum();
        System.out.println("Сумма всех сладостей в рублях : " + res);
        res = ((PresentBox) box3).totalWeight();
        System.out.println("Общий вес составляет : " + res);

        System.out.println("--------------------------------------------------------");

        // Фильтр данных по условию
        System.out.println("Поиск нутеллы");
        ((PresentBox) box).filterByPredicate(s -> s.getClass().getSimpleName().startsWith("Nut"));

        System.out.println("Поиск сладости с ценой больше 100");
        ((PresentBox) box2).filterByPredicate(s -> s.getPrice() > 100);

        System.out.println("Поиск сладости с весом больше 20 и ценой меньше 200");
        ((PresentBox) box3).filterByPredicate(s -> s.getWeight() > 20 && s.getPrice() < 200);

        System.out.println("Поиск печенья с ценой меньше 100");
        ((PresentBox) box4).filterByPredicate(s -> s.getClass().getSimpleName().startsWith("Or") && s.getPrice() < 100);
    }
}