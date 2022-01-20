package com.vk.utils.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import com.vk.utils.JsonUtils;

public class TestDataValuesConfiguration {
    private static ISettingsFile testDataFile = JsonUtils.geIsettingsFile("testData.json");
    public static String wpt = testDataFile.getValue("/wpt").toString();
    public static String userID = testDataFile.getValue("/userID").toString();
}
