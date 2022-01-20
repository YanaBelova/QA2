package ru.aviasales.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonUtils {

    public static ISettingsFile geIsettingsFile(String fileMame){
        return new JsonSettingsFile(fileMame);
    }

}
