package com.finalproject.model;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import com.finalproject.BR;

import java.io.Serializable;

public class ContactUsModel extends BaseObservable implements Serializable {
    private String name;
    private String phone;
    private String message;

    public ObservableField<String> error_name = new ObservableField<>();
    public ObservableField<String> error_phone = new ObservableField<>();
    public ObservableField<String> error_message = new ObservableField<>();

    public ContactUsModel() {
        this.name = "";
        this.phone = "";
        this.message = "";
    }

    public Boolean isDataValid(Context context) {

        if (!name.trim().isEmpty()
                &&
                !phone.trim().isEmpty()
              && (!message.trim().isEmpty()))
        {
            error_name.set(null);
            error_phone.set(null);
            error_message.set(null);

            return true;
        } else {
            if (name.trim().isEmpty()) {
                error_name.set("Field is Required");
            } else {
                error_name.set(null);
            }
            if (phone.trim().isEmpty()) {
                error_phone.set("Field is Required");
            } else {
                error_phone.set(null);
            }
            if (message.trim().isEmpty()) {
                error_message.set("Field is Required");
            } else {
                error_message.set(null);

            }
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.phone);
    }
}
