package com.finalproject.model;

import java.io.Serializable;

public class OwnerModel extends StatusResponse{
    private UserModel.Data data;

    public UserModel.Data getData() {
        return data;
    }

    public static class Data implements Serializable {
        private int id;
        private String name;
        private String user_name;
        private String national_id;
        private String gender;
        private String type;
        private String email;
        private String updated_at;
        private String created_at;


        public String getName() {
            return name;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getNational_id() {
            return national_id;
        }

        public String getEmail() {
            return email;
        }

        public String getGender() {
            return gender;
        }

        public String getType() {
            return type;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
        }
    }

}
