package aplana.HW7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private final Properties properties = new Properties();
    private static TestProperties INSTANCE = null;

    private TestProperties() {
        try {
            properties.load(new FileInputStream(new File("src/main/resources/app.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public static TestProperties getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }
}