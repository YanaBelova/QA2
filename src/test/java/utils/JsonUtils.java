package utils;

import APIModels.User;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonUtils {

    public static String getValueFromTestDataJson(String value){
        ISettingsFile testData = new JsonSettingsFile("testData.json");
        return testData.getValue(value).toString();
    }

    public static String getValueFromConfigJson(String value){
        ISettingsFile conf = new JsonSettingsFile("config.json");
        return conf.getValue(value).toString();
    }

    public static String getValueFromDatabasePasswordJson(String value){
        ISettingsFile conf = new JsonSettingsFile("databasePassword.json");
        return conf.getValue(value).toString();
    }

    public static String getValueFromDatabaseConfigJson(String value){
        ISettingsFile conf = new JsonSettingsFile("databaseConfig.json");
        return conf.getValue(value).toString();
    }

    public static String getValueFromSettingsJson(String value){
        ISettingsFile conf = new JsonSettingsFile("settings.json");
        return conf.getValue(value).toString();
    }

    public static User convertJsonFileToObjectUser(File file){
        Gson gson = new Gson();
        User user = null;
        try (Reader reader = new FileReader(file)) {
            user = gson.fromJson(reader, User.class);
        } catch (IOException e) {
            AqualityServices.getLogger().warn("The file may not exist");
        }
        return user;
    }

}