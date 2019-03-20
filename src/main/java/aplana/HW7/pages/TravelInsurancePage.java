package aplana.HW7.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TravelInsurancePage extends BasePage {

    /**
     * Страхование выезжающих
     */
    @FindBy(xpath = "//a[contains(text(), 'Страхование выезжающих')]")
    public WebElement departingBtn;
}