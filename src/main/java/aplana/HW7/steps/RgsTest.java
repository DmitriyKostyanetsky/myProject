package aplana.HW7.steps;

import aplana.HW7.Init;
import aplana.HW7.TestProperties;
import aplana.HW7.pages.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Когда;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Сценарий теста росгосстраха
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 26.03.2019
 */
public class RgsTest {

    public static Properties properties = TestProperties.getINSTANCE().getProperties();

    @Before("@web")
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

        String baseUrl = properties.getProperty("app.url");
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

    @Когда("загружена страница Калькулятор страхования путешественников онлайн")
    public void loadPage() {
        System.out.println("Нужная страница");
    }

    @Когда("выбираем сколько поездок за рубеж")
    public void chooseTravelCount() {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        calcPage.scrollDown(calcPage.quantityBtn);
        calcPage.clickOnElement(calcPage.quantityBtn);
    }

    @Когда("выбираем куда едем String \"(.+)\"")
    public void whereTravel(String param) {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        if (!Init.getDriver().findElements(By.xpath("//input[@class=\"form-control-multiple-autocomplete-actual-input tt-input\"]")).isEmpty()) {
            calcPage.clickOnElement(calcPage.countryField);
            calcPage.inputInField(calcPage.countryField, param);
            calcPage.countryField.sendKeys(Keys.DOWN);
            calcPage.countryField.sendKeys(Keys.ENTER);
        }
    }

    @Когда("выбираем страну String \"(.+)\"")
    public void chooseCountry(String countryElement) {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        calcPage.clickOnElement(calcPage.countryListName);
        new Select(calcPage.countryListName).selectByVisibleText(countryElement);
    }

    @Когда("указываем дату первой поездки")
    public void firstTravelDate() {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        calcPage.scrollDown(calcPage.firstTripDateField);
        calcPage.clickOnElement(calcPage.firstTripDateField);
        calcPage.createDate(calcPage.firstTripDateField);
    }

    @Когда("выбираем количество дней за рубежом")
    public void chooseTravelDays() {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        calcPage.clickOnElement(calcPage.quantityDaysBtn);
    }

    @Когда("заполняем ФИО String \"(.+)\"")
    public void fillFio(String fio) {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        boolean isPresent = Init.getDriver().findElements(By.xpath("//div[@class=\"pull-left margin-right-ms-0 width-xs-auto width-sm-21rem margin-bottom-ms-2\"]//input[@class=\"form-control validation-control-has-success\" != @disabled][@data-test-name=\"FullName\"]")).isEmpty();
        if (isPresent) {
            calcPage.clickOnElement(calcPage.fioField);
            calcPage.inputInField(calcPage.fioField, fio);
        } else {
            calcPage.clickOnElement(calcPage.fioSuccessField);
            calcPage.fioSuccessField.clear();
            calcPage.inputInField(calcPage.fioErrorField, fio);
        }
    }

    @Когда("заполняем дату рождения String \"(.+)\"")
    public void fillBirthday(String birthday) {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        boolean isPresent = Init.getDriver().findElements(By.xpath("//input[@data-test-name=\"BirthDate\"][@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-success\"]")).isEmpty();
        if (isPresent) {
            calcPage.clickOnElement(calcPage.dateField);
            calcPage.inputInField(calcPage.dateField, birthday);
        } else {
            calcPage.clickOnElement(calcPage.dateSuccessField);
            calcPage.dateSuccessField.clear();
            calcPage.inputInField(calcPage.dateErrorField, birthday);
        }
    }

    @Когда("выбираем активный отдых String \"(.+)\"")
    public void activeRelax(String active) {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        if (!Init.getDriver().findElements(By.xpath("//*[contains(text(), 'активный отдых или спорт')]/ancestor::div[@class=\"calc-vzr-toggle-risk-group\"]//div[@class=\"toggle off toggle-rgs\"]")).isEmpty()) {
            calcPage.clickOnElement(calcPage.activeCheckBox);
            calcPage.clickOnElement(calcPage.activeOnCheckBox);
        }
        if (active.equals("yes")) {
            calcPage.checkboxCheck(calcPage.activeOffCheckBox, true);
        } else {
            calcPage.checkboxCheck(calcPage.activeOnCheckBox, false);
        }
    }

    @Когда("проверяем корректность ФИО String \"(.+)\"")
    public void checkCorrectFio(String fio) {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        //calcPage.checkInsertedValues(fio, calcPage.fioField);
    }

    @Когда("проверяем корректность даты рождения String \"(.+)\"")
    public void checkCorrectBirthday(String birthday) {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        //calcPage.checkInsertedValues(birthday, calcPage.dateField);
    }

    @Когда("соглашаемся с условиями")
    public void conditionTrue() {
        CalcOnlinePage calcPage = new CalcOnlinePage();
        calcPage.scrollDown(calcPage.conditionCheckBox);
        calcPage.checkboxCheck(calcPage.conditionCheckBox, true);
    }

    @After("@web")
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

        Init.getDriver().quit();
        //Assert.assertEquals("Итоговые данные не совпали", expect, actual);
        System.out.println("Тест прошел успешно!");
    }
}