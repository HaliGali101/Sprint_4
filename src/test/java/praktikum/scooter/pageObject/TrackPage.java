package praktikum.scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class TrackPage {

    private final WebDriver driver;

    private final By notFoundImg = By.xpath(".//img[@alt='Not found']");

    public TrackPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkPage(String number) {
        assertEquals("https://qa-scooter.praktikum-services.ru/track?t=" + number, driver.getCurrentUrl());
    }

    public void checkNotFoundImg() {
        driver.findElement(notFoundImg).isDisplayed();
    }


}
