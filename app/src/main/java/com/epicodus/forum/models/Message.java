package com.epicodus.forum.models;

/**
 * Created by Guest on 3/30/16.
 */
public class Message {
    private String categoryId;
    private String topicId;
    private String messageId;
    private String name;

    public Message(String categoryId, String topicId, String messageId, String name) {
        this.categoryId = categoryId;
        this.topicId = topicId;
        this.messageId = messageId;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
