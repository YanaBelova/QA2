package ru.aviasales.utils;

import aquality.selenium.browser.AqualityServices;

import java.util.concurrent.TimeUnit;

public class WaitUtils {
        public static void implicitlyWait(long timeOfWait){
            AqualityServices.getBrowser().getDriver().manage().timeouts().implicitlyWait(timeOfWait, TimeUnit.MILLISECONDS);
        }
}
