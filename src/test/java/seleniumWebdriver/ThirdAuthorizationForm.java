package seleniumWebdriver;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ThirdAuthorizationForm extends Form {

    public ThirdAuthorizationForm(){
        super(By.xpath("//div[@class='slider__handle']"), "Third authorization page page");
    }
}