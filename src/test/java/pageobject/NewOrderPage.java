package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class NewOrderPage {
    WebDriver driver;
    By yaButton = By.xpath(".//a[contains(@class, 'Header_LogoYandex')]");
    By scooterButton = By.xpath(".//a[contains(@class, 'Header_LogoScooter')]");
    By firstOrderStepBlock = By.xpath(".//div[contains(@class, 'Order_Content')]");
    By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    By lastnameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    By addressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    By stationField = By.xpath(".//input[@placeholder = '* Станция метро']");
    By phoneField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    By nextButton = By.xpath(".//div[contains(@class, 'NextButton')]//button");
    By dateField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    By datePoint = By.xpath(".//div[contains(@class, 'day--keyboard-selected')]");
    By termList = By.xpath(".//div[text() = '* Срок аренды']");
    By colorBlack = By.xpath(".//input[@id='black']");
    By colorGrey = By.xpath(".//input[@id='grey']");
    By comment = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    By makeOrder = By.xpath(".//button[contains(text(), 'Заказать') and contains(@class, 'Middle')]");
    By confirm = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']//button[contains(text(), 'Да')]");
    By confirmationWindow = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]");
    By fieldsError = By.xpath(".//div[contains(@class, 'Input_ErrorMessage') and contains(@class, 'Visible')]");


    public NewOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage clickScooterLogo() {
        driver.findElement(scooterButton).click();
        return new MainPage(driver);
    }

    public YandexPage clickYandexLogo() {
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.findElement(yaButton).click();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        return new YandexPage(driver);
    }

    public NewOrderPage fillFirstPartOfFormAndClickNext(String name, String lastname, String address, String station, String phone) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(firstOrderStepBlock));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(lastnameField).sendKeys(lastname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(stationField).sendKeys(station);
        driver.findElement(By.xpath(".//div[contains(@class, 'Order_Text') and contains(text(), '" + station + "')]")).click();
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
        return this;
    }

    public int checkErrorsOnPage() {
        int count = driver.findElements(fieldsError).size();
        return count;
    }

    public NewOrderPage fillSecondPartOfFormAndClickNext(String term, String color, String commentText) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(dateField));
        driver.findElement(dateField).click();
        driver.findElement(datePoint).click();
        driver.findElement(termList).click();
        driver.findElement(By.xpath(".//div[contains(@class, 'Dropdown-menu')]/div[contains(text(), '" + term + "')]")).click();
        if ("Черный".equals(color) && !driver.findElement(colorBlack).isSelected()) {
            driver.findElement(colorBlack).click();
        } else if ("Серый".equals(color) && !driver.findElement(colorGrey).isSelected()) {
            driver.findElement(colorGrey).click();
        }
        driver.findElement(comment).sendKeys(commentText);
        driver.findElement(makeOrder).click();
        return this;
    }

    public NewOrderPage confirmOrder() {
        driver.findElement(confirm).click();
        return this;
    }

    public boolean checkThatConfirmationWindowIsShow() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(confirmationWindow));
        if (driver.findElement(confirmationWindow).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
