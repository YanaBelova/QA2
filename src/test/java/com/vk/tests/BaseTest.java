package com.vk.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import com.vk.utils.configuration.ConfigValuesConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    public void beforeMethod() {
        AqualityServices.getBrowser();
        AqualityServices.getBrowser().getDriver();
        getBrowser().goTo(ConfigValuesConfiguration.baseURL);
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
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
    public void stepLogger(int step, String message) {AqualityServices.getLogger().debug(String.format("%s%s%s%s", "step ", step," : ", message));}
}