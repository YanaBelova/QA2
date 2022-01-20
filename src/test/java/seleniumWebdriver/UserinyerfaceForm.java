package seleniumWebdriver;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserinyerfaceForm extends Form {
    private final IButton clickHereButton = getElementFactory().getButton(By.className("start__link"), "'Here'");

    public UserinyerfaceForm() {
        super(By.className("logo__icon"), "Welcome page element");
    }

    public void clickHereToGoToTHeNextPage(){
        clickHereButton.clickAndWait();
    }

}
