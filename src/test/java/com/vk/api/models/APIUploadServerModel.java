package com.vk.api.models;

public class APIUploadServerModel {
    private Response response;
    private class Response{
        private String album_id;
        private String upload_url;
        private String user_id;
    }

    public String getUploadUrl(){
        return response.upload_url;
    }
}
