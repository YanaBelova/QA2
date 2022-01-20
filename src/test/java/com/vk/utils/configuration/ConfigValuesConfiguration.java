package com.vk.utils.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import com.vk.utils.JsonUtils;

public class ConfigValuesConfiguration {
   private static ISettingsFile configFile = JsonUtils.geIsettingsFile("config.json");
   public static String baseURL = configFile.getValue("/baseURL").toString();
   public static String urlScheme = configFile.getValue("/urlScheme").toString();
    public static String urlHost = configFile.getValue("/urlHost").toString();
    public static String urlPath = configFile.getValue("/urlPath").toString();
    public static String emailOrPhone = configFile.getValue("/emailOrPhone").toString();
    public static String password = configFile.getValue("/password").toString();
    public static String accessToken = configFile.getValue("/accessToken").toString();
    public static String version = configFile.getValue("/version").toString();
    public static String pathToFile = configFile.getValue("/pathToFile").toString();
    public static String fileName = configFile.getValue("/fileName").toString();
}
