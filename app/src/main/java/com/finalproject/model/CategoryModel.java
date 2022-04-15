package com.finalproject.model;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
