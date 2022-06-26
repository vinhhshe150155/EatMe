package com.foodapp.eatme.model;

import java.util.HashMap;
import java.util.Map;

public class Comment {
    private String commentId;
    private String content;
    private long timestamp;
    private String userId;
    private String username;
    private Map<String, ChildComment> reply = new HashMap<>();
    private boolean isExpandable;

    public String getCommentId() {
        return commentId;
    }

    public Comment(String commentId, String content, long timestamp, String userId, String username, Map<String, ChildComment> reply, boolean isExpandable) {
        this.commentId = commentId;
        this.content = content;
        this.timestamp = timestamp;
        this.userId = userId;
        this.username = username;
        this.reply = reply;
        this.isExpandable = isExpandable;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Comment() {
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return userId;
    }

    public void setUid(String uid) {
        this.userId = uid;
    }

    public Map<String, ChildComment> getReply() {
        return reply;
    }

    public void setReply(Map<String, ChildComment> reply) {
        this.reply = reply;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
