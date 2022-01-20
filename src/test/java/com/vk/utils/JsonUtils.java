package com.vk.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

public class JsonUtils {

    public static ISettingsFile geIsettingsFile(String fileMame){
        return new JsonSettingsFile(fileMame);
    }

    public static String getValueFromJsonArray(String jsonResponse, String jsonArrayName, String key){
        String value = null;
        try {
            JSONObject objectResponse = new JSONObject(jsonResponse);
            JSONArray algorithms = objectResponse.getJSONArray(jsonArrayName);
            for (int i = 0; i < algorithms.length(); i++) {
                JSONObject j = algorithms.getJSONObject(i);
                value = j.getString(key);
            }
        } catch (JSONException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }
        return value;
    }
}