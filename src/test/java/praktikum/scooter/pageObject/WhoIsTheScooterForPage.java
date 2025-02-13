package praktikum.scooter.pageObject;

import org.openqa.selenium.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WhoIsTheScooterForPage {
    private final WebDriver driver;
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By secondNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    private final By errorMessagesList = By.xpath(".//div[@class=\"Order_Form__17u6u\"]/div/div[text()]");

    public WhoIsTheScooterForPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSecondNameField(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }

    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setMetroStationField(String metroStation) {
        WebElement element = driver.findElement(metroStationField);
        element.sendKeys(metroStation, Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void setPhoneNumberField(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void nextButtonClick() {
        WebElement button = driver.findElement(nextButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    public void makeOrder(String name, String secondName, String address, String metroStation, String phoneNumber) {
        setNameField(name);
        setSecondNameField(secondName);
        setAddressField(address);
        setMetroStationField(metroStation);
        setPhoneNumberField(phoneNumber);
        nextButtonClick();
    }

    public void checkAndAssertErrorMessages(List<String> messagesList) {
        List<WebElement> messages = driver.findElements(errorMessagesList);
        int i = 0;
        for(WebElement message : messages) {
            assertTrue(message.isDisplayed());
            assertEquals("Текст сообщения не соответствует", messagesList.get(i), message.getText());
            i++;
        }
    }
}
