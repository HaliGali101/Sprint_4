package praktikum.scooter.test;

import org.junit.Test;
import praktikum.scooter.config.BaseTestConfig;
import praktikum.scooter.pageObject.MainPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckYandexHeaderLinkTest extends BaseTestConfig {

    @Test
    public void checkHeaderLink() throws InterruptedException {
        String yaRu = "https://dzen.ru/?yredirect=true";

        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.yandexHeaderLinkClick();

        // Переключаемся между вкладками
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        // Так как точного условия нет, принимаем текущее значение за ожидаемое
        // Так как актуальная страница прогружается очень долго, вместо одидания её полной загрузки был добавлен таймаут
        Thread.sleep(1000);
        assertEquals(yaRu, driver.getCurrentUrl());
    }
}