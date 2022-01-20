package com.vk.api;

import com.vk.utils.URLBuilder;
import kong.unirest.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VK_API extends API<Object>{
    private final String type = "post";
    private List<NameValuePair> listOfValues = new ArrayList<>();

    public HttpResponse<?> wallPostAPI(Object object, String ownerID, String message){
        listOfValues.add(new BasicNameValuePair(APIRequestFields.OWNER_ID.getField(), ownerID));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.MESSAGE.getField(), message));
        return getAPIObject(URLBuilder.urlBuilder(APICommands.WALL_POST.getCommand(), listOfValues).toString(), object);
    }
    public HttpResponse<?> wallEditAPI(Object object, String ownerID, String postID, String message, String attachment){
        listOfValues.add(new BasicNameValuePair(APIRequestFields.OWNER_ID.getField(), ownerID));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.POST_ID.getField(), postID));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.MESSAGE.getField(), message));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.ATTACHMENTS.getField(), attachment));
        return getAPIObject(URLBuilder.urlBuilder(APICommands.WALL_EDIT.getCommand(), listOfValues).toString(), object);
    }
    public HttpResponse<?> photosGetWallUploadServerAPI(Object object){
        return getAPIObject(URLBuilder.urlBuilder(APICommands.PHOTOS_GET_WALL_UPLOAD_SERVER.getCommand(), listOfValues).toString(), object);
    }
    public HttpResponse<?> wallCreateCommentAPI(Object object, String ownerID, String postID, String message){
        listOfValues.add(new BasicNameValuePair(APIRequestFields.OWNER_ID.getField(), ownerID));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.POST_ID.getField(), postID));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.MESSAGE.getField(), message));
        return getAPIObject(URLBuilder.urlBuilder(APICommands.WALL_CREATE_COMMENT.getCommand(), listOfValues).toString(), object);
    }
    public HttpResponse<?> likesIsLikedAPI(Object object, String itemID){
        listOfValues.add(new BasicNameValuePair(APIRequestFields.TYPE.getField(), type));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.ITEM_ID.getField(), itemID));
        return getAPIObject(URLBuilder.urlBuilder(APICommands.LIKES_IS_LIKED.getCommand(), listOfValues).toString(), object);
    }
    public HttpResponse<?> wallDeleteAPI(String ownerID, String postID){
        listOfValues.add(new BasicNameValuePair(APIRequestFields.OWNER_ID.getField(), ownerID));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.POST_ID.getField(), postID));
        return getAPI(URLBuilder.urlBuilder( APICommands.WALL_DELETE.getCommand(), listOfValues).toString()).asString();
    }
    public HttpResponse<?> fileTransfer(Object object, String uploadUrl, Map<String, Object> values){
        return postAPIPostObj(object, uploadUrl, values);
    }
    public HttpResponse<?> photosSaveWallPhotoAPI(String server, String photo, String hash){
        listOfValues.add(new BasicNameValuePair(APIRequestFields.PHOTO.getField(), photo));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.SERVER.getField(), server));
        listOfValues.add(new BasicNameValuePair(APIRequestFields.HASH.getField(), hash));
        return getAPI(URLBuilder.urlBuilder(APICommands.PHOTOS_SAVE_WALL_PHOTO.getCommand(), listOfValues).toString()).asJson();
    }
}

