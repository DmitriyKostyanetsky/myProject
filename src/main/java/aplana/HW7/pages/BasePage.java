package aplana.HW7.pages;

import aplana.HW7.Init;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Init.getDriver(), this);
    }

    public void waitElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Init.getDriver(), 30, 1000);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickOnElement(WebElement element) {
        waitElement(element);
        element.click();
    }

    public void inputInField(WebElement element, String inputText) {
        element.clear();
        element.click();
        element.sendKeys(inputText);
    }

    /**
     * Проскролить окно до элемента
     */
    public void scrollDown(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Init.getDriver();
        Assert.assertNotNull(element);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}