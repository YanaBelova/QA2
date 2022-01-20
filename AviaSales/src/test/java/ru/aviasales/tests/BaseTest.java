package ru.aviasales.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.aviasales.utils.configuration.ConfigValuesConfiguration;

public class BaseTest {

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
    public void stepLogger(int step, String message) {AqualityServices.getLogger().debug(String.format("%s%s%s%s", "Step ", step," : ", message));}
}
