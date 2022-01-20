package seleniumWebdriver;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    private final UserinyerfaceForm userinyerfaceForm = new UserinyerfaceForm();
    private final FirstAuthorizationForm firstauthorizationForm = new FirstAuthorizationForm();

    @BeforeMethod
    public void beforeMethod() {
        AqualityServices.getBrowser();
        AqualityServices.getBrowser().getDriver();
        getBrowser().goTo(JsonUtils.getValueFromConfigJson("/baseURL"));
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
    }

    public void goToTheFirstAutorizationPage(){
        Assert.assertTrue(userinyerfaceForm.state().isDisplayed(), "Userinyerface page didn't open");
        userinyerfaceForm.clickHereToGoToTHeNextPage();
        firstauthorizationForm.state().waitForDisplayed();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    public Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}