package aplana.HW5;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Сценарий теста росгосстраха
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 15.03.2019
 */
public class RgsTest {

    private WebDriver driver;
    public RgsTest(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Проверяет, что элемент по xpath"у найден, а так же ждет 10 секунд отклика от элемента
     * @param path путь до элемента
     * @param expect ожидаемый текст
     * @return веб-элемент
     */
    public WebElement checkByXPath(String path, String expect) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        WebElement element = driver.findElement(By.xpath(path));
        wait.until(ExpectedConditions.visibilityOf(element));
        if (element.getText().contains(expect)) {
            element.click();
            System.out.println("Совпадение : " + expect);
        } else {
            System.out.println("Несовпадение : " + expect + " , вместо этого : " + element.getText());
            driver.quit();
        }
        return element;
    }

    /**
     * Проскролить окно до элемента
     */
    public void scrollTop() {
        //WebElement element = driver.findElement(By.xpath("//div/img[@src=\"/media/sys-ng/dist/images/brand/logo.svg\"]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    /**
     * Проскролить окно на 900 пикселей вниз
     */
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 900)");
    }

    /**
     * Заполняет текстовое поле
     * @param path путь до элемента
     * @param input текст, который надо ввести
     * @return веб-элемент
     */
    public WebElement fillField(String path, String input) {
        WebElement element = driver.findElement(By.xpath(path));
        element.click();
        element.clear();
        element.sendKeys(input);
        return element;
    }

    /**
     * Создает текущую дату и прибавляет к ней 2 недели
     * @param element путь до элемента
     */
    public void createDate(WebElement element) {
        Long date = (new Date().getTime()) + (14 * 24 * 3600 * 1000);
        Date newDate = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM y");
        System.out.println("Дата плюс 2 недели : " + dateFormat.format(newDate));
        element.sendKeys(dateFormat.format(newDate));
    }

    /**
     * Проверка чекбокса включен он или нет
     * @param path путь до элемента
     */
    public void checkboxCheck(String path) {
        if ( !driver.findElement(By.xpath(path)).isSelected() ) {
            driver.findElement(By.xpath(path)).click();
            System.out.println("В чекбокс поставлена галочка");
        } else {
            System.out.println("В чекбоксе уже поставлена галочка");
        }
    }

    /**
     * Проверка отчета
     * @param actual актуальные данные
     * @param expect ожидаемые
     * @return true если данные совпали, false если не совпали
     */
    public boolean verificationReport(List<String> actual, List<String> expect) {
        WebElement element;
        for (int i = 0; i < actual.size(); i++) {
            element = driver.findElement(By.xpath(actual.get(i)));
            if (element.getText().contains(expect.get(i))) {
                System.out.println("Совпадение " + element.getText());
            } else {
                System.out.println("Не совпадение " + element.getText());
                return false;
            }
        }
        return true;
    }
}