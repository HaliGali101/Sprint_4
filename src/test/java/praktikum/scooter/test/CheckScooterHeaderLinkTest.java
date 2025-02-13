package praktikum.scooter.test;

import org.junit.Test;
import praktikum.scooter.config.BaseTestConfig;
import praktikum.scooter.pageObject.MainPage;

import static org.junit.Assert.assertEquals;

public class CheckScooterHeaderLinkTest extends BaseTestConfig {


    @Test
    public void checkHeaderLink() {
        String mainUrl = "https://qa-scooter.praktikum-services.ru/";

        driver.get(mainUrl);

        MainPage mainPage = new MainPage(driver);
        mainPage.topOrderButtonClick();
        mainPage.scooterHeaderLinkClick();
        assertEquals(mainUrl, driver.getCurrentUrl());
    }
}
