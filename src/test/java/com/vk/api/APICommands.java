package com.vk.api;

public enum APICommands {
    WALL_POST("wall.post"),
    WALL_EDIT("wall.edit"),
    PHOTOS_GET_WALL_UPLOAD_SERVER("photos.getWallUploadServer"),
    WALL_CREATE_COMMENT("wall.createComment"),
    LIKES_IS_LIKED("likes.isLiked"),
    WALL_DELETE("wall.delete"),
    PHOTOS_SAVE_WALL_PHOTO("photos.saveWallPhoto");

    private final String command;

    APICommands(String url) {
        this.command = url;
    }

    public String getCommand() {
        return command;
    }
}
