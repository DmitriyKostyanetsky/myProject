package aplana.HW7.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    /**
     * Страхование
     */
    @FindBy(xpath = "//ol/li/a[contains(text(), 'Страхование')]")
    public WebElement insuranceBtn;

    /**
     * Путешествия
     */
    @FindBy(xpath = "//a[@href=\"https://www.rgs.ru/products/private_person/tour/index.wbp\"]")
    public WebElement travelBtn;
}