package com.vk.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPageForm extends Form {
    private final String idOfMyPageButton = "l_pr";
    private final IButton myPageButton = getElementFactory().getButton(By.id(idOfMyPageButton), "My page");
    public NewsPageForm() {
        super(By.id("feed_rmenu"), "News page");
    }

    public void clickMyPageButton(){
        myPageButton.clickAndWait();
    }
}
