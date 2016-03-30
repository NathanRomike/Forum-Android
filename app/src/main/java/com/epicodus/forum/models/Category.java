package com.epicodus.forum.models;

/**
 * Created by Guest on 3/30/16.
 */
public class Category {
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
