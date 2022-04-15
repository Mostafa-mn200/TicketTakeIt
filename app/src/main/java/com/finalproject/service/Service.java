package com.finalproject.service;

import com.finalproject.model.HomeDataModel;
import com.finalproject.model.UserModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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



    @GET("api/home")
    Single<Response<HomeDataModel>>getHomeData();

}
