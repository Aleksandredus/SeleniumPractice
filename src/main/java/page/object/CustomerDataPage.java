package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CustomerDataPage {

    // Поле "Имя"
    private By firstNameField = By.xpath(".//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    private By secondNameField = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле "Куда привезти"
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");

    // Поле "Телефон"
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка "Далее"
    private By nextButton = By.xpath("//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private WebDriver driver;

    public CustomerDataPage(WebDriver driver) {
        this.driver = driver;
    }

    //  Заполняем поле Имя
    public void setFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    //  Заполняем поле Имя Фамилия
    public void setSecondNameField(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }

    //  Заполняем поле Адрес
    public void setStreetField(String streetName) {
        driver.findElement(addressField).sendKeys(streetName);
    }

    //  Выбираем станцию Метро
    public void setMetroStationField(String metroStantion) {
        driver.findElement(metroStationField).sendKeys(metroStantion, Keys.ARROW_DOWN, Keys.ENTER);
    }

    //   Заполняем поле телефон
    public void setPhoneNumberField(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    //  Кликаем по кнопке Далее
    public void setNextButton() {
        driver.findElement(nextButton).click();
    }

    // Шаг Заполнение страницы ввода данных клиента
    public void fillCustomerData(String firstName, String secondName, String address, String metroStation, String phoneNumber) {
        setFirstNameField(firstName);
        setSecondNameField(secondName);
        setStreetField(address);
        setMetroStationField(metroStation);
        setPhoneNumberField(phoneNumber);
    }
}
