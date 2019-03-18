package aplana.HW5;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.out.println("Выберите браузер для теста : 1 - Chrome 2 - Firefox 3 - InternetExplorer");
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        switch (i) {
            case 1:
                System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case 2:
                System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case 3:
                System.setProperty("webdriver.ie.driver", "drv/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                System.err.println("Incorrect value");
        }
        String url = "https://www.rgs.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        // Выбираем страхование
        String path = "//ol/li/a[contains(text(), 'Страхование')]";
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
    }

    @AfterClass
    public static void quit() {
        List<String> actual = new ArrayList<>();
        List<String> expect;

        // Рассчитываем сумму
        String path = "//button[contains(text(), 'Рассчитать')][@class=\"btn btn-primary btn-sm text-uppercase text-semibold\"]";
        checkByXPath(path, "РАССЧИТАТЬ");

        // Ждем рассчета калькулятора
        path = "//div[@class=\"program-name\"][contains(text(), \"Комфорт\")]";
        checkByXPath(path, "Комфорт");

        path = "//span[@class='h1'][contains(text(), 'Страхование')]";
        scrollDown(driver.findElement(By.xpath(path)));
        checkByXPath(path, "Страхование");

        System.out.println("----------Итоговый отчет----------");
        path = "//span[@data-bind=\"with: Trips\"]//child::span[@class='text-bold']";
        WebElement element = driver.findElement(By.xpath(path));
        actual.add(element.getText());

        path = "//span[@data-bind=\"foreach: countries\"]//child::strong[@data-bind=\"text: Name\"]";
        element = driver.findElement(By.xpath(path));
        actual.add(element.getText());

        path = "//div[@class=\"summary-row\"]//child::strong[@data-bind=\"text: LastName() + ' ' + FirstName()\"]";
        element = driver.findElement(By.xpath(path));
        actual.add(element.getText());

        path = "//div[@class=\"summary-row\"]//child::strong[@data-bind=\" text: BirthDay.repr('moscowRussianDate')\"]";
        element = driver.findElement(By.xpath(path));
        actual.add(element.getText());

        path = "//div[@style=\"visibility: visible; opacity: 1; display: block; transform: translateX(0px);\"]//child::small[@data-bind=\"text: ko.unwrap('undefined' === typeof info ? '' : info)\"]";
        element = driver.findElement(By.xpath(path));
        actual.add(element.getText());

        expect = new ArrayList<String>() {{
            add("Многократные поездки в течение года");
            add("Шенген");
            add("BENJAMIN BOCK");
            add("13.10.2000");
            add("(включая активный отдых)");
        }};

        Assert.assertEquals("Итоговые данные не совпали", expect, actual);
        System.out.println("Тест прошел успешно!");
        driver.quit();
    }

    /**
     * Проверяет правильность ввода данных. Вводит до тех пор пока данные не введутся коректно
     * @param insertedValue значение которое было введено
     * @param path ожидаемый xpath
     * @param errorPath xpath с ошибочными данными
     */
    public void checkInsertedValues(String insertedValue, String path, String errorPath) {
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
    public static WebElement checkByXPath(String path, String expect) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        WebElement element = driver.findElement(By.xpath(path));

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            driver.quit();
        }
        Assert.assertTrue("Несовпадение. Ожидалось : " + expect, element.getText().contains(expect));
        System.out.println("Искомый текст есть : " + expect);
        element.click();
        return element;
    }

    /**
     * Проскролить окно до элемента
     */
    public static void scrollDown(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Assert.assertNotNull(element);
        js.executeScript("arguments[0].scrollIntoView();", element);
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
        String stroke = dateFormat.format(newDate);
        System.out.println(stroke);
        element.clear();
        element.sendKeys(stroke);
    }

    /**
     * Проверка чекбокса включен он или нет
     * @param path путь до элемента
     */
    public void checkboxCheck(String path, Boolean bool) {
        if (bool) {
            if (driver.findElement(By.xpath(path)).isSelected()) {
                System.out.println("В чекбоксе уже поставлена галочка");
            } else {
                driver.findElement(By.xpath(path)).click();
                System.out.println("В чекбокс поставили галку");
            }
        } else {
            if (driver.findElement(By.xpath(path)).isSelected()) {
                driver.findElement(By.xpath(path)).click();
                System.out.println("Убрали галку");
            } else {
                System.out.println("Галочки нет, все норм");
            }
        }
    }
}