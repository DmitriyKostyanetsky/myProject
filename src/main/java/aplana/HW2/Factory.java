package aplana.HW2;

import java.util.concurrent.ThreadLocalRandom;

public class Factory implements SweetsFactory {

    @Override
    public Sweet createSweet() {
        int random = ThreadLocalRandom.current().nextInt(0, 10);
        Sweet sweet1;
        if (random >= 5) {
            sweet1 = new Nutella("Wow", 33.1, 199.99, "strawberry", true);
        } else {
            sweet1 = new Oreo("MamaMia", 40.46, 80.45, true, "nut");
        }
        return sweet1;
    }
}