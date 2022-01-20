package utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static Map<String, Object> getMap(String[] keys, Object[] values){
        Map<String, Object> valuesMap = new HashMap<String, Object>();
        for (int i=0; i< keys.length;i++){
            valuesMap.put(keys[i], values[i]);
        }
        return valuesMap;
    }
}
