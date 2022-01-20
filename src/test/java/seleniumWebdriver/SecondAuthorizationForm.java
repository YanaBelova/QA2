package seleniumWebdriver;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SecondAuthorizationForm extends Form {
    private final ICheckBox unselectAllCheckbox = getElementFactory().getCheckBox(By.xpath("//label[contains(@for,'unselectall')]"), "'Unselect all'");
    private final IButton buttonDownloadImage = getElementFactory().getButton(By.xpath("//a[contains(text(),'upload')]"), "'upload'");
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//*[contains(text(),'Next')]"), "'Next'");

    public SecondAuthorizationForm() {
        super(By.xpath("//div[contains(@class,'avatar-box')]"), "Second authorization page element");
    }

    public void selectRandomInterests(int countOfInterests){
        Random random = new Random();
        unselectAllCheckbox.check();
        List<ICheckBox> checkBoxes = getElementFactory().findElements(By.xpath("//span[contains(@class, 'checkbox small')]/label"), ElementType.CHECKBOX, ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
        checkBoxes.removeIf(checkBox -> checkBox.getAttribute("for").equals("interest_selectall") || checkBox.getAttribute("for").equals("interest_unselectall"));
        IntStream.range(0, countOfInterests).forEach(x ->checkBoxes.get(random.nextInt(checkBoxes.size())).click());
    }

    public void clickDownloadImageButton() {
        buttonDownloadImage.click();
    }

    public void doubleClickNextButton(){
        nextButton.getMouseActions().doubleClick();
    }
}
