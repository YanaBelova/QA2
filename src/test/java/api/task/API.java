package api.task;

import aquality.selenium.browser.AqualityServices;
import kong.unirest.*;
import java.util.*;

public class API<T> {

    public static Config baseURL(String baseURL){
        return Unirest.config().defaultBaseUrl(baseURL);
    }

    public static GetRequest getRequestOfAPI(String url){
        return Unirest.get(url);
    }

    public HttpResponse<?> getAPIObject(String url, T object){
        HttpResponse<?> postResponse = null;
        try {
            postResponse = getRequestOfAPI(url).asObject(object.getClass());
        } catch (UnirestException e) {
            AqualityServices.getLogger().warn("Exception while creating jsonResponse");
        }
        return postResponse;
    }

    public HttpResponse<?> postAPIPostObj(T obj, String url, Map<String, Object> values){
        return Unirest.post(url).fields(values).asObject(obj.getClass());
    }

    public static int getStatusCode(HttpResponse<?> postResponse){
        return postResponse.getStatus();
    }
}