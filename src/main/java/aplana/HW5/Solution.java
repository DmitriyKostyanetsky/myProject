package aplana.HW5;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Сценарий теста росгосстраха
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 16.03.2019
 */
public class Solution {

    private static WebDriver driver;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
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

            // Выбираем страхование
            path = "//ol/li/a[contains(text(), 'Страхование')]";
            checkByXPath(path, "Страхование");

            // Выбираем Путешествия
            path = "//a[@href=\"https://www.rgs.ru/products/private_person/tour/index.wbp\"]";
            checkByXPath(path, "Путешествия");

            // Выбираем Страхование выезжающих за рубеж
            path = "//a[contains(text(), 'Страхование выезжающих')]";
            checkByXPath(path, "Страхование выезжающих");

            // Выбираем рассчитать
            path = "//a[contains(text(), 'Рассчитать')]";
            scrollDown(driver.findElement(By.xpath(path)));
            checkByXPath(path, "РАССЧИТАТЬ");

            // Выбираем страхование выезжающих за рубеж
            path = "//span[contains(text(), 'Страхование выезжающих за')][@class='h1']";
            checkByXPath(path, "Страхование выезжающих за рубеж");

            // Выбираем несколько в течение года
            path = "//button[@data-test-value='Multiple']";
            checkByXPath(path, "");

            // Заполняем поле шенгеном
            path = "//input[@class=\"form-control-multiple-autocomplete-actual-input tt-input\"]";
            checkByXPath(path, "");
            element = fillField(path, "Шенген");
            element.sendKeys(Keys.DOWN, Keys.ENTER);

            // Выбираем Испанию
            path = "//select[@id='ArrivalCountryList']";
            element = checkByXPath(path, "");
            new Select(element).selectByVisibleText("Испания");

            // Выбираем дату первой поездки
            path = "//input[@data-test-name='FirstDepartureDate']";
            scrollDown(driver.findElement(By.xpath(path)));
            element = checkByXPath(path, "");
            createDate(element);
            element.sendKeys(Keys.ENTER);

            // Не более 90 дней
            path = "//label[@class = 'btn btn-attention']";
            checkByXPath(path, "Не более 90 дней");

            // Заполняем ФИО и дату рождения
            path = "//div[@data-fi-input-mode=\"combined\"]//div[@class=\"form-group\"]//input[@class=\"form-control\" != @disabled]";
            element = checkByXPath(path, "");
            element.click();
            element.clear();
            element.sendKeys("MATIUS DERZKI");

            path = "//input[@data-test-name=\"BirthDate\"]";
            element = checkByXPath(path, "");
            element.click();
            element.clear();
            element.sendKeys("11021998");

            // Выбираем активный отдых
            path = "//*[contains(text(), 'активный отдых или спорт')]/ancestor::div[@class=\"calc-vzr-toggle-risk-group\"]//div[@class=\"toggle off toggle-rgs\"]";
            checkboxCheck(path);

            // Проверяем правильность ввода ФИО
            path = "//div[@class=\"pull-left margin-right-ms-0 width-xs-auto width-sm-21rem margin-bottom-ms-2\"]//input[@class=\"form-control validation-control-has-success\" != @disabled][@data-test-name=\"FullName\"]";
            String errorPath = "//div[@class=\"pull-left margin-right-ms-0 width-xs-auto width-sm-21rem margin-bottom-ms-2\"]//input[@class=\"form-control validation-control-has-error\" != @disabled][@data-test-name=\"FullName\"]";
            checkInsertedValues("MATIUS DERZKI", path, errorPath);

            // Проверяем правильность ввода даты
            path = "//input[@data-test-name=\"BirthDate\"][@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-success\"]";
            errorPath = "//input[@data-test-name=\"BirthDate\"][@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-error\"]";
            checkInsertedValues("11021998", path, errorPath);

            // Соглашаемся с условиями
            path = "//input[@data-test-name=\"IsProcessingPersonalDataToCalculate\"]";
            scrollDown(driver.findElement(By.xpath(path)));
            System.out.println("Прошел скролл");
            checkboxCheck(path);
            System.out.println("Прошел чекбокс");

