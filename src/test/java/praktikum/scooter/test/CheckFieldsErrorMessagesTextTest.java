package praktikum.scooter.test;

import org.junit.Test;
import praktikum.scooter.config.BaseTestConfig;
import praktikum.scooter.pageObject.MainPage;
import praktikum.scooter.pageObject.WhoIsTheScooterForPage;

import java.util.Arrays;
import java.util.List;

public class CheckFieldsErrorMessagesTextTest extends BaseTestConfig {

    @Test
    public void checkErrorMessages() {
        List<String> messages = Arrays.asList("Введите корректное имя",
                "Введите корректную фамилию",
                "Введите корректный адрес",
                "Выберите станцию",
                "Введите корректный номер");

        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.topOrderButtonClick();

        WhoIsTheScooterForPage whoIs = new WhoIsTheScooterForPage(driver);
        whoIs.nextButtonClick();

        whoIs.checkAndAssertErrorMessages(messages);
    }
}
