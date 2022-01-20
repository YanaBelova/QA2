package seleniumWebdriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase2 extends BaseTest{
    private final FirstAuthorizationForm firstAuthorizationForm = new FirstAuthorizationForm();
    private final String expectedResultForHelpFormTest ="help-form is-hidden";

    @Test
    public void testHelpWindow(){
        goToTheFirstAutorizationPage();
        firstAuthorizationForm.getHelpForm().skipHelpWindow();
        firstAuthorizationForm.getHelpForm().state().waitForNotDisplayed();
        Assert.assertEquals(expectedResultForHelpFormTest, firstAuthorizationForm.getHelpForm().getAttributeFromHelpForm(), "Help-form didn't hide");
    }
}
