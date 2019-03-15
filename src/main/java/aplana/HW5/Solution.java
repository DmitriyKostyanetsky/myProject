package aplana.HW5;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Сценарий теста росгосстраха
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 15.03.2019
 */
public class Solution {
    private static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        String path;
        WebElement element;
        List<String> actual = new ArrayList<>();
        List<String> expect;
        driver = new ChromeDriver();
        String url = "https://www.rgs.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        RgsTest rgs = new RgsTest(driver);

        // Выбираем страхование
        path = "//ol/li/a[contains(text(), 'Страхование')]";
        rgs.checkByXPath(path, "Страхование");

        // Выбираем вызжающим за рубеж
        path = "//*[contains(text(), 'Выезжающим за рубеж')]";
        rgs.checkByXPath(path, "Выезжающим за рубеж");

        rgs.scrollDown();

        // Выбираем рассчитать
        path = "//a[contains(text(), 'Рассчитать')]";
        rgs.checkByXPath(path, "РАССЧИТАТЬ");

        // Выбираем страхование выезжающих за рубеж
        path = "//span[contains(text(), 'Страхование выезжающих за')][@class='h1']";
        rgs.checkByXPath(path, "Страхование выезжающих за рубеж");

        // Выбираем несколько в течение года
        path = "//button[@data-test-value='Multiple']";
        rgs.checkByXPath(path, "");

        // Заполняем поле шенгеном
        path = "//input[@class=\"form-control-multiple-autocomplete-actual-input tt-input\"]";
        rgs.checkByXPath(path, "");
        element = rgs.fillField(path, "Шенген");
        element.sendKeys(Keys.DOWN, Keys.ENTER);

        // Выбираем Испанию
        path = "//select[@id='ArrivalCountryList']";
        element = rgs.checkByXPath(path, "");
        new Select(element).selectByVisibleText("Испания");

        rgs.scrollDown();

        // Выбираем дату первой поездки
        path = "//input[@data-test-name='FirstDepartureDate']";
        element = rgs.checkByXPath(path, "");
        rgs.createDate(element);
        element.sendKeys(Keys.ENTER);

        // Не более 90 дней
        path = "//label[@class = 'btn btn-attention']";
        rgs.checkByXPath(path, "Не более 90 дней");


        // Заполняем ФИО и дату рождения
        path = "//input[@placeholder=\"NIKOLAEV NIKOLAY\"][@class=\"form-control\" != @disabled]";
        rgs.checkByXPath(path, "");
        path = "//input[@data-test-name=\"BirthDate\"]";
        rgs.checkByXPath(path, "");
        path = "//input[@placeholder=\"NIKOLAEV NIKOLAY\"][@class=\"form-control validation-control-has-error\"]";
        driver.findElement(By.xpath(path)).clear();
        driver.findElement(By.xpath(path)).sendKeys("MATIUS DERZKI");
        path = "//input[@data-test-name=\"BirthDate\"][@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-error\"]";
        driver.findElement(By.xpath(path)).click();
        driver.findElement(By.xpath(path)).clear();
        driver.findElement(By.xpath(path)).sendKeys("11021988");

        // Выбираем активный отдых
        path = "//*[text() = ' активный отдых или спорт ']/ancestor::div[@class=\"calc-vzr-toggle-risk-group\"]//div[@class=\"toggle off toggle-rgs\"]";
        rgs.checkboxCheck(path);

        rgs.scrollDown();

        // Соглашаемся с условиями
        path = "//input[@data-test-name=\"IsProcessingPersonalDataToCalculate\"]";
        rgs.checkboxCheck(path);

        // Рассчитываем сумму
        path = "//button[contains(text(), 'Рассчитать')][@class=\"btn btn-primary btn-sm text-uppercase text-semibold\"]";
        rgs.checkByXPath(path, "РАССЧИТАТЬ");

        // Ждем рассчета калькулятора
        path = "//div[@class=\"program-name\"][contains(text(), \"Комфорт\")]";
        rgs.checkByXPath(path, "Комфорт");
        rgs.scrollTop();

        System.out.println("----------Итоговый отчет----------");
        path = "//div[@class=\"summary-row\"]//child::span[contains(text(), 'Многократные')]";
        actual.add(path);

        path = "//div[@class=\"summary-row\"]//child::strong[contains(text(), 'Шенг')]";
        actual.add(path);
        path = "//div[@class=\"summary-row\"]//child::strong[contains(text(), 'MATIUS')]";
        actual.add(path);

        path = "//div[@class=\"summary-row\"]//child::strong[contains(text(), '11')]";
        actual.add(path);

        path = "//div[@class=\"summary-row\"]//child::small[contains(text(), 'включая активный')]";
        actual.add(path);

        expect = new ArrayList<String>() {{
            add("Многократные");
            add("Шенген");
            add("MATIUS");
            add("11");
            add("включая активный");
        }};

        if(rgs.verificationReport(actual, expect)) {
            System.out.println("Сценарий пройден успешно");
        } else {
            System.out.println("Сценарий пройден НЕ успешно :( ");
        }
        driver.quit();
    }
}