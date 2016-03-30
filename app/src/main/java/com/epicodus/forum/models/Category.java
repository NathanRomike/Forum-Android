package com.epicodus.forum.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 3/30/16.
 */

@Parcel
public class Category {
    private String categoryName;
    private String categoryId;
//    private static ArrayList<Category> catagoryList = new ArrayList<>();

    public Category() {

    }

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
//        catagoryList.add(this);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

//    public static ArrayList<Category> getCategories() {
//        return catagoryList;
//    }

}
