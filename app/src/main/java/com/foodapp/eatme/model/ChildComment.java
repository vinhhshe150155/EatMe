package com.foodapp.eatme.model;

public class ChildComment {

    private String commentId;
    private String content;
    private long timestamp;
    private String username;
    private String parentId;
    private String userReply;

    public ChildComment() {
    }

    public ChildComment(String commentId, String content, long timestamp, String username, String parentId, String userReply) {
        this.commentId = commentId;
        this.content = content;
        this.timestamp = timestamp;
        this.username = username;
        this.parentId = parentId;
        this.userReply = userReply;
    }

    public String getUserReply() {
        return userReply;
    }

    public void setUserReply(String userReply) {
        this.userReply = userReply;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
