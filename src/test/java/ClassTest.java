import aplana.HW7.Init;
import aplana.HW7.TestProperties;
import aplana.HW7.pages.CalcOnlinePage;
import aplana.HW7.pages.MainPage;
import aplana.HW7.pages.TouristsAbroadPage;
import aplana.HW7.pages.TravelInsurancePage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ClassTest {
    private static String baseUrl;
    public static Properties properties = TestProperties.getINSTANCE().getProperties();

    @BeforeClass
    public static void setUp() {
        switch (properties.getProperty("browser1")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                Init.setDriver(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                Init.setDriver(new FirefoxDriver());
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", properties.getProperty("webdriver.ie.driver"));
                Init.setDriver(new InternetExplorerDriver());
                break;
        }

        baseUrl = properties.getProperty("app.url");
        Init.getDriver().get(baseUrl);
        Init.getDriver().manage().window().maximize();
        Init.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        MainPage mainPage = new MainPage();
        TravelInsurancePage travelPage = new TravelInsurancePage();
        TouristsAbroadPage abroadPage = new TouristsAbroadPage();

        mainPage.clickOnElement(mainPage.insuranceBtn);
        mainPage.clickOnElement(mainPage.travelBtn);

        travelPage.clickOnElement(travelPage.departingBtn);

        abroadPage.scrollDown(abroadPage.calcBtn);
        abroadPage.clickOnElement(abroadPage.calcBtn);
    }

    @AfterClass
    public static void quit() {
        List<String> actual = new ArrayList<>();
        List<String> expect;
        CalcOnlinePage c = new CalcOnlinePage();

        c.clickOnElement(c.calculateBtn);
        c.waitElement(c.comfortText);

        c.scrollDown(c.insureTitleText);

        System.out.println("----------Итоговый отчет----------");
        String path = "//span[@data-bind=\"with: Trips\"]//child::span[@class='text-bold']";
        WebElement element = Init.getDriver().findElement(By.xpath(path));
        actual.add(element.getText());

        path = "//span[@data-bind=\"foreach: countries\"]//child::strong[@data-bind=\"text: Name\"]";
        element = Init.getDriver().findElement(By.xpath(path));
        actual.add(element.getText());

        path = "//div[@class=\"summary-row\"]//child::strong[@data-bind=\"text: LastName() + ' ' + FirstName()\"]";
        element = Init.getDriver().findElement(By.xpath(path));
        actual.add(element.getText());

        path = "//div[@class=\"summary-row\"]//child::strong[@data-bind=\" text: BirthDay.repr('moscowRussianDate')\"]";
        element = Init.getDriver().findElement(By.xpath(path));
        actual.add(element.getText());

        path = "//div[@style=\"visibility: visible; opacity: 1; display: block; transform: translateX(0px);\"]//child::small[@data-bind=\"text: ko.unwrap('undefined' === typeof info ? '' : info)\"]";
        element = Init.getDriver().findElement(By.xpath(path));
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
        Init.getDriver().quit();
    }
}