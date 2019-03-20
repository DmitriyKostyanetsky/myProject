package aplana.HW7.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TouristsAbroadPage extends BasePage {

    /**
     * Кнопка РАССЧИТАТЬ
     */
    @FindBy(xpath = "//a[contains(text(), 'Рассчитать')]")
    public WebElement calcBtn;
}