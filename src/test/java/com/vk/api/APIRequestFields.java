package com.vk.api;

public enum APIRequestFields {
    OWNER_ID("owner_id"),
    POST_ID("post_id"),
    ITEM_ID("item_id"),
    TYPE("type"),
    MESSAGE("message"),
    ATTACHMENTS("attachments"),
    PHOTO("photo"),
    SERVER("server"),
    HASH("hash"),
    ACCESSS_TOKEN("access_token"),
    V("v");

    private final String field;

    APIRequestFields(String url) {
        this.field = url;
    }

    public String getField() {
        return field;
    }
}
