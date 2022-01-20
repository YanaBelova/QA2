package seleniumWebdriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase4 extends BaseTest{
    private final FirstAuthorizationForm firstAuthorizationForm = new FirstAuthorizationForm();

    @Test
    public void testTimer() {
        goToTheFirstAutorizationPage();
        firstAuthorizationForm.getTimerForm();
        firstAuthorizationForm.state().waitForDisplayed();
        Assert.assertEquals(JsonUtils.getValueFromTestDataJson("/expectedResultForTimerTest"), firstAuthorizationForm.getTimerForm().getTimerTime());
    }
}
