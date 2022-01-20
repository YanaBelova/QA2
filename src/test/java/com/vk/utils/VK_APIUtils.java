package com.vk.utils;

import com.vk.api.APIRequestFields;
import com.vk.api.VK_API;
import com.vk.api.models.*;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.util.Map;

public class VK_APIUtils extends VK_API{
    private final String userID;

    public VK_APIUtils(String userID){
        this.userID = userID;
    }
    public HttpResponse<APIPostModel> createPost(String message){
       return (HttpResponse<APIPostModel>) wallPostAPI(new APIPostModel(), userID, message);
    }

    public HttpResponse<APIPostModel> editPost(String postID, String message, String photo){
        return (HttpResponse<APIPostModel>) wallEditAPI(new APIPostModel(), userID, postID, message, String.format("%s%s%s%s", APIRequestFields.PHOTO.getField(),userID,"_",photo));
    }

    public HttpResponse<APIUploadServerModel> getWallUploadServer(){
        return (HttpResponse<APIUploadServerModel>) photosGetWallUploadServerAPI(new APIUploadServerModel());
    }

    public HttpResponse<APICommentModel> createComment(String postID, String message){
        return (HttpResponse<APICommentModel>) wallCreateCommentAPI(new APICommentModel(),userID,postID, message);
    }

    public HttpResponse<APILikeModel> requestLikesIsLiked(String itemID){
        return (HttpResponse<APILikeModel>) likesIsLikedAPI(new APILikeModel(), itemID);
    }

    public void deletePost(String postID){
        wallDeleteAPI(userID, postID);
    }

    public HttpResponse<APIPhotoModel> fileTransfer(String uploadUrl, Map<String, Object> valuesMap){
        return (HttpResponse<APIPhotoModel>) fileTransfer(new APIPhotoModel(), uploadUrl, valuesMap);
    }

    public HttpResponse<JsonNode> saveUploadPhoto(String server, String photo, String hash){
        return (HttpResponse<JsonNode>) photosSaveWallPhotoAPI(server, photo, hash);
    }
}
