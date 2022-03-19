package com.finalproject.model;

import java.io.Serializable;

public class SeatModel implements Serializable {
    private String id;
    private boolean checked=false;
    private boolean isSelected;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
