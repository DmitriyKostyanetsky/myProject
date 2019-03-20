package aplana.HW7.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalcOnlinePage extends BasePage {

    /**
     * Текст "Страхование выезжающих за рубеж"
     */
    @FindBy(xpath = "//span[contains(text(), 'Страхование выезжающих за')][@class='h1']")
    public WebElement calcTitleTxt;

    /**
     * Кнопка "сколько поездок за рубеж в год"
     */
    @FindBy(xpath = "//button[@data-test-value='Multiple']")
    public WebElement quantityBtn;

    /**
     * Выбрать страну (шенген)
     */
    @FindBy(xpath = "//input[@class=\"form-control-multiple-autocomplete-actual-input tt-input\"]")
    public WebElement countryField;

    /**
     * Поле заполнения страны (уже заполненое)
     */
    @FindBy(xpath = "//input[@class=\"form-control-multiple-autocomplete-actual-input tt-input\"]")
    public WebElement countryFilledField;

    /**
     * Список стран
     */
    @FindBy(xpath = "//select[@id='ArrivalCountryList']")
    public WebElement countryListName;

    /**
     * Дата первой поездки
     */
    @FindBy(xpath = "//input[@data-test-name='FirstDepartureDate']")
    public WebElement firstTripDateField;

    /**
     * Кнопка "сколько дней за рубежом"
     */
    @FindBy(xpath = "//label[@class = 'btn btn-attention']")
    public WebElement quantityDaysBtn;

    /**
     * Поле ФИО
     */
    @FindBy(xpath = "//div[@data-fi-input-mode=\"combined\"]//div[@class=\"form-group\"]//input[@class=\"form-control\" != @disabled]")
    public WebElement fioField;

    /**
     * Поле ФИО (ошибка)
     */
    @FindBy(xpath = "//div[@class=\"pull-left margin-right-ms-0 width-xs-auto width-sm-21rem margin-bottom-ms-2\"]//input[@class=\"form-control validation-control-has-error\" != @disabled][@data-test-name=\"FullName\"]")
    public WebElement fioErrorField;

    /**
     * Поле ФИО (успех)
     */
    @FindBy(xpath = "//div[@class=\"pull-left margin-right-ms-0 width-xs-auto width-sm-21rem margin-bottom-ms-2\"]//input[@class=\"form-control validation-control-has-success\" != @disabled][@data-test-name=\"FullName\"]")
    public WebElement fioSuccessField;

    /**
     * Поле дата
     */
    @FindBy(xpath = "//input[@data-test-name=\"BirthDate\"]")
    public WebElement dateField;

    /**
     * Поле дата (ошибка)
     */
    @FindBy(xpath = "//input[@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-error\"]")
    public WebElement dateErrorField;

    /**
     * Поле дата (успех)
     */
    @FindBy(xpath = "//input[@data-test-name=\"BirthDate\"][@class=\"form-control width-xs-9rem width-sm-9rem validation-control-has-success\"]")
    public WebElement dateSuccessField;

    /**
     * Чекбокс "активный отдых" до нажатия
     */
    @FindBy(xpath = "//*[contains(text(), 'активный отдых или спорт')]/ancestor::div[@class=\"calc-vzr-toggle-risk-group\"]//div[@class=\"toggle off toggle-rgs\"]")
    public WebElement activeCheckBox;

    /**
     * Чекбокс "активный отдых" выключен
     */
    @FindBy(xpath = "//div[@class=\"toggle toggle-rgs off\"]")
    public WebElement activeOffCheckBox;

    /**
     * Чекбокс "активный отдых" включен
     */
    @FindBy(xpath = "//div[@class=\"toggle toggle-rgs\"]")
    public WebElement activeOnCheckBox;

    /**
     * Чекбокс "условия соглашения"
     */
    @FindBy(xpath = "//input[@data-test-name=\"IsProcessingPersonalDataToCalculate\"]")
    public WebElement conditionCheckBox;

    /**
     * Кнопка рассчитать
     */
    @FindBy(xpath = "//button[contains(text(), 'Рассчитать')][@class=\"btn btn-primary btn-sm text-uppercase text-semibold\"]")
    public WebElement calculateBtn;

    /**
     * Текст "Комфорт"
     */
    @FindBy(xpath = "//div[@class=\"program-name\"][contains(text(), \"Комфорт\")]")
    public WebElement comfortText;

    /**
     * Текст тайтл
     */
    @FindBy(xpath = "//span[@class='h1'][contains(text(), 'Страхование')]")
    public WebElement insureTitleText;

    /**
     * Создает текущую дату и прибавляет к ней 2 недели
     * @param element путь до элемента
     */
    public String createDate(WebElement element) {
        Long date = (new Date().getTime()) + (14 * 24 * 3600 * 1000);
        Date newDate = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM y");
        System.out.println("Дата плюс 2 недели : " + dateFormat.format(newDate));
        String stroke = dateFormat.format(newDate);
        System.out.println(stroke);
        element.sendKeys(Keys.SPACE);
        element.sendKeys(" ");
        for (int i = 0; i < stroke.length(); i++){
            char c = stroke.charAt(i);
            String s = String.valueOf(c);
            element.sendKeys(s);
            System.out.println("input : " + s);
        }
        element.sendKeys(Keys.ENTER);
        return stroke;
    }

    /**
     * Проверка чекбокса включен он или нет
     * @param element веб-элемент
     */
    public void checkboxCheck(WebElement element, Boolean bool) {
        if (bool) {
            if (element.isSelected()) {
                System.out.println("В чекбоксе уже поставлена галочка");
            } else {
                element.click();
                System.out.println("В чекбокс поставили галку");
            }
        } else {
            if (element.isSelected()) {
                System.out.println("Галочки нет, все норм");
            } else {
                element.click();
                System.out.println("Убрали галку");
            }
        }
    }

    /**
     * Проверяет правильность ввода данных. Вводит до тех пор пока данные не введутся коректно
     * @param insertedValue значение которое было введено
     * @param sucElement ожидаемый xpath
     * @param errElement xpath с ошибочными данными
     */
    public void checkInsertedValues(String insertedValue, WebElement sucElement, WebElement errElement) {
        while (sucElement == null) {
            System.out.println("НЕ коректно введены данные");
            errElement.click();
            errElement.clear();
            errElement.sendKeys(insertedValue);
            errElement.sendKeys(Keys.ENTER);
        }
        System.out.println("Коректно введены данные");
    }
}