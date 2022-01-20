package seleniumWebdriver;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonUtils {

     public static String getValueFromTestDataJson(String value){
         ISettingsFile testData = new JsonSettingsFile("testData.json");
         return testData.getValue(value).toString();
     }

     public static String getValueFromConfigJson(String value){
         ISettingsFile conf = new JsonSettingsFile("config.json");
         return conf.getValue(value).toString();
     }
}
