package com.epicodus.forum.models;

import java.util.Date;

/**
 * Created by Guest on 3/30/16.
 */
public class Message {
    private String categoryId;
    private String topicId;
    private String messageId;
    private String userID;
    private Date dateCreated;
    private String message;

    public Message(String categoryId, String topicId, String messageId, String userID, Date dateCreated, String message) {
        this.categoryId = categoryId;
        this.topicId = topicId;
        this.messageId = messageId;
        this.userID = userID;
        this.dateCreated = dateCreated;
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getMessage() {
        return message;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getMessageId() {
        return messageId;
    }

}
