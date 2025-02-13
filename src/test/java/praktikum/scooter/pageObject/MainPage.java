package praktikum.scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainPage {

    private final WebDriver driver;

    private final By importantQuestions = By.xpath(".//div[@class='Home_FourPart__1uthg']");
    private final By questions = By.xpath(".//div[@class='accordion__item']");
    private final By topOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    private final By downOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    private final By scooterHeaderLink = By.xpath(".//img[@alt='Scooter']");
    private final By yandexHeaderLink = By.xpath(".//img[@alt='Yandex']");

    private final By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");
    private final By orderNumberField = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By goButton = By.xpath(".//button[text()='Go!']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Скрол до блока "Вопросы о важном"
    public void goToImportantQuestions() {
        WebElement element = driver.findElement(importantQuestions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void checkQuestion(List<String> actualAnswerText) {
        List<WebElement> questionsList = driver.findElements(questions);

        int i = 0;
        //проходим по каждому вопросу и раскрываем его
        for (WebElement question : questionsList) {
            WebElement testableQuestion = question.findElement(By.className("accordion__button"));
            testableQuestion.click();

            //У тестируемого объекта находим ответ, ожидаем его отображение, осуществляем проверку текста на соответствие
            WebElement testableAnswer = question.findElement(By.className("accordion__panel"));
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(testableAnswer));
            assertEquals("Текст ответа не соответствует!", actualAnswerText.get(i), testableAnswer.getText());
            i++;
        }
    }

    public void orderButtonClick(String checkOrderButtonValue) {
        if (checkOrderButtonValue.equals("top")) {
            driver.findElement(topOrderButton).click();
        } else if (checkOrderButtonValue.equals("down")) {
            WebElement element = driver.findElement(downOrderButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
        }
    }

    public void scooterHeaderLinkClick() {
        driver.findElement(scooterHeaderLink).click();
    }

    public void yandexHeaderLinkClick() {
        driver.findElement(yandexHeaderLink).click();
    }

    public void topOrderButtonClick() {
        driver.findElement(topOrderButton).click();
    }

    public void orderStatusButtonClick() {
        driver.findElement(orderStatusButton).click();
    }

    public void checkOrderStatus(String number) {
        WebElement field = driver.findElement(orderNumberField);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(field));
        field.sendKeys(number);
        driver.findElement(goButton).click();
    }
}
