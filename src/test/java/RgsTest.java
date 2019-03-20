import aplana.HW7.Init;
import aplana.HW7.pages.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.Collection;

/**
 * Сценарий теста росгосстраха
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 20.03.2019
 */
@RunWith(Parameterized.class)
public class RgsTest extends ClassTest {

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
        System.out.println("----------------------Начало теста----------------------");

        CalcOnlinePage calcPage = new CalcOnlinePage();

        // Выбираем несколько в течение года
        calcPage.scrollDown(calcPage.quantityBtn);
        calcPage.clickOnElement(calcPage.quantityBtn);

        // Заполняем поле шенгеном
        if (!Init.getDriver().findElements(By.xpath("//input[@class=\"form-control-multiple-autocomplete-actual-input tt-input\"]")).isEmpty()) {
            calcPage.clickOnElement(calcPage.countryField);
            calcPage.inputInField(calcPage.countryField, countryName);
            calcPage.countryField.sendKeys(Keys.DOWN);
            calcPage.countryField.sendKeys(Keys.ENTER);
        }

        // Выбираем страну
        calcPage.clickOnElement(calcPage.countryListName);
        new Select(calcPage.countryListName).selectByVisibleText(countryElement);

        // Выбираем дату первой поездки
        calcPage.scrollDown(calcPage.firstTripDateField);
        calcPage.clickOnElement(calcPage.firstTripDateField);
        calcPage.createDate(calcPage.firstTripDateField);

        // Не более 30 или 90 дней
        calcPage.clickOnElement(calcPage.quantityDaysBtn);

        // Заполняем ФИО и дату рождения
        boolean isPresent = Init.getDriver().findElements(By.xpath("//div[@class=\"pull-left margin-right-ms-0 width-xs-auto width-sm-21rem margin-bottom-ms-2\"]//input[@class=\"form-control validation-control-has-success\" != @disabled][@data-test-name=\"FullName\"]")).isEmpty();
        if (isPresent) {
            calcPage.clickOnElement(calcPage.fioField);
            calcPage.inputInField(calcPage.fioField, fio);
        } else {
            calcPage.clickOnElement(calcPage.fioSuccessField);
            calcPage.fioSuccessField.clear();
            calcPage.inputInField(calcPage.fioErrorField, fio);
        }

        isPresent = Init.getDriver().findElements(By.xpath("//input[@data-test-name=\"BirthDate\"][@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-success\"]")).isEmpty();
        if (isPresent) {
            calcPage.clickOnElement(calcPage.dateField);
            calcPage.inputInField(calcPage.dateField, birthday);
        } else {
            calcPage.clickOnElement(calcPage.dateSuccessField);
            calcPage.dateSuccessField.clear();
            calcPage.inputInField(calcPage.dateErrorField, birthday);
        }

        // Выбираем активный или не активный отдых
        if (!Init.getDriver().findElements(By.xpath("//*[contains(text(), 'активный отдых или спорт')]/ancestor::div[@class=\"calc-vzr-toggle-risk-group\"]//div[@class=\"toggle off toggle-rgs\"]")).isEmpty()) {
            calcPage.clickOnElement(calcPage.activeCheckBox);
            calcPage.clickOnElement(calcPage.activeOnCheckBox);
        }
        if (isActive) {
            calcPage.checkboxCheck(calcPage.activeOffCheckBox, isActive);
        } else {
            calcPage.checkboxCheck(calcPage.activeOnCheckBox, isActive);
        }

        // Проверяем правильность ввода ФИО и даты рождения
        calcPage.checkInsertedValues(fio, calcPage.fioSuccessField, calcPage.fioErrorField);
        calcPage.checkInsertedValues(birthday, calcPage.dateSuccessField, calcPage.dateErrorField);

        // Соглашаемся с условиями
        calcPage.scrollDown(calcPage.conditionCheckBox);
        calcPage.checkboxCheck(calcPage.conditionCheckBox, true);
        System.out.println("----------------------Конец теста----------------------");
    }
}