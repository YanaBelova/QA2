package com.vk.api.models;

public class APIPostModel {
    private Response response;
    private class Response{
        private String post_id;
    }
    public String getPost_id(){
        return response.post_id;
    }
}