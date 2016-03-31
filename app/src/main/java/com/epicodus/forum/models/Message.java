package com.epicodus.forum.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Guest on 3/30/16.
 */
public class Message {
    private String topicId;
    private String messageId;
    private String userID;
    private String dateCreated;
    private String message;

    public Message() {
    }

    public Message(String topicId, String messageId, String userID, String message) {
        this.topicId = topicId;
        this.messageId = messageId;
        this.userID = userID;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        this.dateCreated = dateFormat.format(date);
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getMessage() {
        return message;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getMessageId() {
        return messageId;
    }

}
