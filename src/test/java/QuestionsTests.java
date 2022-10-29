import org.junit.Rule;
import pageObject.MainPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import rules.BrowserRule;


@RunWith(Parameterized.class)
public class QuestionsTests {

    @Rule
    public BrowserRule browserRule = new BrowserRule();
    private int index;

    public QuestionsTests(int index){
        this.index = index;
    }

    @Parameterized.Parameters(name = "{0} question")
    public static Object[][] getSumData() {
        return new Object[][] {
                { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 }, { 6 }, { 7 },
        };
    }

    @Test()
    public void checkQuestionAccordion(){
        MainPage page = new MainPage(browserRule.getDriver());

        page.goToMainPage()
            .scrollToAccordion()
            .clickAccordion(index)
            .checkThatAccordionTextIsVisible(index);
    }
}
