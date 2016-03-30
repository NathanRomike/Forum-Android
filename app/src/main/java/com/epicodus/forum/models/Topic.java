package com.epicodus.forum.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 3/30/16.
 */
@Parcel
public class Topic {
    private String topicName;
    private String categoryId;
    private String topicId;

    public Topic() {

    }

    public Topic(String topicName, String categoryId, String topicId) {
        this.topicName = topicName;
        this.categoryId = categoryId;
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getTopicId() {
        return topicId;
    }
}
