package com.vk.api.models;

public class APILikeModel {
    private Response response;
    private class Response {
    private String liked;
    private String copied;
    }

    public int getLiked(){
        return Integer.parseInt(response.liked);
    }
    public String getCopied(){
        return response.copied;
    }
}
