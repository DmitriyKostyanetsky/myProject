package aplana.HW2;

/**
 * Nutella and his specifications
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 07.03.2019
 */
public class Nutella extends Sweet {

    private String filling;
    private boolean isPalmOil;

    public Nutella(String name, double weight, double price, String filling, boolean isPalmOil) {
        super(name, weight, price);
        this.filling = filling;
        this.isPalmOil = isPalmOil;
    }

    /**
     * @see Sweet
     */
    @Override
    public String toString() {
        return super.toString() + "filling " + filling + " palm oil is " + isPalmOil;
    }
}