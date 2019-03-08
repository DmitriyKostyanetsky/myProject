package aplana.HW2;

/**
 * Collecting sweets in box
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 07.03.2019
 */
public class Solution {
    public static void main(String[] args) {
        Box box = new PresentBox();
        Sweet sweet1 = new Marshmallow("marsh777", 13.53, 150, "chocolate", true);
        Sweet sweet2 = new Oreo("MamaMia", 40.46, 80.45, true, "nut");
        Sweet sweet3 = new Nutella("Boom", 70.35, 240.99, "vanilla", false);
        Sweet sweet4 = new Nutella("Wow", 33.1, 199.99, "strawberry", true);
        box.add(sweet1);
        box.add(sweet2);
        box.add(sweet3);
        box.add(sweet4);

        box.showInfo();

        while (box.showPrice() > 500) {
            box.reducePrice();
            box.showInfo();
        }

        while (box.showWeight() > 100) {
            box.reduceWeight();
            box.showInfo();
        }

        box.showInfo();
    }
}