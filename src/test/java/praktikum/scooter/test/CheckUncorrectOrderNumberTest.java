package praktikum.scooter.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.scooter.config.BaseTestConfig;
import praktikum.scooter.pageObject.MainPage;
import praktikum.scooter.pageObject.TrackPage;

public class CheckUncorrectOrderNumberTest extends BaseTestConfig {

    @Test
    public void checkUncorrectOrderNumber() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        String testNumber = "5534234";
        MainPage mainPage = new MainPage(driver);
        mainPage.orderStatusButtonClick();

        mainPage.checkOrderStatus(testNumber);

        TrackPage trackPage = new TrackPage(driver);
        trackPage.checkPage(testNumber);
        trackPage.checkNotFoundImg();
    }
}
