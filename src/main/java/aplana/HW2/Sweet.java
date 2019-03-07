package aplana.HW2;

/**
 * Sweet and his specifications
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 07.03.2019
 */
public abstract class Sweet {
    private String name;
    private double weight, price;

    public Sweet(String name, double weight, double price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    /**
     * Show what this sweet
     * @return specific sweet
     */
    @Override
    public String toString() {
        return "Sweet ";
    }

    /**
     * Info about sweet
     */
    public void getInfo() {
        System.out.print(name + " weight " + weight + " price " + price + " ");
    }

    /**
     * Sweet weight
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sweet price
     * @return price
     */
    public double getPrice() {
        return price;
    }
}