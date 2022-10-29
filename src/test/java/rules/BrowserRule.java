package rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BrowserRule extends ExternalResource {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\bin\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\webdriver\\bin\\geckodriver.exe");
        String browser = System.getenv("browser");

        if ("CHROME".equals(browser)) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
    }

    @Override
    protected void after() {
        driver.quit();
    }
}