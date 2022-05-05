package com.finalproject.service;

import com.finalproject.model.CategoryDataModel;
import com.finalproject.model.HomeDataModel;
import com.finalproject.model.MovieDetailsDataModel;
import com.finalproject.model.MoviesDataModel;
import com.finalproject.model.ShowDetailsDataModel;
import com.finalproject.model.ShowDataModel;
import com.finalproject.model.ShowModel;
import com.finalproject.model.SliderDataModel;
import com.finalproject.model.StatusResponse;
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
    Single<Response<SliderDataModel>>getSlider();

    @GET("api/home")
    Single<Response<HomeDataModel>>getHomeData();

    @GET("api/posts/show")
    Single<Response<ShowDataModel>>getShow();

    @GET("api/posts/move")
    Single<Response<MoviesDataModel>> getMovies(@Query("category_id") String category_id);

    @GET("api/categories")
    Single<Response<CategoryDataModel>> getCategories();

    @GET("api/onePost")
    Single<Response<MovieDetailsDataModel>> getDetails(@Query("post_id") String post_id);

    @GET("api/onePost")
    Single<Response<ShowDetailsDataModel>> getShowDetails(@Query("post_id") String post_id);

    @FormUrlEncoded
    @POST("api/contact_us")
    Single<Response<StatusResponse>> ContactUs(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("message") String message
    );

}
