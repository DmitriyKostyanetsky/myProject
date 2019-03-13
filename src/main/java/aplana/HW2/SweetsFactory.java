package aplana.HW2;

@FunctionalInterface
public interface SweetsFactory<S extends Sweet> {
    S createSweet();
}