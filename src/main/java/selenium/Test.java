package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver-v0.24.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String url = "https://www.yandex.ru/";
        driver.get(url);
        String getUrl = driver.getCurrentUrl();
        if (url.equals(getUrl)) {
            WebElement element = driver.findElement(By.cssSelector("input#text"));

            element.sendKeys("query");
            element.submit();
            System.out.println("Page title is: " + driver.getTitle());
            (new WebDriverWait(driver, 10)).until((ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith("query"));

            System.out.println("Page title is: " + driver.getTitle());

        }
        //driver.navigate().to("https://news.sportbox.ru/");
        //driver.navigate().back();
        driver.quit();
    }

    
    public void testDoubleClick() throws Exception {

    }
}
