package aplana.HW2;

/**
 * Marshmallow and his specifications
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 07.03.2019
 */
public class Marshmallow extends Sweet{

    private String type;
    private boolean isSugar;

    public Marshmallow(String name, double weight, double price, String type, boolean isSugar) {
        super(name, weight, price);
        this.type = type;
        this.isSugar = isSugar;
    }

    /**
     * @see Sweet
     */
    @Override
    public String toString() {
        return super.toString() + "marshmallow";
    }

    /**
     * @see Sweet
     */
    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("type " + type + " sugar is " + isSugar);
    }
}