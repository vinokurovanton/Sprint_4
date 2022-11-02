package pageobject;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    private By mainBlock = By.xpath(".//div[contains(@class, 'Home_FirstPart')]");
    private By checkOrderButton = By.xpath("//button[text() = 'Статус заказа']");
    private By orderIdField = By.xpath(".//input[@placeholder = 'Введите номер заказа']");
    private By findOrderButton = By.xpath(".//div[contains(@class,'Header_SearchInput')]/button");
    private By orderNotFound = By.xpath(".//img[@alt = 'Not found']");
    private By newOrderTopButton = By.xpath(".//div[contains(@class, 'Header_Nav')]//button[contains(@class, 'Button_Button')]");
    private By newOrderBottomButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]//button[contains(@class, 'Button_Button')]");
    private By questionsAccordion = By.xpath("//div[contains(@id,'accordion__heading')]");
    private String questionAccordion = "accordion__heading-";
    private String questionText = "accordion__panel-";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkThatMainPageIsOpened() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(mainBlock));
        Assert.assertTrue("Main page is not opened", driver.findElement(mainBlock).isDisplayed());
    }

    public MainPage goToMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
        return this;
    }

    public MainPage findOrder(String id) {
        driver.findElement(checkOrderButton).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(orderIdField));
        driver.findElement(orderIdField).sendKeys(id);
        driver.findElement(findOrderButton).click();
        return this;
    }

    public void checkThatOrderNotFound() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(orderNotFound));
        Assert.assertTrue("Order not found page not displayed", driver.findElement(orderNotFound).isDisplayed());
    }

    public MainPage scrollToAccordion() {
        driver.findElement(questionsAccordion).sendKeys(Keys.CONTROL, Keys.END);
        WebElement element = driver.findElement(questionsAccordion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public MainPage clickAccordion(int index) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id(questionAccordion + index)));
        driver.findElement(By.id(questionAccordion + index)).click();
        return this;
    }

    public MainPage checkThatAccordionTextIsVisible(int index) {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.id(questionText + index)));
        Assert.assertEquals("Question test in accordion not visible", true, driver.findElement(By.id(questionText + index)).isDisplayed());
        return this;
    }

    public NewOrderPage clickNewOrderButton(int button) {
        if (button == 1) {
            driver.findElement(newOrderTopButton).click();
        } else if (button == 2) {
            driver.findElement(questionsAccordion).sendKeys(Keys.CONTROL, Keys.END);
            driver.findElement(newOrderBottomButton).click();
        } else {
            Assert.assertTrue("UNKNOWN NUMBER OF BUTTON", true);
        }
        return new NewOrderPage(driver);
    }

}
