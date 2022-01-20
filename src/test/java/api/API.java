package api.task;

import aquality.selenium.browser.AqualityServices;
import kong.unirest.*;
import java.util.*;

public class API {

    public static Config baseURL(String baseURL){
        return Unirest.config().defaultBaseUrl(baseURL);
    }

    public static GetRequest getAPI(String url){
        return Unirest.get(url);
    }

    public static HttpResponse<?> getAPIObject(String url, Object cl){
        HttpResponse<?> postResponse = null;
        try {
            postResponse = getAPI(url).asObject(cl.getClass());
        } catch (UnirestException e) {
            AqualityServices.getLogger().warn("Exception while creating jsonResponse");
        }
        return postResponse;
    }

    public static HttpResponse<?> postAPIPostObj(Object obj, String url, Map<String, String> values){
        List<String> keyList = new ArrayList<>(values.keySet());
        System.out.println(keyList.get(0));
        return Unirest.post(url)
                .field(keyList.get(0), values.get(keyList.get(0)))
                .field(keyList.get(1), values.get(keyList.get(1)))
                .field(keyList.get(2), values.get(keyList.get(2)))
                .asObject(obj.getClass());
    }

    public static int getStatusCode(HttpResponse<?> postResponse){
        return postResponse.getStatus();
    }
}