package com.vk.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AutorizationForm extends Form {
    private final String idOftxbEmailOrPhone = "index_email";
    private final String idOftxbPassword = "index_pass";
    private final String idOfSignInButton = "index_login_button";
    private final ITextBox txbEmailOrPhone = getElementFactory().getTextBox(By.id(idOftxbEmailOrPhone), "Email ot phone");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.id(idOftxbPassword), "Password");
    private final IButton signInButton = getElementFactory().getButton(By.id(idOfSignInButton), "Sign in");

    public AutorizationForm() {
        super(By.className("JoinForm__in"), "JoinForm");
    }
    public void inputEmailOrPhone(String phone){
        txbEmailOrPhone.clearAndTypeSecret(phone);
    }

    public void inputPassword(String password){
        txbPassword.clearAndTypeSecret(password);
    }

    public void clickSignInButton(){
        signInButton.clickAndWait();
    }

}
