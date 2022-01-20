package com.vk.api;

import aquality.selenium.browser.AqualityServices;
import kong.unirest.*;
import java.util.*;

public class API<T> {

    public GetRequest getAPI(String url){
        return Unirest.get(url);
    }

    public HttpResponse<?> getAPIObject(String url, T obj){
        HttpResponse<?> getResponse = null;
        try {
            getResponse = getAPI(url).asObject(obj.getClass());
        } catch (UnirestException e) {
            AqualityServices.getLogger().warn("Exception while creating jsonResponse");
        }
        return getResponse;
    }

    public HttpResponse<?> postAPIPostObj(T obj, String url, Map<String, Object> values){
            return Unirest.post(url).fields(values).asObject(obj.getClass());
    }
}
