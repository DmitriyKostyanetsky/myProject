package aplana.HW2;

/**
 * Oreo and his specifications
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 07.03.2019
 */
public class Oreo extends Sweet {

    private boolean isGlaze;
    private String supplements;

    public Oreo(String name, double weight, double price, boolean isGlaze, String supplements) {
        super(name, weight, price);
        this.isGlaze = isGlaze;
        this.supplements = supplements;
    }

    /**
     * @see Sweet
     */
    @Override
    public String toString() {
        return super.toString() + " glaze " + isGlaze + " with supplements " + supplements;
    }
}