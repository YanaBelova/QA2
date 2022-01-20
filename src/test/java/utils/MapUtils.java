package utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    private static String firstKey = "title";
    private static String secondKey = "body";
    private static String thirdKey = "userId";
    public static Map<String, String> getMap(String value1, String value2, String value3){
        Map<String, String> values = new HashMap<String, String>();
        values.put(firstKey, value1);
        values.put(secondKey, value2);
        values.put(thirdKey, value3);
        return values;
    }
}
