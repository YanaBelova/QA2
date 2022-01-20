package seleniumWebdriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase3 extends BaseTest{
    private final FirstAuthorizationForm firstAuthorizationForm = new FirstAuthorizationForm();

    @Test
    public void testCookies() {
        goToTheFirstAutorizationPage();
        firstAuthorizationForm.getCookiesForm().acceptTheUseOfCookies();
        Assert.assertFalse(firstAuthorizationForm.getCookiesForm().state().isDisplayed(), "Form with cookies didn't hide");
    }
}
