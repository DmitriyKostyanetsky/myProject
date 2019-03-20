package aplana.HW7;

import org.openqa.selenium.WebDriver;

public class Init {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Init.driver = driver;
    }
}