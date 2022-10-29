import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pageObject.MainPage;
import rules.BrowserRule;

public class MainTests {
    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void checkMainPageIsOpenedAfterLogoClick(){
        MainPage page = new MainPage(browserRule.getDriver());
        page.goToMainPage()
            .clickNewOrderButton(1)
            .clickScooterLogo()
            .checkThatMainPageIsOpened();
    }

    @Test
    public void checkYandexPageIsOpenedAfterLogoClick(){
        MainPage page = new MainPage(browserRule.getDriver());
        page.goToMainPage()
            .clickNewOrderButton(1)
            .clickYandexLogo()
            .checkThatYandexPageIsOpened();
    }
}
