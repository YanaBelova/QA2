package seleniumWebdriver;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import static aquality.selenium.browser.AqualityServices.getBrowser;

public class FirstAuthorizationForm extends Form {
        private final ITextBox txbEmail = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "Email");
        private final ITextBox txbDomain = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "Domain");
        private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "Password");
        private final IComboBox dropdownOpener = getElementFactory().getComboBox(By.className("dropdown__opener"), "Dropdown opener");
        private final ICheckBox checkBoxOfAcceptingTheTermsOfUse = getElementFactory().getCheckBox(By.xpath("//span[contains(@class,'icon-check')]"),"Accepting the terms of use");
        private final IButton nextButton = getElementFactory().getButton(By.xpath("//*[contains(text(),'Next')]"), "'Next'");
        private HelpForm helpForm = new HelpForm();
        private CookiesForm cookiesForm = new CookiesForm();
        private TimerForm timerForm = new TimerForm();

        public FirstAuthorizationForm() {
            super(By.xpath("//input[contains(@placeholder,'email')]"), "First authorization page element");
        }

        public HelpForm getHelpForm(){
            return this.helpForm;
        }

        public CookiesForm getCookiesForm(){
            return this.cookiesForm;
        }

        public TimerForm getTimerForm(){
            return this.timerForm;
        }

        public void acceptingTheTermsOfUse(){
            checkBoxOfAcceptingTheTermsOfUse.check();
        }

        public void inputEmail(String email){
            txbEmail.clearAndTypeSecret(email);
        }

        public void inputPassword(String password){
            txbPassword.clearAndTypeSecret(password);
        }

        public void inputDomain(String domain){
            txbDomain.clearAndTypeSecret(domain);
        }

        public void selectDropdownOpener(){
            Random random = new Random();
            dropdownOpener.click();
            List<IComboBox> comboBoxes = getElementFactory().findElements(By.xpath("//div[contains(@class,'dropdown__list-item')]"), ElementType.COMBOBOX, ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
            IntStream.range(0, 1).forEach(x ->comboBoxes.get(random.nextInt(comboBoxes.size())).click());
        }

        public void clickNextButton(){
            nextButton.clickAndWait();
        }

    public class HelpForm extends Form{
        private final IComboBox helpForm = getElementFactory().getComboBox(By.className("help-form"), "Help form");
        private final IButton buttonSkipHelp = getElementFactory().getButton(By.xpath("//button[contains(@class,'help-form__send')]"), "Skip help window");

        public HelpForm(){
            super(By.className("help-form__container"), "Help window");
        }

        public void skipHelpWindow(){
            buttonSkipHelp.clickAndWait();
        }

        public String getAttributeFromHelpForm(){
            return helpForm.getAttribute("class");
        }
    }

    public class CookiesForm extends Form{
        private final IButton acceptCookies = getElementFactory().getButton(By.xpath("//button[contains(@class,'transparent')]"),"Accepting Cookies");

        public CookiesForm() {
            super(By.xpath("//div[@class='cookies']"), "Cookies");
        }

        public void acceptTheUseOfCookies(){
            acceptCookies.clickAndWait();
        }
    }

    public class TimerForm extends Form{
        private final ILabel timerLabel = getElementFactory().getLabel(By.xpath("//div[contains(@class,'timer')]"), "Timer");
        Browser browserTabNavigation = getBrowser();

        public TimerForm(){
            super(By.xpath("//div[contains(@class,'timer')]"), "Timer");
        }

        public String getTimerTime(){
            return timerLabel.getText();
        }

        public void refreshPage(){
            browserTabNavigation.refresh();
        }
    }
}

