package com.finalproject.model;

import java.io.Serializable;
import java.util.List;

public class FilterModel implements Serializable {
    private String id;
    private String title;
    private boolean isSelected = false;


    public FilterModel(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
