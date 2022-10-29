package pageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexPage {
    private WebDriver driver;

    By yandexFindField = By.xpath(".//form[contains(@class, 'dzen-search-arrow-common')]");

    public YandexPage(WebDriver driver){
        this.driver = driver;
    }

    public void checkThatYandexPageIsOpened(){
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(yandexFindField));
        Assert.assertTrue("Yandex page is not opened", driver.findElement(yandexFindField).isDisplayed());
    }
}
