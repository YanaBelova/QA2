package com.vk.api.models;

public class APICommentModel {
    private Response response;
    private class Response {
        private String comment_id;
        private String parents_stack;
    }
    public String getComment_id(){
        return response.comment_id;
    }
    public String getParents_stack(){
        return response.parents_stack;
    }
}
