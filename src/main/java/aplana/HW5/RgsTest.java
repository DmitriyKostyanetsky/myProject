package aplana.HW5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.Arrays;
import java.util.Collection;

/**
 * Сценарий теста росгосстраха
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 18.03.2019
 */
@RunWith(Parameterized.class)
public class RgsTest extends BaseTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {
                        "//button[@data-test-value='Multiple']",
                        "Шенген",
                        "Испания",
                        "//label[@class = 'btn btn-attention']",
                        "MATIUS DERZKI",
                        "11.02.1998",
                        true
                },

                {
                        "//button[@data-test-value='Multiple']",
                        "Шенген",
                        "Италия",
                        "//label[@class = 'btn btn-attention']",
                        "LEBREST BOROV",
                        "01.07.1977",
                        false
                },

                {
                        "//button[@data-test-value='Multiple']",
                        "Шенген",
                        "Дания",
                        "//label[@class = 'btn btn-attention']",
                        "BENJAMIN BOCK",
                        "13.10.2000",
                        true
                }
        });
    }

    /* Сколько поездок за рубеж */
    @Parameterized.Parameter
    public String countTrip;

    /* Куда (шенген) */
    @Parameterized.Parameter(1)
    public String countryName;

    /* Выбираем страну из выпадающего списка */
    @Parameterized.Parameter(2)
    public String countryElement;

    /* Сколько в сумме дней пробыть за рубежом */
    @Parameterized.Parameter(3)
    public String countDays;

    /* ФИО */
    @Parameterized.Parameter(4)
    public String fio;

    /* Дата рождения */
    @Parameterized.Parameter(5)
    public String birthday;

    /* Активный отдых */
    @Parameterized.Parameter(6)
    public Boolean isActive;

    @Test
    public void rgs() {
        String path;
        WebElement element;

        /**
         * Страница с заполнением формы
         **/
        // Выбираем несколько в течение года
        scrollDown(driver.findElement(By.xpath(countTrip)));
        checkByXPath(countTrip, "");

        // Заполняем поле шенгеном
        path = "//input[@class=\"form-control-multiple-autocomplete-actual-input tt-input\"]";
        Boolean isPresent = driver.findElements(By.xpath(path)).isEmpty();
        if (!isPresent) {
            checkByXPath(path, "");
            element = fillField(path, countryName);
            element.sendKeys(Keys.DOWN, Keys.ENTER);
        }

        // Выбираем страну
        path = "//select[@id='ArrivalCountryList']";
        element = checkByXPath(path, "");
        new Select(element).selectByVisibleText(countryElement);

        // Выбираем дату первой поездки
        path = "//input[@data-test-name='FirstDepartureDate']";
        scrollDown(driver.findElement(By.xpath(path)));
        element = checkByXPath(path, "");
        createDate(element);
        element.sendKeys(Keys.ENTER);

        // Не более 90 дней
        checkByXPath(countDays, "Не более");

        // Заполняем ФИО и дату рождения
        isPresent = driver.findElements(By.xpath("//div[@class=\"pull-left margin-right-ms-0 width-xs-auto width-sm-21rem margin-bottom-ms-2\"]//input[@class=\"form-control validation-control-has-success\" != @disabled][@data-test-name=\"FullName\"]")).isEmpty();
        if (isPresent) {
            path = "//div[@data-fi-input-mode=\"combined\"]//div[@class=\"form-group\"]//input[@class=\"form-control\" != @disabled]";
            element = checkByXPath(path, "");
            element.click();
            element.clear();
            element.sendKeys(fio);
        } else {
            element = driver.findElement(By.xpath("//div[@class=\"pull-left margin-right-ms-0 width-xs-auto width-sm-21rem margin-bottom-ms-2\"]//input[@class=\"form-control validation-control-has-success\" != @disabled][@data-test-name=\"FullName\"]"));
            element.clear();
            element.click();
            element.sendKeys(fio);
        }

        isPresent = driver.findElements(By.xpath("//input[@data-test-name=\"BirthDate\"][@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-success\"]")).isEmpty();
        if (isPresent) {
            path = "//input[@data-test-name=\"BirthDate\"]";
            element = checkByXPath(path, "");
            element.click();
            element.clear();
            element.sendKeys(birthday);
        } else {
            element = driver.findElement(By.xpath("//input[@data-test-name=\"BirthDate\"][@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-success\"]"));
            element.clear();
            element.click();
            element.sendKeys(birthday);
        }

        // Выбираем активный отдых
        if (!driver.findElements(By.xpath("//*[contains(text(), 'активный отдых или спорт')]/ancestor::div[@class=\"calc-vzr-toggle-risk-group\"]//div[@class=\"toggle off toggle-rgs\"]")).isEmpty()) {
            element = driver.findElement(By.xpath("//*[contains(text(), 'активный отдых или спорт')]/ancestor::div[@class=\"calc-vzr-toggle-risk-group\"]//div[@class=\"toggle off toggle-rgs\"]"));
            element.click();
            element.click();
        }

        if (isPresent) {
            path = "//div[@class=\"toggle toggle-rgs\"]";
            checkboxCheck(path, true);
        } else {
            path = "//div[@class=\"toggle toggle-rgs off\"]";
            checkboxCheck(path, false);
        }

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
        checkboxCheck(path, true);
    }
}