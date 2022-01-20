package seleniumWebdriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 extends BaseTest{
    private final FirstAuthorizationForm firstauthorizationForm = new FirstAuthorizationForm();
    private final SecondAuthorizationForm secondAuthorizationForm = new SecondAuthorizationForm();
    private final ThirdAuthorizationForm thirdAuthorizationForm = new ThirdAuthorizationForm();
    private final PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
            .useDigits(true)
            .useLower(true)
            .useUpper(true)
            .usePunctuation(true)
            .build();
    private String partOfEmail ;
    private final int lengthOfGeneratedEmail = 10;
    private final int lengthOfTheInitialRandomPassword = 10;
    private final int lengthOfGeneratedDomain = 4;
    private final int emailIndexNumberFromWhichWeWillStartTakingLettersForThePassword = 7;
    private final int numberOfCheckboxesWeShouldSelect = 3;

    @Test
    public void testAutorization(){
           goToTheFirstAutorizationPage();
           firstauthorizationForm.state().waitForDisplayed();
           Assert.assertTrue(firstauthorizationForm.state().isDisplayed(),"First autorization page didn't open");
           firstauthorizationForm.acceptingTheTermsOfUse();
           firstauthorizationForm.inputEmail(passwordGenerator.generate(lengthOfGeneratedEmail));
           partOfEmail = passwordGenerator.generate(lengthOfGeneratedEmail).substring(emailIndexNumberFromWhichWeWillStartTakingLettersForThePassword);
           firstauthorizationForm.inputPassword(String.format("%s%s",passwordGenerator.generate(lengthOfTheInitialRandomPassword),partOfEmail));
           firstauthorizationForm.inputDomain(passwordGenerator.generate(lengthOfGeneratedDomain));
           firstauthorizationForm.selectDropdownOpener();
           firstauthorizationForm.state().waitForDisplayed();
           firstauthorizationForm.clickNextButton();
           secondAuthorizationForm.state().waitForDisplayed();
           Assert.assertTrue(secondAuthorizationForm.state().isDisplayed(), "Second autorization page didn't open");
           secondAuthorizationForm.clickDownloadImageButton();
           FileUploadUtils.downloadFile(String.format("%s%s%s",System.getProperty("user.dir"), JsonUtils.getValueFromConfigJson("/wayToFile"), JsonUtils.getValueFromTestDataJson("/fileName")));
           secondAuthorizationForm.selectRandomInterests(numberOfCheckboxesWeShouldSelect);
           secondAuthorizationForm.doubleClickNextButton();
           thirdAuthorizationForm.state().waitForDisplayed();
           Assert.assertTrue(thirdAuthorizationForm.state().isDisplayed(), "Third autorization page didn't open");
    }
}
