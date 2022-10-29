import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pageObject.MainPage;
import pageObject.NewOrderPage;
import rules.BrowserRule;

public class OrderTests {
    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void newOrderWithoutNorRequiredFields(){
        MainPage page = new MainPage(browserRule.getDriver());

        page.goToMainPage()
            .clickNewOrderButton(1)
            .fillFirstPartOfFormAndClickNext("Антон", "Винокуров", "Маршала Малиновского 3", "Лубянка", "+79654564565")
            .fillSecondPartOfFormAndClickNext("сутки", "", "")
            .confirmOrder();

        Assert.assertTrue("Confirmation window not found", new NewOrderPage(browserRule.getDriver()).checkThatConfirmationWindowIsShow());
    }

    @Test
    public void newOrderWithNorRequiredFields(){
        MainPage page = new MainPage(browserRule.getDriver());

        page.goToMainPage()
                .clickNewOrderButton(2)
                .fillFirstPartOfFormAndClickNext("Антон", "Винокуров", "Маршала Малиновского 3", "Лубянка", "+79654564565")
                .fillSecondPartOfFormAndClickNext("сутки", "Черный", "Привет дружище")
                .confirmOrder();

        Assert.assertTrue("Confirmation window not found", new NewOrderPage(browserRule.getDriver()).checkThatConfirmationWindowIsShow());
    }

    @Test
    public void findNotCorrectOrderID(){
        MainPage page = new MainPage(browserRule.getDriver());

        page.goToMainPage()
            .findOrder("sddsadsd")
            .checkThatOrderNotFound();
    }

    @Test
    public void checkThatErrorMessagesOfOrderFieldsAreVisible() {
        MainPage page = new MainPage(browserRule.getDriver());

        int countErr = page.goToMainPage()
                .clickNewOrderButton(1)
                .fillFirstPartOfFormAndClickNext("121name", "121name", "--&", "Лубянка", "name123")
                .checkErrorsOnPage();

        Assert.assertEquals(4, countErr);
    }
}
