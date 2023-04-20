package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RentPage {

    //Локатор "Когда привезти самокат"
    private By dateField = By.xpath("//div/input[@placeholder='* Когда привезти самокат']");

    //Локатор "Срок аренды"
    private By orderArrowTimeField = By.xpath(".//span[@class='Dropdown-arrow']");

    //Локатор "Срок аренды"
    private By orderDropTimeField = By.xpath(".//div[contains(text(),'двое суток')]");

    //Локатор выбор цвета скутера
    private By shoiceColorScooter = By.xpath(".//label[contains(text(),'чёрный жемчуг')]");

    //Локатор Комментраий
    public By orderComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Локатор кнопки Заказать
    private By orderBook = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(),'Заказать')]");

    //Локатор подтверждения заказа
    private By orderConfirmButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Да')]");

    //Локатор текста подтверждения заказа
    private By orderConfirmText = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    private WebDriver driver;

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод указывающий Дату заказа
    public void setDateOrder(String dataFields) {
        driver.findElement(dateField).sendKeys(dataFields);
    }

    //Метод выбора срока аренды
    public void setOrderArrowTimeField() {
        driver.findElement(orderArrowTimeField).click();
    }

    //Метод выбора срока аренды2
    public void setOrderDropTimeField() {
        driver.findElement(orderDropTimeField).click();
    }

    //Метод выбор цвета самоката
    public void setColorScooterChek() {
        driver.findElement(shoiceColorScooter).click();
    }

    //Метод добавления коментария
    public void setOrderComments(String commentField) {
        driver.findElement(orderComment).sendKeys(commentField);
    }

    //Метод нажатия кнопки заказать
    public void clickOrderBook() {
        driver.findElement(orderBook).click();
    }

    // Добавили метод нажатия кнопки подтверждения заказа
    public void clickOrderConfirmButton() {
        driver.findElement(orderConfirmButton).click();
    }

    // Добавили метод получения текста из подтверждения заказа
    public String getOrderConfirmText() {
        String orderText = driver.findElement(orderConfirmText).getText();
        return orderText;
    }

    //Ввод данных для даставки заказа
    public void fillRentPage(String dataFields, String commentField) {
        setDateOrder(dataFields);
        setOrderArrowTimeField();
        setOrderDropTimeField();
        setColorScooterChek();
        setOrderComments(commentField);
    }
}
