package praktikum.scooter.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import praktikum.scooter.config.BaseTestConfig;
import praktikum.scooter.pageObject.AboutRentPage;
import praktikum.scooter.pageObject.MainPage;
import praktikum.scooter.pageObject.WhoIsTheScooterForPage;

@RunWith(Parameterized.class)
public class ScooterOrderTest extends BaseTestConfig {

    private final String checkOrderButtonValue;
    private final String name;
    private final String secondName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final int daysCount;
    private final String rentalDaysCount;
    private final String colorValue;
    private final String comment;

    public ScooterOrderTest(String checkOrderButtonValue, String name, String secondName, String address, String metroStation, String phoneNumber, int daysCount, String rentalDaysCount, String colorValue, String comment) {
        this.checkOrderButtonValue = checkOrderButtonValue;
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.daysCount = daysCount;
        this.rentalDaysCount = rentalDaysCount;
        this.colorValue = colorValue;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return  new Object[][] {
                {"top", "Тест", "Тестовый", "Москва", "Бульвар Рокоссовского", "89000000000", 1, "двое суток", "gray", "Тест с кнокой вверху"},
                {"down", "Тест", "Тестовый", "Санкт-Петербург", "Сокольники", "89111111111", 2, "семеро суток", "random", "Тест с кнокой внизу"}
        };
    }

    @Test
    public void scooterOrder() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        // Выбор кнопки "Заказать" в зависимости от тестового параметра
        mainPage.orderButtonClick(checkOrderButtonValue);

        // Заполнение формы "Для кого самокат"
        WhoIsTheScooterForPage whoIs = new WhoIsTheScooterForPage(driver);
        whoIs.makeOrder(name, secondName , address, metroStation, phoneNumber);

        // Заполнение формы "Про аренду"
        AboutRentPage aboutRentPage = new AboutRentPage(driver);
        aboutRentPage.setFormFields(daysCount, rentalDaysCount, colorValue, comment);

        aboutRentPage.waitAndAcceptModalWindow();
        aboutRentPage.checkOrder();

    }
}
