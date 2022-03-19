package com.finalproject.model;

import java.io.Serializable;

public class TimeModel implements Serializable {
    private String time_text;
    private String type;
    private boolean isSelected = false;

    public TimeModel(String time_text, String type) {
        this.time_text = time_text;
        this.type = type;
    }

    public String getTime_text() {
        return time_text;
    }

    public void setTime_text(String time_text) {
        this.time_text = time_text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
