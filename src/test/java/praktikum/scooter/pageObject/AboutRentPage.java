package praktikum.scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;

public class AboutRentPage {

    private final WebDriver driver;

    private final By orderButton = By.xpath(".//div[@class=\"Order_Buttons__1xGrp\"]/button[text()='Заказать']");
    private final By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalDaysCountField = By.xpath(".//div[text()='* Срок аренды']");
    private final By checkboxBlack = By.xpath(".//label[text()='чёрный жемчуг']");
    private final By checkboxGray = By.xpath(".//label[text()='серая безысходность']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By modalWindow = By.xpath("");
    private final By yesButton = By.xpath(".//button[text()='Да']");
    private final By checkOrder = By.xpath(".//div[text()='Заказ оформлен']");


    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Устанавливаем дату доставки = сегодня + переданное количество дней
    public void setDeliveryDateField(int daysCount) {
        LocalDate today = LocalDate.now();
        driver.findElement(deliveryDateField).sendKeys(today.plusDays(daysCount).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), Keys.ENTER);
    }

    // Устанавливаем срок аренды, в зависимости от переданного параметра
    public void setRentalDaysCount(String rentalDaysCount) {
        driver.findElement(rentalDaysCountField).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalDaysCount + "']")).click();
    }

    // Устанавливаем чек боксы цвета самоката в зависимости от переданного параметра
    public void setScooterColorField(String colorValue) {
        if(colorValue.equals("black")) {
            driver.findElement(checkboxBlack).click();
        } else if (colorValue.equals("gray")) {
            driver.findElement(checkboxGray).click();
        } else if (colorValue.equals("random")) {
            driver.findElement(checkboxBlack).click();
            driver.findElement(checkboxGray).click();
        }
    }

    // Записать в поле комментария, переданное значение
    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void orderButtonClick() {
        driver.findElement(orderButton).click();
    }

    // Подтвердить оформление заказа
    // Решил не создавать отдельный класс для модального окна, так как не вижу в этом смысла
    public void waitAndAcceptModalWindow() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));
        driver.findElement(yesButton).click();
    }


    // Решил не создавать отдельный класс для модального окна, так как не вижу в этом смысла
    public void checkOrder() {
        assertTrue(driver.findElement(checkOrder).isDisplayed());
    }

    public void setFormFields(int daysCount, String rentalDaysCount, String colorValue, String comment) {
        setDeliveryDateField(daysCount);
        setRentalDaysCount(rentalDaysCount);
        setScooterColorField(colorValue);
        setCommentField(comment);
        orderButtonClick();
    }
}
