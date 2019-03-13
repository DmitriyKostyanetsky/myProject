package aplana.HW2;

import java.util.function.Predicate;

@FunctionalInterface
public interface Policy {
    void setPolicy(Predicate<Sweet> predicate);
}