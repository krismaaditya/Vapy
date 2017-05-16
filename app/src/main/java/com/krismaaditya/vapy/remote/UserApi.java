package com.krismaaditya.vapy.remote;

import com.krismaaditya.vapy.model.UserData;
import com.krismaaditya.vapy.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by KrismaAditya on 09/05/2017.
 */

//class interface yang digunakan untuk menangani HTTP request (GET, POST, DELETE) ke USER
public interface UserApi {

    //TAMPILIN USER BERDASARKAN NICKNAME
    @GET("user/nickname/{nickname}")
    Call<UserResponse> getUser(@Path("nickname") String nickname);
    
    //UNTUK LOGIN
    @FormUrlEncoded
    @POST("user/login")
    Call<UserResponse> loginUser(
            @Field("nickname") String nickname,
            @Field("password") String password
    );

    //UNTUK SIGNUP
    @FormUrlEncoded
    @POST("user/new")
    Call<UserResponse> newUser(
            @Field("fullname") String fullname,
            @Field("nickname") String nickname,
            @Field("email") String email,
            @Field("birthdate") String birthdate,
            @Field("gender") String gender,
            @Field("kota") String kota,
            @Field("password") String password
    );
}