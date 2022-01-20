package com.vk.utils;

import aquality.selenium.browser.AqualityServices;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    public static Map<String, Object> getMap(String[] keys, Object[] values){
        Map<String, Object> valuesMap = null;
        if(keys.length == values.length){
           valuesMap = new HashMap<String, Object>();
            for (int i=0; i< keys.length;i++){
                valuesMap.put(keys[i], values[i]);
            }
        }
        else{
            AqualityServices.getLogger().error("Array sizes Keys and Values don't match");
        }
        return valuesMap;
    }
}
