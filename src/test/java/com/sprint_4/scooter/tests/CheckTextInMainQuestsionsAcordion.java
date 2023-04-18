package com.sprint_4.scooter.tests;

import com.sprint_4.scooter.page.object.HomePageScooter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Parameterized.class)
public class CheckTextInMainQuestsionsAcordion {
    private final String result;
    private final int accordionLocator;
    WebDriver driver;

    public CheckTextInMainQuestsionsAcordion(String result, int accordionLocator) {
        this.result = result;
        this.accordionLocator = accordionLocator;
    }

    @Parameterized.Parameters//
    public static Object[][] data() {
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7},
        };
    }

    @Before
    public void startDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--disable-dev-shm-usage", "--no-sandbox");
        driver = new ChromeDriver(options);
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        objHomePageScooter.clickAcceptCookiesButton();
    }

    @Test
    public void CheckTextInAccordeon() throws InterruptedException {
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        objHomePageScooter.scrollToMainQuestionLabel();

        driver.findElement(By.xpath(String.format("%s%d']", objHomePageScooter.getAccordionLocator(), accordionLocator))).click();
        Thread.sleep(500);
        String answerText = objHomePageScooter.getAnswerAccordionText();
        Assert.assertEquals("Обнаружено несоответствие текста, полученного из акордеона с ожидаемым", result, answerText);
    }

    @After
    public void quit() {
        driver.quit();
    }
}