            // Рассчитываем сумму
            path = "//button[contains(text(), 'Рассчитать')][@class=\"btn btn-primary btn-sm text-uppercase text-semibold\"]";
            checkByXPath(path, "РАССЧИТАТЬ");

            // Ждем рассчета калькулятора
            path = "//div[@class=\"program-name\"][contains(text(), \"Комфорт\")]";
            checkByXPath(path, "Комфорт");

            path = "//span[@class='h1'][contains(text(), 'Страхование')]";
            scrollDown(driver.findElement(By.xpath(path)));
            checkByXPath(path, "Страхование");

            System.out.println("----------Итоговый отчет----------");
            path = "//span[@data-bind=\"with: Trips\"]//child::span[@class='text-bold']";
            actual.add(path);

            path = "//span[@data-bind=\"foreach: countries\"]//child::strong[@data-bind=\"text: Name\"]";
            actual.add(path);
            path = "//div[@class=\"summary-row\"]//child::strong[@data-bind=\"text: LastName() + ' ' + FirstName()\"]";
            actual.add(path);

            path = "//div[@class=\"summary-row\"]//child::strong[@data-bind=\" text: BirthDay.repr('moscowRussianDate')\"]";
            actual.add(path);

            path = "//div[@style=\"visibility: visible; opacity: 1; display: block; transform: translateX(0px);\"]//child::small[@data-bind=\"text: ko.unwrap('undefined' === typeof info ? '' : info)\"]";
            actual.add(path);

            expect = new ArrayList<String>() {{
                add("Многократные поездки в течение года");
                add("Шенген");
                add("MATIUS DERZKI");
                add("11.02.1998");
                add("(включая активный отдых)");
            }};

            if (verificationReport(actual, expect)) {
                System.out.println("Сценарий пройден успешно");
            } else {
                System.out.println("Сценарий пройден НЕ успешно :( ");
            }
            driver.quit();
        }
    }

    /**
     * Проверяет правильность ввода данных. Вводит до тех пор пока данные не введутся коректно
     * @param insertedValue значение которое было введено
     * @param path ожидаемый xpath
     * @param errorPath xpath с ошибочными данными
     */
    private static void checkInsertedValues(String insertedValue, String path, String errorPath) {
         Boolean isPresent = driver.findElements(By.xpath(path)).size() > 0;
         while (!isPresent) {
             System.out.println("НЕ коректно введены данные");
             WebElement element = driver.findElement(By.xpath(errorPath));
             element.click();
             element.clear();
             element.sendKeys(insertedValue);
             isPresent = driver.findElements(By.xpath(path)).size() > 0;
         }
        System.out.println("Коректно введены данные");
    }

    /**
     * Проверяет, что элемент по xpath"у найден, а так же ждет 10 секунд отклика от элемента
     * @param path путь до элемента
     * @param expect ожидаемый текст
     * @return веб-элемент
     */
    private static WebElement checkByXPath(String path, String expect) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        WebElement element = driver.findElement(By.xpath(path));

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            driver.quit();
        }

        if (element.getText().contains(expect)) {
            System.out.println("Совпадение : " + expect);
        } else {
            System.out.println("Несовпадение : " + expect + " , вместо этого : " + element.getText());
            driver.quit();
        }
        element.click();
        return element;
    }

    /**
     * Проскролить окно до элемента
     */
    private static void scrollDown(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (element == null) {
            System.out.println("Элемент не найден");
            driver.quit();
        }
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Заполняет текстовое поле
     * @param path путь до элемента
     * @param input текст, который надо ввести
     * @return веб-элемент
     */
    private static WebElement fillField(String path, String input) {
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
    private static void createDate(WebElement element) {
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
    private static void checkboxCheck(String path) {
        if (!driver.findElement(By.xpath(path)).isSelected()) {
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
    private static boolean verificationReport(List<String> actual, List<String> expect) {
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