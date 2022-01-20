package ru.aviasales.utils.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import ru.aviasales.utils.JsonUtils;

public class ConfigValuesConfiguration {
    private static ISettingsFile configFile = JsonUtils.geIsettingsFile("config.json");
    public static String baseURL = configFile.getValue("/baseURL").toString();
    public static String departureDate = configFile.getValue("/departureDate").toString();
    public static String arrivalDate = configFile.getValue("/arrivalDate").toString();
    public static String timeOutInSeconds = configFile.getValue("/timeOutInSeconds").toString();
}
