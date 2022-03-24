package com.finalproject.service;

import com.finalproject.model.SignUpModel;
import com.finalproject.model.UserModel;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {

    @FormUrlEncoded
    @POST("api/register")
    Single<Response<UserModel>> signUp(@Field("name") String name,
                                       @Field("user_name") String user_name,
                                       @Field("password") String password,
                                       @Field("national_id") String national_id,
                                       @Field("email") String email,
                                       @Field("gender") String gender,
                                       @Field("type") String type
    );

    @FormUrlEncoded
    @POST("api/login")
    Single<Response<UserModel>> logIn(
            @Field("user_name") String user_name,
            @Field("password") String password,
            @Field("type") String type
    );

}
