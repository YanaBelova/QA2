package utils;

import api.task.API;
import APIModels.Post;
import APIModels.User;
import aquality.selenium.browser.AqualityServices;
import com.google.gson.Gson;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class APIUtils {
    public static Post getBodyOfPostObj(HttpResponse<Post> postResponse){
        return postResponse.getBody();
    }

    public static HttpResponse<String> getAPIAsString(String url){
        return API.getRequestOfAPI(url).asString();
    }

    public static List<User> getUsersList(HttpResponse<String> response){
        Gson gson = new Gson();
        String strJson = response.getBody();
        User[] usersArr = gson.fromJson(strJson, User[].class);
        return new ArrayList<>(Arrays.asList(usersArr));
    }

    public static HttpResponse<List<Post>> getAPIPostList(String url){
        HttpResponse<List<Post>> postResponsePosts = null;
        try {
            postResponsePosts = API.getRequestOfAPI(url).asObject(new GenericType<List<Post>>(){});
        } catch (UnirestException e) {
            AqualityServices.getLogger().warn("Exception while creating jsonResponse");
        }
        return postResponsePosts;
    }

    public static List<Post> getBodyOfPostList(HttpResponse<List<Post>> postResponse){
        return postResponse.getBody();
    }

    public static int getIdValue(HttpResponse<List<Post>> postResponse, int index){
        return getBodyOfPostList(postResponse).get(index).getId();
    }
    public static boolean notNullValue(String value){
        return !(value.length()==0);
    }

    public static boolean checkSort(HttpResponse<List<Post>> postResponse){
        List<Object> idList = new ArrayList<>();
        for(int i = 0; i<getBodyOfPostList(postResponse).size(); i++) {
            idList.add(APIUtils.getIdValue(postResponse, i));
        }
        return idList.stream().sorted().collect(Collectors.toList()).equals(idList);
    }
     public static boolean notNullObject(HttpResponse<Post> postResponse){
        return !getBodyOfPostObj(postResponse).equals(null);
     }

     public static int findElementOfListById(HttpResponse<String> userResponse, String id){
        int index = 0;
        for (int i = 0; i < getUsersList(userResponse).size(); i++){
            if (getUsersList(userResponse).get(i).getId().equals(id))
               index = i;
        }
        return index;
     }

    public static boolean isJSONValid(String test) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                AqualityServices.getLogger().warn("Exception when trying to convert text to format JSONArray");
                return false;
            }
        return true;
    }
}