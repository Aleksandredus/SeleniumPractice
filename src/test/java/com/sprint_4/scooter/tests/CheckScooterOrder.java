package com.sprint_4.scooter.tests;

import page.object.CustomerDataPage;
import page.object.HomePageScooter;
import page.object.RentPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class CheckScooterOrder {

    private final String firstName;
    private final String secondName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String dataFields;
    private final String commentField;
    private WebDriver driver;

    public CheckScooterOrder(String firstName, String secondName, String address, String metroStation, String phoneNumber, String dataFields, String commentField) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.dataFields = dataFields;
        this.commentField = commentField;
    }

    @Before
    public void startDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--disable-dev-shm-usage", "--no-sandbox");
        driver = new ChromeDriver(options);
        // driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        objHomePageScooter.clickAcceptCookiesButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Parameterized.Parameters(name = "Проверка Оформления заказа. Кейс: {6}")
    public static Object[][] getCostumersData() {
        return new Object[][]{
                {"Александр", "Александрович", "Фаберже 7", "Чистые пруды", "89091234567", "31.12.2022", "Заказ на станцию Чистые пруды"},
                {"Иван", "Иванович", "Касая аллея 1", "Полежаевская", "89641234567", "31.12.2022", "Заказ на станцию Полежаевская"},
        };
    }

    @Test
    public void testOrdersStartsToClickOrderTopButton() throws InterruptedException {
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        objHomePageScooter.clickOrderTopButton();
        CustomerDataPage objCustomerDataPage = new CustomerDataPage(driver);
        objCustomerDataPage.fillCustomerData(firstName, secondName, address, metroStation, phoneNumber);
        objCustomerDataPage.setNextButton();
        RentPage objRentPage = new RentPage(driver);
        objRentPage.fillRentPage(dataFields, commentField);
        objRentPage.clickOrderBook();
        objRentPage.clickOrderConfirmButton();
        String orderText = objRentPage.getOrderConfirmText();
        MatcherAssert.assertThat(orderText, containsString("Заказ оформлен"));
    }

    @Test
    public void testOrdersStartsToClickBottomButton() throws InterruptedException {
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        objHomePageScooter.clickOrderBottomButton();
        CustomerDataPage objCustomerDataPage = new CustomerDataPage(driver);
        objCustomerDataPage.fillCustomerData(firstName, secondName, address, metroStation, phoneNumber);
        objCustomerDataPage.setNextButton();
        RentPage objRentPage = new RentPage(driver);
        objRentPage.fillRentPage(dataFields, commentField);
        objRentPage.clickOrderBook();
        objRentPage.clickOrderConfirmButton();
        String orderText = objRentPage.getOrderConfirmText();
        MatcherAssert.assertThat(orderText, containsString("Заказ оформлен"));
    }

    @After
    public void quit() {
        driver.quit();
    }
}

